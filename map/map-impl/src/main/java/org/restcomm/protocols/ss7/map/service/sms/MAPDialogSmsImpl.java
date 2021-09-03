
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertReason;
import org.restcomm.protocols.ss7.map.api.service.sms.CorrelationID;
import org.restcomm.protocols.ss7.map.api.service.sms.IpSmGwGuidance;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MWStatus;
import org.restcomm.protocols.ss7.map.api.service.sms.SMDeliveryNotIntended;
import org.restcomm.protocols.ss7.map.api.service.sms.SMDeliveryOutcome;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_MTI;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_SMEA;
import org.restcomm.protocols.ss7.map.api.service.sms.SmsSignalInfo;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPDialogSmsImpl extends MAPDialogImpl implements MAPDialogSms {

    protected MAPDialogSmsImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog, MAPProviderImpl mapProviderImpl,
            MAPServiceSms mapService, AddressString origReference, AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }

    public Long addForwardShortMessageRequest(SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA, SmsSignalInfo sm_RP_UI, boolean moreMessagesToSend) throws MAPException {
        return addForwardShortMessageRequest(_Timer_Default, sm_RP_DA, sm_RP_OA, sm_RP_UI, moreMessagesToSend);
    }

    public Long addForwardShortMessageRequest(int customInvokeTimeout, SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA,
            SmsSignalInfo sm_RP_UI, boolean moreMessagesToSend) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMORelayContext && this.mapApplicationContext
                .getApplicationContextName() != MAPApplicationContextName.shortMsgMTRelayContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for addForwardShortMessageRequest: must be shortMsgMORelayContext_V1 or V2 or shortMsgMTRelayContext_V1 or V2");

        if (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            moreMessagesToSend = false;

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.mo_forwardSM);
        invoke.setOperationCode(operationCode);

        ForwardShortMessageRequestImpl forwardShortMessageRequest = new ForwardShortMessageRequestImpl(sm_RP_DA, sm_RP_OA, sm_RP_UI,
                moreMessagesToSend);
        AsnOutputStream aos = new AsnOutputStream();
        forwardShortMessageRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(forwardShortMessageRequest.getTagClass());
        parameter.setPrimitive(forwardShortMessageRequest.getIsPrimitive());
        parameter.setTag(forwardShortMessageRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    public void addForwardShortMessageResponse(long invokeId) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMORelayContext && this.mapApplicationContext
                .getApplicationContextName() != MAPApplicationContextName.shortMsgMTRelayContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for addForwardShortMessageResponse: must be shortMsgMORelayContext_V1 or V2 or shortMsgMTRelayContext_V1 or V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addMoForwardShortMessageRequest(SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA, SmsSignalInfo sm_RP_UI,
            MAPExtensionContainer extensionContainer, IMSI imsi) throws MAPException {
        return addMoForwardShortMessageRequest(_Timer_Default, sm_RP_DA, sm_RP_OA, sm_RP_UI, extensionContainer, imsi);
    }

    public Long addMoForwardShortMessageRequest(int customInvokeTimeout, SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA,
            SmsSignalInfo sm_RP_UI, MAPExtensionContainer extensionContainer, IMSI imsi) throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMORelayContext
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException("Bad application context name for addMoForwardShortMessageRequest: must be shortMsgMORelayContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.mo_forwardSM);
        invoke.setOperationCode(operationCode);

        MoForwardShortMessageRequestImpl moForwardShortMessageRequest = new MoForwardShortMessageRequestImpl(sm_RP_DA, sm_RP_OA, sm_RP_UI,
                extensionContainer, imsi);
        AsnOutputStream aos = new AsnOutputStream();
        moForwardShortMessageRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(moForwardShortMessageRequest.getTagClass());
        parameter.setPrimitive(moForwardShortMessageRequest.getIsPrimitive());
        parameter.setTag(moForwardShortMessageRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    public void addMoForwardShortMessageResponse(long invokeId, SmsSignalInfo sm_RP_UI, MAPExtensionContainer extensionContainer)
            throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMORelayContext
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException("Bad application context name for addMoForwardShortMessageResponse: must be shortMsgMORelayContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.mo_forwardSM);
        resultLast.setOperationCode(operationCode);

        if (sm_RP_UI != null || extensionContainer != null) {

            MoForwardShortMessageResponseImpl moForwardShortMessageResponse = new MoForwardShortMessageResponseImpl(sm_RP_UI, extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            moForwardShortMessageResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(moForwardShortMessageResponse.getTagClass());
            parameter.setPrimitive(moForwardShortMessageResponse.getIsPrimitive());
            parameter.setTag(moForwardShortMessageResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addMtForwardShortMessageRequest(SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA, SmsSignalInfo sm_RP_UI,
            boolean moreMessagesToSend, MAPExtensionContainer extensionContainer) throws MAPException {
        return this.addMtForwardShortMessageRequest(_Timer_Default, sm_RP_DA, sm_RP_OA, sm_RP_UI, moreMessagesToSend,
                extensionContainer);
    }

    public Long addMtForwardShortMessageRequest(int customInvokeTimeout, SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA,
            SmsSignalInfo sm_RP_UI, boolean moreMessagesToSend, MAPExtensionContainer extensionContainer) throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMTRelayContext
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException("Bad application context name for addMtForwardShortMessageRequest: must be shortMsgMTRelayContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        try {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.mt_forwardSM);
            invoke.setOperationCode(operationCode);

            MtForwardShortMessageRequestImpl mtForwardShortMessageRequest = new MtForwardShortMessageRequestImpl(sm_RP_DA, sm_RP_OA, sm_RP_UI,
                    moreMessagesToSend, extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            mtForwardShortMessageRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(mtForwardShortMessageRequest.getTagClass());
            parameter.setPrimitive(mtForwardShortMessageRequest.getIsPrimitive());
            parameter.setTag(mtForwardShortMessageRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;

        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    public void addMtForwardShortMessageResponse(long invokeId, SmsSignalInfo sm_RP_UI, MAPExtensionContainer extensionContainer)
            throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgMTRelayContext
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException("Bad application context name for addMtForwardShortMessageResponse: must be shortMsgMTRelayContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.mt_forwardSM);
        resultLast.setOperationCode(operationCode);

        if (sm_RP_UI != null || extensionContainer != null) {

            MtForwardShortMessageResponseImpl mtForwardShortMessageResponse = new MtForwardShortMessageResponseImpl(sm_RP_UI, extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            mtForwardShortMessageResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(mtForwardShortMessageResponse.getTagClass());
            parameter.setPrimitive(mtForwardShortMessageResponse.getIsPrimitive());
            parameter.setTag(mtForwardShortMessageResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addSendRoutingInfoForSMRequest(ISDNAddressString msisdn, boolean sm_RP_PRI, AddressString serviceCentreAddress,
            MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator, SM_RP_MTI sM_RP_MTI, SM_RP_SMEA sM_RP_SMEA,
            SMDeliveryNotIntended smDeliveryNotIntended, boolean ipSmGwGuidanceIndicator, IMSI imsi, boolean t4TriggerIndicator,
            boolean singleAttemptDelivery, TeleserviceCode teleserviceCode, CorrelationID correlationId) throws MAPException {
        return this.addSendRoutingInfoForSMRequest(_Timer_Default, msisdn, sm_RP_PRI, serviceCentreAddress, extensionContainer,
                gprsSupportIndicator, sM_RP_MTI, sM_RP_SMEA, smDeliveryNotIntended, ipSmGwGuidanceIndicator, imsi,
                t4TriggerIndicator, singleAttemptDelivery, teleserviceCode, correlationId);
    }

    public Long addSendRoutingInfoForSMRequest(int customInvokeTimeout, ISDNAddressString msisdn, boolean sm_RP_PRI,
            AddressString serviceCentreAddress, MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator,
            SM_RP_MTI sM_RP_MTI, SM_RP_SMEA sM_RP_SMEA, SMDeliveryNotIntended smDeliveryNotIntended,
            boolean ipSmGwGuidanceIndicator, IMSI imsi, boolean t4TriggerIndicator, boolean singleAttemptDelivery,
            TeleserviceCode teleserviceCode, CorrelationID correlationId) throws MAPException {

        MAPApplicationContextVersion vers = this.mapApplicationContext.getApplicationContextVersion();
        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgGatewayContext
                || (vers != MAPApplicationContextVersion.version1 && vers != MAPApplicationContextVersion.version2 && vers != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addSendRoutingInfoForSMRequest: must be shortMsgGatewayContext_V1, V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForSM);
        invoke.setOperationCode(operationCode);

        try {
            SendRoutingInfoForSMRequestImpl sendRoutingInfoForSMRequest = new SendRoutingInfoForSMRequestImpl(msisdn, sm_RP_PRI, serviceCentreAddress,
                    extensionContainer, gprsSupportIndicator, sM_RP_MTI, sM_RP_SMEA, smDeliveryNotIntended,
                    ipSmGwGuidanceIndicator, imsi, t4TriggerIndicator, singleAttemptDelivery, teleserviceCode, correlationId);
            AsnOutputStream aos = new AsnOutputStream();
            sendRoutingInfoForSMRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(sendRoutingInfoForSMRequest.getTagClass());
            parameter.setPrimitive(sendRoutingInfoForSMRequest.getIsPrimitive());
            parameter.setTag(sendRoutingInfoForSMRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;

        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    public void addSendRoutingInfoForSMResponse(long invokeId, IMSI imsi, LocationInfoWithLMSI locationInfoWithLMSI,
            MAPExtensionContainer extensionContainer, Boolean mwdSet, IpSmGwGuidance ipSmGwGuidance) throws MAPException {

        MAPApplicationContextVersion mapApplicationContextVersion = this.mapApplicationContext.getApplicationContextVersion();
        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgGatewayContext
                || (mapApplicationContextVersion != MAPApplicationContextVersion.version1 && mapApplicationContextVersion != MAPApplicationContextVersion.version2 && mapApplicationContextVersion != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addSendRoutingInfoForSMResponse: must be shortMsgGatewayContext_V1, V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForSM);
        resultLast.setOperationCode(operationCode);

        SendRoutingInfoForSMResponseImpl sendRoutingInfoForSMResponse = new SendRoutingInfoForSMResponseImpl(imsi, locationInfoWithLMSI,
                extensionContainer, mwdSet, ipSmGwGuidance);
        AsnOutputStream aos = new AsnOutputStream();
        sendRoutingInfoForSMResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendRoutingInfoForSMResponse.getTagClass());
        parameter.setPrimitive(sendRoutingInfoForSMResponse.getIsPrimitive());
        parameter.setTag(sendRoutingInfoForSMResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addReportSMDeliveryStatusRequest(ISDNAddressString msisdn, AddressString serviceCentreAddress,
            SMDeliveryOutcome sMDeliveryOutcome, Integer absentSubscriberDiagnosticSM,
            MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator, boolean deliveryOutcomeIndicator,
            SMDeliveryOutcome additionalSMDeliveryOutcome, Integer additionalAbsentSubscriberDiagnosticSM) throws MAPException {
        return this.addReportSMDeliveryStatusRequest(_Timer_Default, msisdn, serviceCentreAddress, sMDeliveryOutcome,
                absentSubscriberDiagnosticSM, extensionContainer, gprsSupportIndicator, deliveryOutcomeIndicator,
                additionalSMDeliveryOutcome, additionalAbsentSubscriberDiagnosticSM);
    }

    public Long addReportSMDeliveryStatusRequest(int customInvokeTimeout, ISDNAddressString msisdn,
            AddressString serviceCentreAddress, SMDeliveryOutcome sMDeliveryOutcome, Integer absentSubscriberDiagnosticSM,
            MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator, boolean deliveryOutcomeIndicator,
            SMDeliveryOutcome additionalSMDeliveryOutcome, Integer additionalAbsentSubscriberDiagnosticSM) throws MAPException {

        MAPApplicationContextVersion mapApplicationContextVersion = this.mapApplicationContext.getApplicationContextVersion();
        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgGatewayContext
                || (mapApplicationContextVersion != MAPApplicationContextVersion.version1 && mapApplicationContextVersion != MAPApplicationContextVersion.version2 && mapApplicationContextVersion != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addReportSMDeliveryStatusRequest: must be shortMsgGatewayContext_V1, V2 or V3");

        if (msisdn == null || serviceCentreAddress == null
                || (mapApplicationContextVersion != MAPApplicationContextVersion.version1 && sMDeliveryOutcome == null))
            throw new MAPException("msisdn, serviceCentreAddress and sMDeliveryOutcome must not be null");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        try {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.reportSM_DeliveryStatus);
            invoke.setOperationCode(operationCode);

            ReportSMDeliveryStatusRequestImpl reportSMDeliveryStatusRequest = new ReportSMDeliveryStatusRequestImpl(this.getApplicationContext()
                    .getApplicationContextVersion().getVersion(), msisdn, serviceCentreAddress, sMDeliveryOutcome,
                    absentSubscriberDiagnosticSM, extensionContainer, gprsSupportIndicator, deliveryOutcomeIndicator,
                    additionalSMDeliveryOutcome, additionalAbsentSubscriberDiagnosticSM);
            AsnOutputStream aos = new AsnOutputStream();
            reportSMDeliveryStatusRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(reportSMDeliveryStatusRequest.getTagClass());
            parameter.setPrimitive(reportSMDeliveryStatusRequest.getIsPrimitive());
            parameter.setTag(reportSMDeliveryStatusRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;

        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    public void addReportSMDeliveryStatusResponse(long invokeId, ISDNAddressString storedMSISDN, MAPExtensionContainer extensionContainer) throws MAPException {

        MAPApplicationContextVersion mapApplicationContextVersion = this.mapApplicationContext.getApplicationContextVersion();
        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgGatewayContext
                || (mapApplicationContextVersion != MAPApplicationContextVersion.version1 && mapApplicationContextVersion != MAPApplicationContextVersion.version2 && mapApplicationContextVersion != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addReportSMDeliveryStatusResponse: must be shortMsgGatewayContext_V1, V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.reportSM_DeliveryStatus);
        resultLast.setOperationCode(operationCode);

        if (mapApplicationContextVersion.getVersion() == 3 && (storedMSISDN != null || extensionContainer != null) || mapApplicationContextVersion.getVersion() == 2
                && storedMSISDN != null) {
            ReportSMDeliveryStatusResponseImpl reportSMDeliveryStatusResponse = new ReportSMDeliveryStatusResponseImpl(mapApplicationContextVersion.getVersion(), storedMSISDN,
                    extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            reportSMDeliveryStatusResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(reportSMDeliveryStatusResponse.getTagClass());
            parameter.setPrimitive(reportSMDeliveryStatusResponse.getIsPrimitive());
            parameter.setTag(reportSMDeliveryStatusResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addInformServiceCentreRequest(ISDNAddressString storedMSISDN, MWStatus mwStatus,
            MAPExtensionContainer extensionContainer, Integer absentSubscriberDiagnosticSM,
            Integer additionalAbsentSubscriberDiagnosticSM) throws MAPException {
        return this.addInformServiceCentreRequest(_Timer_Default, storedMSISDN, mwStatus, extensionContainer,
                absentSubscriberDiagnosticSM, additionalAbsentSubscriberDiagnosticSM);
    }

    public Long addInformServiceCentreRequest(int customInvokeTimeout, ISDNAddressString storedMSISDN, MWStatus mwStatus,
            MAPExtensionContainer extensionContainer, Integer absentSubscriberDiagnosticSM,
            Integer additionalAbsentSubscriberDiagnosticSM) throws MAPException {

        MAPApplicationContextVersion mapApplicationContextVersion = this.mapApplicationContext.getApplicationContextVersion();
        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgGatewayContext
                || (mapApplicationContextVersion != MAPApplicationContextVersion.version2 && mapApplicationContextVersion != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addInformServiceCentreRequest: must be shortMsgGatewayContext_V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        try {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.informServiceCentre);
            invoke.setOperationCode(operationCode);

            InformServiceCentreRequestImpl informServiceCentreRequest = new InformServiceCentreRequestImpl(storedMSISDN, mwStatus, extensionContainer,
                    absentSubscriberDiagnosticSM, additionalAbsentSubscriberDiagnosticSM);
            AsnOutputStream aos = new AsnOutputStream();
            informServiceCentreRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(informServiceCentreRequest.getTagClass());
            parameter.setPrimitive(informServiceCentreRequest.getIsPrimitive());
            parameter.setTag(informServiceCentreRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;

        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    public Long addAlertServiceCentreRequest(ISDNAddressString msisdn, AddressString serviceCentreAddress) throws MAPException {
        return this.addAlertServiceCentreRequest(_Timer_Default, msisdn, serviceCentreAddress);
    }

    public Long addAlertServiceCentreRequest(int customInvokeTimeout, ISDNAddressString msisdn,
            AddressString serviceCentreAddress) throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgAlertContext
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for addAlertServiceCentreRequest: must be shortMsgAlertContext_V1 or V2");

        Invoke invoke;
        if (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1)
            invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                    .createTCInvokeRequest(InvokeClass.Class4);
        else
            invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        try {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            if (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1)
                operationCode.setLocalOperationCode((long) MAPOperationCode.alertServiceCentreWithoutResult);
            else
                operationCode.setLocalOperationCode((long) MAPOperationCode.alertServiceCentre);
            invoke.setOperationCode(operationCode);

            AlertServiceCentreRequestImpl alertServiceCentreRequest = new AlertServiceCentreRequestImpl(msisdn, serviceCentreAddress);
            AsnOutputStream aos = new AsnOutputStream();
            alertServiceCentreRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(alertServiceCentreRequest.getTagClass());
            parameter.setPrimitive(alertServiceCentreRequest.getIsPrimitive());
            parameter.setTag(alertServiceCentreRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;

        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    public void addAlertServiceCentreResponse(long invokeId) throws MAPException {

        if (this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.shortMsgAlertContext
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for addAlertServiceCentreResponse: must be shortMsgAlertContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer
        // OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        // operationCode.setLocalOperationCode((long) MAPOperationCode.alertServiceCentre);
        // resultLast.setOperationCode(operationCode);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addReadyForSMRequest(IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator, MAPExtensionContainer extensionContainer,
            boolean additionalAlertReasonIndicator) throws MAPException {
        return addReadyForSMRequest(_Timer_Default, imsi, alertReason, alertReasonIndicator, extensionContainer, additionalAlertReasonIndicator);
    }

    @Override
    public Long addReadyForSMRequest(int customInvokeTimeout, IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator,
            MAPExtensionContainer extensionContainer, boolean additionalAlertReasonIndicator) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.mwdMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addReadyForSMRequest: must be mwdMngtContext_V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.readyForSM);
        invoke.setOperationCode(operationCode);

        ReadyForSMRequestImpl readyForSMRequest = new ReadyForSMRequestImpl(imsi, alertReason, alertReasonIndicator, extensionContainer, additionalAlertReasonIndicator);
        AsnOutputStream aos = new AsnOutputStream();
        readyForSMRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(readyForSMRequest.getTagClass());
        parameter.setPrimitive(readyForSMRequest.getIsPrimitive());
        parameter.setTag(readyForSMRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addReadyForSMResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.mwdMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for addReadyForSMRequest: must be mwdMngtContext_V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.readyForSM);
        resultLast.setOperationCode(operationCode);

        if (this.mapApplicationContext.getApplicationContextVersion().getVersion() >= 3 || extensionContainer != null) {

            ReadyForSMResponseImpl readyForSMResponse = new ReadyForSMResponseImpl(extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            readyForSMResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(readyForSMResponse.getTagClass());
            parameter.setPrimitive(readyForSMResponse.getIsPrimitive());
            parameter.setTag(readyForSMResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addNoteSubscriberPresentRequest(IMSI imsi) throws MAPException {
        return addNoteSubscriberPresentRequest(_Timer_Default, imsi);
    }

    @Override
    public Long addNoteSubscriberPresentRequest(int customInvokeTimeout, IMSI imsi) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.mwdMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1))
            throw new MAPException("Bad application context name for addNoteSubscriberPresentRequest: must be mwdMngtContext_V1");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.noteSubscriberPresent);
        invoke.setOperationCode(operationCode);

        NoteSubscriberPresentRequestImpl noteSubscriberPresentRequest = new NoteSubscriberPresentRequestImpl(imsi);
        AsnOutputStream aos = new AsnOutputStream();
        noteSubscriberPresentRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(noteSubscriberPresentRequest.getTagClass());
        parameter.setPrimitive(noteSubscriberPresentRequest.getIsPrimitive());
        parameter.setTag(noteSubscriberPresentRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }
}
