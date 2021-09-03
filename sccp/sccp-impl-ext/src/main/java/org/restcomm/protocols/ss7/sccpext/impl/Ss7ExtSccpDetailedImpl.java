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
import org.restcomm.protocols.ss7.sccpext.impl.congestion.SccpCongestionControl;
import org.restcomm.protocols.ss7.sccp.impl.Ss7ExtSccpDetailedInterface;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;

public class Ss7ExtSccpDetailedImpl implements Ss7ExtSccpDetailedInterface {

    private Logger logger;
    private SccpStackImpl sccpStackImpl;
    private SccpProviderImpl sccpProviderImpl;

    protected Router router;
    protected SccpManagement sccpManagement;

    protected RouterExtImpl routerExt;
    protected SccpCongestionControl sccpCongestionControl;
    private final ConcurrentHashMap<Integer, Long> prohibitedSpcs = new ConcurrentHashMap<>();

    public Ss7ExtSccpDetailedImpl() {
    }

    @Override
    public void init(SccpStackImpl sccpStackImpl) {
        this.logger = Logger.getLogger(Ss7ExtSccpDetailedImpl.class.getCanonicalName() + "-" + sccpStackImpl.getName());
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
        SccpAddress translationPrimaryAddress = routerExt.getRoutingAddress(rule.getPrimaryAddressId());
        if (translationPrimaryAddress == null) {
            return 0;
        }

        return translationPrimaryAddress.getSignalingPointCode();
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
        SccpAddress translationPrimaryAddress = routerExt.getRoutingAddress(rule.getPrimaryAddressId());
        TranslationAddressCheckingResult translationPrimaryAddressResult = this.checkTranslationAddress(msg, rule, translationPrimaryAddress, "primary");
        if (translationPrimaryAddressResult == TranslationAddressCheckingResult.translationFailure) {
            ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
            return;
        }

        SccpAddress translationSecondaryAddress = null;
        TranslationAddressCheckingResult translationSecondaryAddressResult = TranslationAddressCheckingResult.destinationUnavailable_SubsystemFailure;
        if (rule.getRuleType() != RuleType.SOLITARY) {
            translationSecondaryAddress = routerExt.getRoutingAddress(rule.getSecondaryAddressId());
            translationSecondaryAddressResult = this.checkTranslationAddress(msg, rule, translationSecondaryAddress, "secondary");
            if (translationSecondaryAddressResult == TranslationAddressCheckingResult.translationFailure) {
                ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
                return;
            }
        }

        if (translationPrimaryAddressResult != TranslationAddressCheckingResult.destinationAvailable
                && translationPrimaryAddressResult != TranslationAddressCheckingResult.destinationUnavailable_Congestion
                && translationSecondaryAddressResult != TranslationAddressCheckingResult.destinationAvailable
                && translationSecondaryAddressResult != TranslationAddressCheckingResult.destinationUnavailable_Congestion) {
            switch (translationPrimaryAddressResult) {
                case destinationUnavailable_SubsystemFailure:
                    ctx.sendSccpError(msg, ReturnCauseValue.SUBSYSTEM_FAILURE, RefusalCauseValue.SUBSYSTEM_FAILURE);
                    return;
                case destinationUnavailable_MtpFailure:
                    ctx.sendSccpError(msg, ReturnCauseValue.MTP_FAILURE, RefusalCauseValue.DESTINATION_INACCESSIBLE);
                    return;
                case translationFailure:
                    ctx.sendSccpError(msg, ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS, RefusalCauseValue.NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE);
                    return;
                default:
                    ctx.sendSccpError(msg, ReturnCauseValue.SCCP_FAILURE, RefusalCauseValue.SCCP_FAILURE);
                    return;
            }
        }

        SccpAddress translationAddress = null;
        SccpAddress translationAddress2 = null;
        if (translationPrimaryAddressResult == TranslationAddressCheckingResult.destinationAvailable
                && translationSecondaryAddressResult != TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationPrimaryAddress;
        } else if (translationPrimaryAddressResult != TranslationAddressCheckingResult.destinationAvailable
                && translationSecondaryAddressResult == TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationSecondaryAddress;
        } else if (translationPrimaryAddressResult == TranslationAddressCheckingResult.destinationUnavailable_Congestion
                && translationSecondaryAddressResult != TranslationAddressCheckingResult.destinationAvailable) {
            translationAddress = translationPrimaryAddress;
        } else if (translationPrimaryAddressResult != TranslationAddressCheckingResult.destinationAvailable
                && translationSecondaryAddressResult == TranslationAddressCheckingResult.destinationUnavailable_Congestion) {
            translationAddress = translationSecondaryAddress;
        } else {
            switch (rule.getRuleType()) {
                case SOLITARY:
                case DOMINANT:
                    translationAddress = translationPrimaryAddress;
                    break;
                case LOADSHARED:
                    // load sharing case and both destinations are available
                    if (!msg.getIsIncoming() && sccpStackImpl.isRespectPc()) {
                        int pc = msg.getOutgoingDpc();
                        if (pc > 0 && pc == translationSecondaryAddress.getSignalingPointCode())
                            translationAddress = translationSecondaryAddress;
                        else
                            translationAddress = translationPrimaryAddress;
                    } else {
                        if (msg.getSccpCreatesSls()) {
                            if (this.sccpStackImpl.newSelector())
                                translationAddress = translationPrimaryAddress;
                            else
                                translationAddress = translationSecondaryAddress;
                        } else {
                            if (this.selectLoadSharingRoute(rule.getLoadSharingAlgorithm(), msg))
                                translationAddress = translationPrimaryAddress;
                            else
                                translationAddress = translationSecondaryAddress;
                        }
                    }
                    break;

                case BROADCAST:
                    // Broadcast case and both destinations are available
                    translationAddress = translationPrimaryAddress;
                    translationAddress2 = translationSecondaryAddress;
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
            SccpAddress sccpAddress1;
            int localSsn = address.getSubsystemNumber(); // could use any ssn here but we know only dest SSN so will use it
            sccpAddress1 = sccpProviderImpl.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, msg.getIncomingDpc(), localSsn);


            SccpConnectionImpl conn = sccpStackImpl.newConnection(address.getSubsystemNumber(), inputCr.getProtocolClass());
            int firstSsn = 0;
            if (msg.getCallingPartyAddress() != null) {
                conn.setRemoteSsn(msg.getCallingPartyAddress().getSubsystemNumber());
                firstSsn = conn.getRemoteSsn();
            }


            SccpConnectionImpl conn1 = (SccpConnectionImpl) sccpProviderImpl.newConnection(conn.getRemoteSsn(), inputCr.getProtocolClass());
            conn.enableCoupling(conn1);


            SccpConnCrMessageImpl sccpConnectionRequestMessage = new SccpConnCrMessageImpl(inputCr.getSls(), inputCr.getOriginLocalSsn(),
                sccpAddress1, inputCr.getCallingPartyAddress(), inputCr.getHopCounter());
            sccpConnectionRequestMessage.setImportance(inputCr.getImportance());
            sccpConnectionRequestMessage.setUserData(inputCr.getUserData());
            sccpConnectionRequestMessage.setCredit(inputCr.getCredit());
            sccpConnectionRequestMessage.setProtocolClass(inputCr.getProtocolClass());
            sccpConnectionRequestMessage.setSourceLocalReferenceNumber(inputCr.getSourceLocalReferenceNumber());
            sccpConnectionRequestMessage.setIncomingDpc(inputCr.getIncomingDpc());
            sccpConnectionRequestMessage.setIncomingOpc(inputCr.getIncomingOpc());

            conn.receiveMessage(sccpConnectionRequestMessage);

            SccpAddress sccpAddress2 = sccpProviderImpl.getParameterFactory()
                    .createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, msg.getIncomingDpc(), firstSsn);
            SccpConnCrMessage sccpConnectionRequestMessage2 = sccpProviderImpl.getMessageFactory()
                    .createConnectMessageClass2(firstSsn, address, sccpAddress2, inputCr.getUserData(), inputCr.getImportance());
            sccpConnectionRequestMessage2.setProtocolClass(inputCr.getProtocolClass());
            sccpConnectionRequestMessage2.setCredit(inputCr.getCredit());

            conn1.establish(sccpConnectionRequestMessage2);

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
            if (lastTimeLog == null || System.currentTimeMillis() - lastTimeLog > sccpStackImpl.getPeriodOfLogging()) {
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
                        .format("Received SccpMessage=%s for Translation but %s Remote Signaling Point Code = %d is congested with level %d ",
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
            return (msg.getSls() & 0x10) == 0;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit3) {
            return (msg.getSls() & 0x08) == 0;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit2) {
            return (msg.getSls() & 0x04) == 0;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit1) {
            return (msg.getSls() & 0x02) == 0;
        } else if (loadSharingAlgo == LoadSharingAlgorithm.Bit0) {
            return (msg.getSls() & 0x01) == 0;
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
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1; ) {
            try {
                e1.getValue().onPcState(affectedPc, SignallingPointStatus.ACCESSIBLE, 0, remoteSccpStatus);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2; ) {
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
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1; ) {
            try {
                e1.getValue().onPcState(
                        affectedPc,
                        (remoteSpc.isRemoteSpcProhibited() ? SignallingPointStatus.INACCESSIBLE
                                : SignallingPointStatus.ACCESSIBLE), 0, remoteSccpStatus);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2; ) {
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
        for (FastMap.Entry<Integer, SccpListener> e1 = lstrs.head(), end1 = lstrs.tail(); (e1 = e1.getNext()) != end1; ) {
            try {
                e1.getValue().onPcState(affectedPc,
                        levelEncreased ? SignallingPointStatus.CONGESTED : SignallingPointStatus.CONGESTION_REDUCED, congLevel,
                        null);
            } catch (Exception ee) {
                logger.error("Exception while invoking onPcState", ee);
            }
            for (FastMap.Entry<Integer, NetworkIdState> e2 = lst.head(), end2 = lst.tail(); (e2 = e2.getNext()) != end2; ) {
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
    public RemoteSignalingPointCodeExt createRemoteSignalingPointCodeExt(RemoteSignalingPointCodeImpl remoteSignalingPointCodeImpl) {
        return new RemoteSignalingPointCodeExtImpl(this.sccpCongestionControl, remoteSignalingPointCodeImpl);
    }
}
