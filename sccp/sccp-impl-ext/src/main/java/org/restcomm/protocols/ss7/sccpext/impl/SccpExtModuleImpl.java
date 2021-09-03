
package org.restcomm.protocols.ss7.sccpext.impl;

import java.util.concurrent.ConcurrentHashMap;

import javolution.util.FastMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.RemoteSccpStatus;
import org.restcomm.protocols.ss7.sccp.RemoteSignalingPointCode;
import org.restcomm.protocols.ss7.sccp.RemoteSubSystem;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpListener;
import org.restcomm.protocols.ss7.sccp.SignallingPointStatus;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeExt;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpConnectionImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpManagement;
import org.restcomm.protocols.ss7.sccp.impl.SccpProviderImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpRoutingCtxInterface;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpAddressedMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnCrMessageImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpConnCrMessage;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.SccpExtModule;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.SccpCongestionControl;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;

/**
*
* @author sergey vetyutnev
*
*/
public class SccpExtModuleImpl implements SccpExtModule {
    private Logger logger;
    private SccpStackImpl sccpStackImpl;
    private SccpProviderImpl sccpProviderImpl;

    protected Router router;
    protected SccpManagement sccpManagement;

    protected RouterExtImpl routerExt;
    protected SccpCongestionControl sccpCongestionControl;

    private ConcurrentHashMap<Integer, Long> prohibitedSpcs = new ConcurrentHashMap<Integer, Long>();

    public SccpExtModuleImpl() {
    }

    @Override
    public RouterExt getRouterExt() {
        return routerExt;
    }

    @Override
    public void init(SccpStackImpl sccpStackImpl) {
        this.logger = Logger.getLogger(SccpExtModuleImpl.class.getCanonicalName() + "-" + sccpStackImpl.getName());
        this.sccpStackImpl = sccpStackImpl;
    }

    @Override
    public void startExtBefore(String persistDir, String name) {
        RouterExtImpl.makeOldConfigCopy(persistDir, name);
    }

    @Override
    public void startExtAfter(Router router, SccpManagement sccpManagement) {
        this.sccpProviderImpl = (SccpProviderImpl) sccpStackImpl.getSccpProvider();

        this.router = router;
        this.sccpManagement = sccpManagement;

        this.sccpCongestionControl = new SccpCongestionControl(sccpManagement, sccpStackImpl);

        routerExt = new RouterExtImpl(sccpStackImpl.getName(), sccpStackImpl, router);
        routerExt.setPersistDir(sccpStackImpl.getPersistDir());
        routerExt.start();
    }

    @Override
    public void stopExt() {
        routerExt.stop();
    }

    @Override
    public void removeAllResources() {
        routerExt.removeAllResources();
    }

    @Override
    public int findDpsForAddresses(SccpAddress calledPartyAddress, SccpAddress callingPartyAddress, int msgNetworkId) {
        Rule rule = routerExt.findRule(calledPartyAddress, callingPartyAddress, false, msgNetworkId);
        if (rule == null) {
            return 0;
        }
        SccpAddress translationAddressPri = routerExt.getRoutingAddress(rule.getPrimaryAddressId());
        if (translationAddressPri == null) {
            return 0;
        }

        return translationAddressPri.getSignalingPointCode();
    }



