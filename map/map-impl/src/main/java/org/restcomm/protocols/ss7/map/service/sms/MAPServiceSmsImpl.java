
package org.restcomm.protocols.ss7.map.service.sms;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.MAPServiceBaseImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSmsListener;
import org.restcomm.protocols.ss7.map.dialog.ServingCheckDataImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPServiceSmsImpl extends MAPServiceBaseImpl implements MAPServiceSms {

    protected Logger logger = Logger.getLogger(MAPServiceSmsImpl.class);

    public MAPServiceSmsImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /*
     * Creating a new outgoing MAP SMS dialog and adding it to the MAPProvider.dialog collection
     */
    public MAPDialogSms createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogSms createNewDialog(MAPApplicationContext appCntx, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogSms because MAPServiceSms is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        MAPDialogSmsImpl dialog = new MAPDialogSmsImpl(appCntx, tcapDialog, this.mapProviderImpl, this, origReference,
                destReference);

        this.putMAPDialogIntoCollection(dialog);

        return dialog;
    }

    @Override
    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext appCntx, Dialog tcapDialog) {
        return new MAPDialogSmsImpl(appCntx, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    public void addMAPServiceListener(MAPServiceSmsListener mapServiceSmsListener) {
        super.addMAPServiceListener(mapServiceSmsListener);
    }

    public void removeMAPServiceListener(MAPServiceSmsListener mapServiceSmsListener) {
        super.removeMAPServiceListener(mapServiceSmsListener);
    }

    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName mapApplicationContextName = mapDialogApplicationContext.getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (mapApplicationContextName) {
            case shortMsgAlertContext:
                if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 2) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else if (mapDialogApplicationContextVersion > 2) {
                    long[] altOid = mapDialogApplicationContext.getOID();
                    altOid[7] = 2;
                    ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }

            case shortMsgMORelayContext:
            case shortMsgGatewayContext:
                if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else if (mapDialogApplicationContextVersion > 3) {
                    long[] altOid = mapDialogApplicationContext.getOID();
                    altOid[7] = 3;
                    ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }

            case shortMsgMTRelayContext:
                if (mapDialogApplicationContextVersion >= 2 && mapDialogApplicationContextVersion <= 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else if (mapDialogApplicationContextVersion > 3) {
                    long[] altOid = mapDialogApplicationContext.getOID();
                    altOid[7] = 3;
                    ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }

            case mwdMngtContext:
                if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else if (mapDialogApplicationContextVersion > 3) {
                    long[] altOid = mapDialogApplicationContext.getOID();
                    altOid[7] = 3;
                    ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    @Override
    public MAPApplicationContext getMAPv1ApplicationContext(int operationCode, Invoke invoke) {

        switch (operationCode) {
            case MAPOperationCode.mo_forwardSM:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgMORelayContext,
                        MAPApplicationContextVersion.version1);
            case MAPOperationCode.alertServiceCentreWithoutResult:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgAlertContext,
                        MAPApplicationContextVersion.version1);
            case MAPOperationCode.sendRoutingInfoForSM:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgGatewayContext,
                        MAPApplicationContextVersion.version1);
            case MAPOperationCode.reportSM_DeliveryStatus:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgGatewayContext,
                        MAPApplicationContextVersion.version1);
            case MAPOperationCode.noteSubscriberPresent:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.mwdMngtContext,
                        MAPApplicationContextVersion.version1);
        }

        return null;
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

        // if an application-context-name different from version 1 is
        // received in a syntactically correct TC-
        // BEGIN indication primitive but is not acceptable from a load
        // control point of view, the MAP PM
        // shall ignore this dialogue request. The MAP-user is not informed.