    @Override
    public void translationFunction(SccpRoutingCtxInterface ctx, SccpAddressedMessageImpl msg) throws Exception {

        // checking for hop counter
        if (!msg.reduceHopCounter()) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format(
                        "Received SccpMessage for Translation but hop counter violation detected\nSccpMessage=%s", msg));
            }
            ctx.sendSccpError(msg, ReturnCauseValue.HOP_COUNTER_VIOLATION, RefusalCauseValue.HOP_COUNTER_VIOLATION);
            return;
        }

        SccpAddress calledPartyAddress = msg.getCalledPartyAddress();
        SccpAddress callingPartyAddress = msg.getCallingPartyAddress();

        Rule rule = routerExt.findRule(calledPartyAddress, callingPartyAddress, msg.getIsMtpOriginated(), msg.getNetworkId());
        if (rule == null) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format(
                        "Received SccpMessage for Translation but no matching Rule found for local routing\nSccpMessage=%s",
                        msg));
            }
            // Translation failed return error
            ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
            return;
        }

        // Check whether to use primary or backup address
        SccpAddress translationAddressPri = routerExt.getRoutingAddress(rule.getPrimaryAddressId());
        TranslationAddressCheckingResult resPri = this.checkTranslationAddress(msg, rule, translationAddressPri, "primary");
        if (resPri == TranslationAddressCheckingResult.translationFailure) {
            ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
            return;
        }

        SccpAddress translationAddressSec = null;
        TranslationAddressCheckingResult resSec = TranslationAddressCheckingResult.destinationUnavailable_SubsystemFailure;
        if (rule.getRuleType() != RuleType.SOLITARY) {
            translationAddressSec = routerExt.getRoutingAddress(rule.getSecondaryAddressId());
            resSec = this.checkTranslationAddress(msg, rule, translationAddressSec, "secondary");
            if (resSec == TranslationAddressCheckingResult.translationFailure) {
                ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
                return;
            }
        }

        if (resPri != TranslationAddressCheckingResult.destinationAvailable
                && resPri != TranslationAddressCheckingResult.destinationUnavailable_Congestion
                && resSec != TranslationAddressCheckingResult.destinationAvailable
                && resSec != TranslationAddressCheckingResult.destinationUnavailable_Congestion) {
            switch (resPri) {
                case destinationUnavailable_SubsystemFailure:
                    ctx.sendSccpError(msg, ReturnCauseValue.SUBSYSTEM_FAILURE, RefusalCauseValue.SUBSYSTEM_FAILURE);
                    return;
                case destinationUnavailable_MtpFailure:
                    ctx.sendSccpError(msg, ReturnCauseValue.MTP_FAILURE, RefusalCauseValue.DESTINATION_INACCESSIBLE);
                    return;
                case destinationUnavailable_Congestion:
                    ctx.sendSccpError(msg, ReturnCauseValue.NETWORK_CONGESTION, RefusalCauseValue.SUBSYSTEM_CONGESTION);
                    return;
                default:
                    ctx.sendSccpError(msg, ReturnCauseValue.SCCP_FAILURE, RefusalCauseValue.SCCP_FAILURE);
                    return;
            }
        }

        SccpAddress translationAddress = null;
        SccpAddress translationAddress2 = null;
        if (resPri == TranslationAddressCheckingResult.destinationAvailable
                && resSec != TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationAddressPri;
        } else if (resPri != TranslationAddressCheckingResult.destinationAvailable
                && resSec == TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationAddressSec;
        } else if (resPri == TranslationAddressCheckingResult.destinationUnavailable_Congestion
                && resSec != TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationAddressPri;
        } else if (resPri != TranslationAddressCheckingResult.destinationAvailable
                && resSec == TranslationAddressCheckingResult.destinationUnavailable_Congestion) {
            translationAddress = translationAddressSec;
        } else {
            switch (rule.getRuleType()) {
                case SOLITARY:
                case DOMINANT:
                    translationAddress = translationAddressPri;
                    break;
                case LOADSHARED:
                    // loadsharing case and both destinations are available
                    if(!msg.getIsIncoming() && sccpStackImpl.isRespectPc()) {
                        int pc = msg.getOutgoingDpc();
                        if(pc > 0 && pc == translationAddressSec.getSignalingPointCode())
                            translationAddress = translationAddressSec;
                        else
                            translationAddress = translationAddressPri;
                    } else {
                        if (msg.getSccpCreatesSls()) {
                            if (this.sccpStackImpl.newSelector())
                                translationAddress = translationAddressPri;
                            else
                                translationAddress = translationAddressSec;
                        } else {
                            if (this.selectLoadSharingRoute(rule.getLoadSharingAlgorithm(), msg))
                                translationAddress = translationAddressPri;
                            else
                                translationAddress = translationAddressSec;
                        }
                    }
                    break;

                case BROADCAST:
                    // Broadcast case and both destinations are available
                    translationAddress = translationAddressPri;
                    translationAddress2 = translationAddressSec;
                    break;
            }
        }

        // changing calling party address if a rule has NewCallingPartyAddress
        if (rule.getNewCallingPartyAddressId() != null) {
            SccpAddress newCallingPartyAddress = routerExt.getRoutingAddress(rule.getNewCallingPartyAddressId());
            if (newCallingPartyAddress != null) {
                msg.setCallingPartyAddress(newCallingPartyAddress);
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("New CallingPartyAddress assigned after translation = %s",
                            newCallingPartyAddress));
                }
            }
        }

        // translate address
        SccpAddress address = rule.translate(calledPartyAddress, translationAddress);

        if (msg instanceof SccpConnCrMessageImpl && router.spcIsLocal(msg.getIncomingDpc())
                && !router.spcIsLocal(address.getSignalingPointCode()) && sccpStackImpl.isCanRelay()) {

            SccpConnCrMessageImpl inputCr = (SccpConnCrMessageImpl) msg;
            SccpAddress here = null;
            int localSsn = address.getSubsystemNumber(); // could use any ssn here but we know only dest SSN so will use it
            here = sccpProviderImpl.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, msg.getIncomingDpc(), localSsn);


            SccpConnectionImpl conn = sccpStackImpl.newConnection(address.getSubsystemNumber(), inputCr.getProtocolClass());
            int firstSsn = 0;
            if (msg.getCallingPartyAddress() != null) {
                conn.setRemoteSsn(msg.getCallingPartyAddress().getSubsystemNumber());
                firstSsn = conn.getRemoteSsn();
            }


            SccpConnectionImpl conn1 = (SccpConnectionImpl) sccpProviderImpl.newConnection(conn.getRemoteSsn(), inputCr.getProtocolClass());
            conn.enableCoupling(conn1);


            SccpConnCrMessageImpl copy = new SccpConnCrMessageImpl(inputCr.getSls(), inputCr.getOriginLocalSsn(),
                    here, inputCr.getCallingPartyAddress(), inputCr.getHopCounter());
            copy.setImportance(inputCr.getImportance());
            copy.setUserData(inputCr.getUserData());
            copy.setCredit(inputCr.getCredit());
            copy.setProtocolClass(inputCr.getProtocolClass());
            copy.setSourceLocalReferenceNumber(inputCr.getSourceLocalReferenceNumber());
            copy.setIncomingDpc(inputCr.getIncomingDpc());
            copy.setIncomingOpc(inputCr.getIncomingOpc());

            conn.receiveMessage(copy);

            SccpAddress here2 = sccpProviderImpl.getParameterFactory()
                    .createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, msg.getIncomingDpc(), firstSsn);
            SccpConnCrMessage crMsg = sccpProviderImpl.getMessageFactory()
                    .createConnectMessageClass2(firstSsn, address, here2, inputCr.getUserData(), inputCr.getImportance());
            crMsg.setProtocolClass(inputCr.getProtocolClass());
            crMsg.setCredit(inputCr.getCredit());

            conn1.establish(crMsg);

            return;
        }

        msg.setCalledPartyAddress(address);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Matching rule found: [%s] CalledPartyAddress after translation = %s", rule, address));
        }

        // routing procedures then continue's
        ctx.routeAddressed(msg);

        if (translationAddress2 != null) {
            // for broadcast mode - route to a secondary destination if it is available
            address = rule.translate(calledPartyAddress, translationAddress2);
            msg.setCalledPartyAddress(address);
            msg.clearReturnMessageOnError();

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("CalledPartyAddress after translation - a second broadcast address = %s", address));
            }

            // routing procedures then continue's
            ctx.routeAddressed(msg);
        }
    }

    private TranslationAddressCheckingResult checkTranslationAddress(SccpAddressedMessageImpl msg, Rule rule,
                                                                     SccpAddress translationAddress, String destName) {

        if (translationAddress == null) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format(
                        "Received SccpMessage=%s for Translation but no matching %s Address defined for Rule=%s for routing",
                        msg, destName, rule));
            }
            return TranslationAddressCheckingResult.translationFailure;
        }

        if (!translationAddress.getAddressIndicator().isPCPresent()) {

            // destination PC is absent - bad rule
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format("Received SccpMessage=%s for Translation but no PC is present for %s Address ", msg,
                        destName));
            }
            return TranslationAddressCheckingResult.translationFailure;
        }

        int targetSsn = translationAddress.getSubsystemNumber();
        if (targetSsn == 0)
            targetSsn = msg.getCalledPartyAddress().getSubsystemNumber();

        if (router.spcIsLocal(translationAddress.getSignalingPointCode())) {
            // destination PC is local
            if (targetSsn == 1 || sccpProviderImpl.getSccpListener(targetSsn) != null) {
                return TranslationAddressCheckingResult.destinationAvailable;
            } else {
                if (logger.isEnabledFor(Level.WARN)) {
                    logger.warn(String.format(
                            "Received SccpMessage=%s for Translation but no local SSN is present for %s Address ", msg,
                            destName));
                }
                return TranslationAddressCheckingResult.destinationUnavailable_SubsystemFailure;
            }
        }

        // Check if the DPC is prohibited
        RemoteSignalingPointCode remoteSpc = this.sccpStackImpl.getSccpResource().getRemoteSpcByPC(
                translationAddress.getSignalingPointCode());
        if (remoteSpc == null) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format(
                        "Received SccpMessage=%s for Translation but no %s Remote Signaling Pointcode = %d resource defined ",
                        msg, destName, translationAddress.getSignalingPointCode()));
            }
            return TranslationAddressCheckingResult.translationFailure;
        }

        if (remoteSpc.isRemoteSpcProhibited()) {
            Long lastTimeLog = prohibitedSpcs.get(remoteSpc.getRemoteSpc());
            if(lastTimeLog == null || System.currentTimeMillis() - lastTimeLog > sccpStackImpl.getPeriodOfLogging())
            {
                prohibitedSpcs.put(remoteSpc.getRemoteSpc(), System.currentTimeMillis());
                if (logger.isEnabledFor(Level.WARN)) {
                    logger.warn(String.format(
                            "Received SccpMessage=%s for Translation but %s Remote Signaling Pointcode = %d is prohibited ", msg,
                            destName, translationAddress.getSignalingPointCode()));
                }
            }

            return TranslationAddressCheckingResult.destinationUnavailable_MtpFailure;
        }

        // Check if the DPC is congested
        if (remoteSpc.getCurrentRestrictionLevel() > 1) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String
                        .format("Received SccpMessage=%s for Translation but %s Remote Signaling Pointcode = %d is congested with level %d ",
                                msg, destName, translationAddress.getSignalingPointCode(),
                                remoteSpc.getCurrentRestrictionLevel()));
            }
            return TranslationAddressCheckingResult.destinationUnavailable_Congestion;
        }

        if (translationAddress.getAddressIndicator().getRoutingIndicator() == RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN) {
            if (targetSsn != 1) {
                RemoteSubSystem remoteSubSystem = this.sccpStackImpl.getSccpResource().getRemoteSsn(
                        translationAddress.getSignalingPointCode(), targetSsn);
                if (remoteSubSystem == null) {
                    if (logger.isEnabledFor(Level.WARN)) {
                        logger.warn(String.format("Received SccpMessage=%s for Translation but no %s Remote SubSystem = %d (dpc=%d) resource defined ", msg,
                                destName, targetSsn, translationAddress.getSignalingPointCode()));
                    }
                    return TranslationAddressCheckingResult.translationFailure;
                }
                if (remoteSubSystem.isRemoteSsnProhibited()) {
                    if (logger.isEnabledFor(Level.WARN)) {
                        logger.warn(String.format("Received SccpMessage=%s for Translation but %s Remote SubSystem = %d (dpc=%d) is prohibited ", msg,
                                destName, targetSsn, translationAddress.getSignalingPointCode()));
                    }
                    return TranslationAddressCheckingResult.destinationUnavailable_SubsystemFailure;
                }
            }
        }

        return TranslationAddressCheckingResult.destinationAvailable;
    }

    private boolean selectLoadSharingRoute(LoadSharingAlgorithm loadSharingAlgo, SccpAddressedMessageImpl msg) {

        if (loadSharingAlgo == LoadSharingAlgorithm.Bit4) {
            if ((msg.getSls() & 0x10) == 0)
                return true;
            else
                return false;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit3) {
            if ((msg.getSls() & 0x08) == 0)
                return true;
            else
                return false;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit2) {
            if ((msg.getSls() & 0x04) == 0)
                return true;
            else
                return false;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit1) {
            if ((msg.getSls() & 0x02) == 0)
                return true;
            else
                return false;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit0) {
            if ((msg.getSls() & 0x01) == 0)
                return true;
            else
                return false;
        } else {
            // TODO: implement complicated algorithms for selecting a destination
            // (CallingPartyAddress & SLS depended)
            // Look at Q.815 8.1.3 - active loadsharing
            return true;
        }
    }

    private enum TranslationAddressCheckingResult {
        destinationAvailable, destinationUnavailable_SubsystemFailure, destinationUnavailable_MtpFailure, destinationUnavailable_Congestion, translationFailure;
    }

    @Override
    public void onAllowRsp(int affectedPc, RemoteSccpStatus remoteSccpStatus) {
        FastMap<Integer, NetworkIdState> lst = routerExt.getNetworkIdList(affectedPc);
        FastMap<Integer, SccpListener> lstrs = this.sccpProviderImpl.getAllSccpListeners();
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1;) {
            try {
                e1.getValue().onPcState(affectedPc, SignallingPointStatus.ACCESSIBLE, 0, remoteSccpStatus);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2;) {
                try {
                    e1.getValue().onNetworkIdState(e2.getKey(), e2.getValue());
                } catch (Exception ee) {
                    logger.error("Exception while invoking onNetworkIdState", ee);
                }
            }
        }
    }

    @Override
    public void onProhibitRsp(int affectedPc, RemoteSccpStatus remoteSccpStatus, RemoteSignalingPointCodeImpl remoteSpc) {
        FastMap<Integer, NetworkIdState> lst = routerExt.getNetworkIdList(affectedPc);
        FastMap<Integer, SccpListener> lstrs = this.sccpProviderImpl.getAllSccpListeners();
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1;) {
            try {
                e1.getValue().onPcState(
                        affectedPc,
                        (remoteSpc.isRemoteSpcProhibited() ? SignallingPointStatus.INACCESSIBLE
                                : SignallingPointStatus.ACCESSIBLE), 0, remoteSccpStatus);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2;) {
                try {
                    e1.getValue().onNetworkIdState(e2.getKey(), e2.getValue());
                } catch (Exception ee) {
                    logger.error("Exception while invoking onNetworkIdState", ee);
                }
            }
        }
    }

    @Override
    public void onRestrictionLevelChange(int affectedPc, int restrictionLevel, boolean levelEncreased) {
        int congLevel = SccpCongestionControl.generateSccpUserCongLevel(restrictionLevel);

        FastMap<Integer, NetworkIdState> lst = routerExt.getNetworkIdList(affectedPc);
        FastMap<Integer, SccpListener> lstrs = this.sccpProviderImpl.getAllSccpListeners();
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1;) {
            try {
                e1.getValue().onPcState(affectedPc,
                        levelEncreased ? SignallingPointStatus.CONGESTED : SignallingPointStatus.CONGESTION_REDUCED, congLevel,
                        null);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2;) {
                try {
                    e1.getValue().onNetworkIdState(e2.getKey(), e2.getValue());
                } catch (Exception ee) {
                    logger.error("Exception while invoking onNetworkIdState", ee);
                }
            }
        }
    }

    @Override
    public FastMap<Integer, NetworkIdState> getNetworkIdList(int affectedPc) {
        return this.routerExt.getNetworkIdList(affectedPc);
    }

    @Override
    public RemoteSignalingPointCodeExt createRemoteSignalingPointCodeExt(
            RemoteSignalingPointCodeImpl remoteSignalingPointCodeImpl) {
        return new RemoteSignalingPointCodeExtImpl(this.sccpCongestionControl, remoteSignalingPointCodeImpl);
    }

}