//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // we agree all sms services when congestion
//        }

        MAPDialogSmsImpl mapDialogSmsImpl = (MAPDialogSmsImpl) mapDialog;

        Long ocValue = operationCode.getLocalOperationCode();
        if (ocValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);
        MAPApplicationContextName acn = mapDialog.getApplicationContext().getApplicationContextName();
        int vers = mapDialog.getApplicationContext().getApplicationContextVersion().getVersion();
        int ocValueInt = (int) (long) ocValue;

        switch (ocValueInt) {
            case MAPOperationCode.mo_forwardSM:
                if (acn == MAPApplicationContextName.shortMsgMORelayContext
                        || acn == MAPApplicationContextName.shortMsgMTRelayContext && vers == 2) {
                    if (vers >= 3) {
                        if (componentType == ComponentType.Invoke)
                            this.moForwardShortMessageRequest(parameter, mapDialogSmsImpl, invokeId);
                        else
                            this.moForwardShortMessageResponse(parameter, mapDialogSmsImpl, invokeId,
                                    componentType == ComponentType.ReturnResult);
                    } else {
                        if (componentType == ComponentType.Invoke)
                            this.forwardShortMessageRequest(parameter, mapDialogSmsImpl, invokeId);
                        else
                            this.forwardShortMessageResponse(parameter, mapDialogSmsImpl, invokeId,
                                    componentType == ComponentType.ReturnResult);
                    }
                }
                break;

            case MAPOperationCode.mt_forwardSM:
                if (acn == MAPApplicationContextName.shortMsgMTRelayContext && vers >= 3) {
                    if (componentType == ComponentType.Invoke)
                        this.mtForwardShortMessageRequest(parameter, mapDialogSmsImpl, invokeId);
                    else
                        this.mtForwardShortMessageResponse(parameter, mapDialogSmsImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.sendRoutingInfoForSM:
                if (acn == MAPApplicationContextName.shortMsgGatewayContext) {
                    if (componentType == ComponentType.Invoke)
                        this.sendRoutingInfoForSMRequest(parameter, mapDialogSmsImpl, invokeId);
                    else
                        this.sendRoutingInfoForSMResponse(parameter, mapDialogSmsImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.reportSM_DeliveryStatus:
                if (acn == MAPApplicationContextName.shortMsgGatewayContext) {
                    if (componentType == ComponentType.Invoke)
                        this.reportSMDeliveryStatusRequest(parameter, mapDialogSmsImpl, invokeId);
                    else
                        this.reportSMDeliveryStatusResponse(parameter, mapDialogSmsImpl, invokeId, vers,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.informServiceCentre:
                if (acn == MAPApplicationContextName.shortMsgGatewayContext && vers >= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.informServiceCentreRequest(parameter, mapDialogSmsImpl, invokeId);
                }
                break;

            case MAPOperationCode.alertServiceCentre:
                if (acn == MAPApplicationContextName.shortMsgAlertContext && vers >= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.alertServiceCentreRequest(parameter, mapDialogSmsImpl, invokeId, ocValueInt);
                    else
                        this.alertServiceCentreResponse(parameter, mapDialogSmsImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.alertServiceCentreWithoutResult:
                if (acn == MAPApplicationContextName.shortMsgAlertContext && vers == 1) {
                    if (componentType == ComponentType.Invoke)
                        this.alertServiceCentreRequest(parameter, mapDialogSmsImpl, invokeId, ocValueInt);
                }
                break;

            case MAPOperationCode.readyForSM:
                if (acn == MAPApplicationContextName.mwdMngtContext && vers >= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.readyForSMRequest(parameter, mapDialogSmsImpl, invokeId, ocValueInt);
                    else
                        this.readyForSMResponse(parameter, mapDialogSmsImpl, invokeId, componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.noteSubscriberPresent:
                if (acn == MAPApplicationContextName.mwdMngtContext && vers == 1) {
                    if (componentType == ComponentType.Invoke)
                        this.noteSubscriberPresentRequest(parameter, mapDialogSmsImpl, invokeId, ocValueInt);
                }
                break;

            default:
                throw new MAPParsingComponentException("MAPServiceSms: unknown incoming operation code: " + ocValueInt,
                        MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void forwardShortMessageRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding forwardShortMessageRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding moForwardShortMessageRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ForwardShortMessageRequestImpl ind = new ForwardShortMessageRequestImpl();
        ind.decodeData(ais, buf.length);

        ind.setInvokeId(invokeId);
        ind.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(ind);
                ((MAPServiceSmsListener) serLis).onForwardShortMessageRequest(ind);
            } catch (Exception e) {
                logger.error("Error processing forwardShortMessageRequest: " + e.getMessage(), e);
            }
        }
    }

    private void forwardShortMessageResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        ForwardShortMessageResponseImpl forwardShortMessageResponseIndication = new ForwardShortMessageResponseImpl();

        forwardShortMessageResponseIndication.setInvokeId(invokeId);
        forwardShortMessageResponseIndication.setMAPDialog(mapDialogImpl);
        forwardShortMessageResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {

            try {
                serLis.onMAPMessage(forwardShortMessageResponseIndication);
                ((MAPServiceSmsListener) serLis).onForwardShortMessageResponse(forwardShortMessageResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing ForwardShortMessageResponse: " + e.getMessage(), e);
            }
        }
    }

    private void moForwardShortMessageRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding moForwardShortMessageRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        MoForwardShortMessageRequestImpl moForwardShortMessageRequestIndication = new MoForwardShortMessageRequestImpl();
        moForwardShortMessageRequestIndication.decodeData(ais, buf.length);

        moForwardShortMessageRequestIndication.setInvokeId(invokeId);
        moForwardShortMessageRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(moForwardShortMessageRequestIndication);
                ((MAPServiceSmsListener) serLis).onMoForwardShortMessageRequest(moForwardShortMessageRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onMoForwardShortMessageIndication: " + e.getMessage(), e);
            }
        }
    }

    private void moForwardShortMessageResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        MoForwardShortMessageResponseImpl moForwardShortMessageResponseIndication = new MoForwardShortMessageResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding moForwardShortMessageResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            moForwardShortMessageResponseIndication.decodeData(ais, buf.length);
        }

        moForwardShortMessageResponseIndication.setInvokeId(invokeId);
        moForwardShortMessageResponseIndication.setMAPDialog(mapDialogImpl);
        moForwardShortMessageResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(moForwardShortMessageResponseIndication);
                ((MAPServiceSmsListener) serLis).onMoForwardShortMessageResponse(moForwardShortMessageResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onMoForwardShortMessageRespIndication: " + e.getMessage(), e);
            }
        }
    }

    private void mtForwardShortMessageRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding mtForwardShortMessageRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding mtForwardShortMessageRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        MtForwardShortMessageRequestImpl mtForwardShortMessageRequestIndication = new MtForwardShortMessageRequestImpl();
        mtForwardShortMessageRequestIndication.decodeData(ais, buf.length);

        mtForwardShortMessageRequestIndication.setInvokeId(invokeId);
        mtForwardShortMessageRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(mtForwardShortMessageRequestIndication);
                ((MAPServiceSmsListener) serLis).onMtForwardShortMessageRequest(mtForwardShortMessageRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onMtForwardShortMessageIndication: " + e.getMessage(), e);
            }
        }
    }

    private void mtForwardShortMessageResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        MtForwardShortMessageResponseImpl mtForwardShortMessageResponseIndication = new MtForwardShortMessageResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding mtForwardShortMessageResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            mtForwardShortMessageResponseIndication.decodeData(ais, buf.length);
        }

        mtForwardShortMessageResponseIndication.setInvokeId(invokeId);
        mtForwardShortMessageResponseIndication.setMAPDialog(mapDialogImpl);
        mtForwardShortMessageResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(mtForwardShortMessageResponseIndication);
                ((MAPServiceSmsListener) serLis).onMtForwardShortMessageResponse(mtForwardShortMessageResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onMtForwardShortMessageRespIndication: " + e.getMessage(), e);
            }
        }
    }

    private void sendRoutingInfoForSMRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendRoutingInfoForSMRequestImpl sendRoutingInfoForSMRequestIndication = new SendRoutingInfoForSMRequestImpl();
        sendRoutingInfoForSMRequestIndication.decodeData(ais, buf.length);

        sendRoutingInfoForSMRequestIndication.setInvokeId(invokeId);
        sendRoutingInfoForSMRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInfoForSMRequestIndication);
                ((MAPServiceSmsListener) serLis).onSendRoutingInfoForSMRequest(sendRoutingInfoForSMRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onSendRoutingInfoForSMIndication: " + e.getMessage(), e);
            }
        }
    }

    private void sendRoutingInfoForSMResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        SendRoutingInfoForSMResponseImpl sendRoutingInfoForSMResponseIndication = new SendRoutingInfoForSMResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding sendRoutingInfoForSMResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding sendRoutingInfoForSMResponse: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        sendRoutingInfoForSMResponseIndication.decodeData(ais, buf.length);

        sendRoutingInfoForSMResponseIndication.setInvokeId(invokeId);
        sendRoutingInfoForSMResponseIndication.setMAPDialog(mapDialogImpl);
        sendRoutingInfoForSMResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInfoForSMResponseIndication);
                ((MAPServiceSmsListener) serLis).onSendRoutingInfoForSMResponse(sendRoutingInfoForSMResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onSendRoutingInfoForSMRespIndication: " + e.getMessage(), e);
            }
        }
    }

    private void reportSMDeliveryStatusRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForSMRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ReportSMDeliveryStatusRequestImpl reportSMDeliveryStatusRequestIndication = new ReportSMDeliveryStatusRequestImpl(mapDialogImpl.getApplicationContext()
                .getApplicationContextVersion().getVersion());
        reportSMDeliveryStatusRequestIndication.decodeData(ais, buf.length);

        reportSMDeliveryStatusRequestIndication.setInvokeId(invokeId);
        reportSMDeliveryStatusRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(reportSMDeliveryStatusRequestIndication);
                ((MAPServiceSmsListener) serLis).onReportSMDeliveryStatusRequest(reportSMDeliveryStatusRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onReportSMDeliveryStatusIndication: " + e.getMessage(), e);
            }
        }
    }

    private void reportSMDeliveryStatusResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            long mapProtocolVersion, boolean returnResultNotLast) throws MAPParsingComponentException {

        ReportSMDeliveryStatusResponseImpl reportSMDeliveryStatusResponseIndication = new ReportSMDeliveryStatusResponseImpl(mapProtocolVersion);

        if (parameter != null) {
            if (mapProtocolVersion >= 3) {
                if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                        || parameter.isPrimitive())
                    throw new MAPParsingComponentException(
                            "Error while decoding reportSMDeliveryStatusResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
            } else {
                if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                        || !parameter.isPrimitive())
                    throw new MAPParsingComponentException(
                            "Error while decoding reportSMDeliveryStatusResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
            }

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            reportSMDeliveryStatusResponseIndication.decodeData(ais, buf.length);
        }

        reportSMDeliveryStatusResponseIndication.setInvokeId(invokeId);
        reportSMDeliveryStatusResponseIndication.setMAPDialog(mapDialogImpl);
        reportSMDeliveryStatusResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(reportSMDeliveryStatusResponseIndication);
                ((MAPServiceSmsListener) serLis).onReportSMDeliveryStatusResponse(reportSMDeliveryStatusResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onReportSMDeliveryStatusRespIndication: " + e.getMessage(), e);
            }
        }
    }

    private void informServiceCentreRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding informServiceCentreRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding informServiceCentreRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        InformServiceCentreRequestImpl informServiceCentreRequestIndication = new InformServiceCentreRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        informServiceCentreRequestIndication.decodeData(ais, buf.length);

        informServiceCentreRequestIndication.setInvokeId(invokeId);
        informServiceCentreRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(informServiceCentreRequestIndication);
                ((MAPServiceSmsListener) serLis).onInformServiceCentreRequest(informServiceCentreRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onInformServiceCentreIndication: " + e.getMessage(), e);
            }
        }
    }

    private void alertServiceCentreRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId, int operationCode)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding alertServiceCentreRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding alertServiceCentreRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        AlertServiceCentreRequestImpl alertServiceCentreRequestIndication = new AlertServiceCentreRequestImpl(operationCode);
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        alertServiceCentreRequestIndication.decodeData(ais, buf.length);

        alertServiceCentreRequestIndication.setInvokeId(invokeId);
        alertServiceCentreRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(alertServiceCentreRequestIndication);
                ((MAPServiceSmsListener) serLis).onAlertServiceCentreRequest(alertServiceCentreRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onAlertServiceCentreIndication: " + e.getMessage(), e);
            }
        }
    }

    private void alertServiceCentreResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        AlertServiceCentreResponseImpl alertServiceCentreResponseIndication = new AlertServiceCentreResponseImpl();

        alertServiceCentreResponseIndication.setInvokeId(invokeId);
        alertServiceCentreResponseIndication.setMAPDialog(mapDialogImpl);
        alertServiceCentreResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {

            try {
                serLis.onMAPMessage(alertServiceCentreResponseIndication);
                ((MAPServiceSmsListener) serLis).onAlertServiceCentreResponse(alertServiceCentreResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onAlertServiceCentreRespIndication: " + e.getMessage(), e);
            }

        }
    }

    private void readyForSMRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId, int operationCode)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding readyForSMRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding readyForSMRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        ReadyForSMRequestImpl readyForSMRequestIndication = new ReadyForSMRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        readyForSMRequestIndication.decodeData(ais, buf.length);

        readyForSMRequestIndication.setInvokeId(invokeId);
        readyForSMRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(readyForSMRequestIndication);
                ((MAPServiceSmsListener) serLis).onReadyForSMRequest(readyForSMRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onReadyForSMRequest: " + e.getMessage(), e);
            }
        }
    }

    private void readyForSMResponse(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        ReadyForSMResponseImpl readyForSMResponseIndication = new ReadyForSMResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding readyForSMResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            readyForSMResponseIndication.decodeData(ais, buf.length);
        }

        readyForSMResponseIndication.setInvokeId(invokeId);
        readyForSMResponseIndication.setMAPDialog(mapDialogImpl);
        readyForSMResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(readyForSMResponseIndication);
                ((MAPServiceSmsListener) serLis).onReadyForSMResponse(readyForSMResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onReadyForSMResponse: " + e.getMessage(), e);
            }
        }
    }

    private void noteSubscriberPresentRequest(Parameter parameter, MAPDialogSmsImpl mapDialogImpl, Long invokeId, int operationCode)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding noteSubscriberPresentRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding noteSubscriberPresentRequest: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        NoteSubscriberPresentRequestImpl noteSubscriberPresentRequestIndication = new NoteSubscriberPresentRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        noteSubscriberPresentRequestIndication.decodeData(ais, buf.length);

        noteSubscriberPresentRequestIndication.setInvokeId(invokeId);
        noteSubscriberPresentRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(noteSubscriberPresentRequestIndication);
                ((MAPServiceSmsListener) serLis).onNoteSubscriberPresentRequest(noteSubscriberPresentRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onNoteSubscriberPresentRequest: " + e.getMessage(), e);
            }
        }
    }

}
