
package org.restcomm.protocols.ss7.cap.service.sms;

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.CAPDialogImpl;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventSpecificInformationSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FCIBCCCAMELsequence1SMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.RPCause;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSAddressString;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSEvent;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPDataCodingScheme;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPProtocolIdentifier;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPShortMessageSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPValidityPeriod;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPDialogSmsImpl extends CAPDialogImpl implements CAPDialogSms {

    protected CAPDialogSmsImpl(CAPApplicationContext appCntx, Dialog tcapDialog, CAPProviderImpl capProviderImpl, CAPServiceBase capService) {
        super(appCntx, tcapDialog, capProviderImpl, capService);
    }

    @Override
    public Long addConnectSMSRequest(SMSAddressString callingPartysNumber, CalledPartyBCDNumber destinationSubscriberNumber,
            ISDNAddressString smscAddress, CAPExtensions extensions) throws CAPException {
        return addConnectSMSRequest(_Timer_Default, callingPartysNumber, destinationSubscriberNumber, smscAddress,
                extensions);
    }

    @Override
    public Long addConnectSMSRequest(int customInvokeTimeout, SMSAddressString callingPartysNumber,
            CalledPartyBCDNumber destinationSubscriberNumber, ISDNAddressString smscAddress, CAPExtensions extensions)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.connectSMS);
        invoke.setOperationCode(operationCode);

        ConnectSMSRequestImpl connectSMSRequest = new ConnectSMSRequestImpl(callingPartysNumber, destinationSubscriberNumber,
                smscAddress, extensions);

        AsnOutputStream aos = new AsnOutputStream();
        connectSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(connectSMSRequest.getTagClass());
        parameter.setPrimitive(connectSMSRequest.getIsPrimitive());
        parameter.setTag(connectSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addEventReportSMSRequest(EventTypeSMS eventTypeSMS, EventSpecificInformationSMS eventSpecificInformationSMS,
            MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {
        return this.addEventReportSMSRequest(_Timer_Default, eventTypeSMS, eventSpecificInformationSMS, miscCallInfo, extensions);
    }

    @Override
    public Long addEventReportSMSRequest(int customInvokeTimeout, EventTypeSMS eventTypeSMS,
            EventSpecificInformationSMS eventSpecificInformationSMS, MiscCallInfo miscCallInfo, CAPExtensions extensions)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.eventReportSMS);
        invoke.setOperationCode(operationCode);

        EventReportSMSRequestImpl eventReportSMSRequest = new EventReportSMSRequestImpl(eventTypeSMS, eventSpecificInformationSMS,
                miscCallInfo, extensions);

        AsnOutputStream aos = new AsnOutputStream();
        eventReportSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(eventReportSMSRequest.getTagClass());
        parameter.setPrimitive(eventReportSMSRequest.getIsPrimitive());
        parameter.setTag(eventReportSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addFurnishChargingInformationSMSRequest(FCIBCCCAMELsequence1SMS fciBCCCAMELsequence1) throws CAPException {
        return this.addFurnishChargingInformationSMSRequest(_Timer_Default, fciBCCCAMELsequence1);
    }

    @Override
    public Long addFurnishChargingInformationSMSRequest(int customInvokeTimeout, FCIBCCCAMELsequence1SMS fciBCCCAMELsequence1) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.furnishChargingInformationSMS);
        invoke.setOperationCode(operationCode);

        FurnishChargingInformationSMSRequestImpl furnishChargingInformationSMSRequest = new FurnishChargingInformationSMSRequestImpl(fciBCCCAMELsequence1);
        AsnOutputStream aos = new AsnOutputStream();
        furnishChargingInformationSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(furnishChargingInformationSMSRequest.getTagClass());
        parameter.setPrimitive(furnishChargingInformationSMSRequest.getIsPrimitive());
        parameter.setTag(furnishChargingInformationSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addInitialDPSMSRequest(int serviceKey, CalledPartyBCDNumber destinationSubscriberNumber,
            SMSAddressString callingPartyNumber, EventTypeSMS eventTypeSMS, IMSI imsi,
            LocationInformation locationInformationMSC, LocationInformationGPRS locationInformationGPRS,
            ISDNAddressString smscCAddress, TimeAndTimezone timeAndTimezone,
            TPShortMessageSpecificInfo tPShortMessageSpecificInfo, TPProtocolIdentifier tPProtocolIdentifier,
            TPDataCodingScheme tPDataCodingScheme, TPValidityPeriod tPValidityPeriod, CAPExtensions extensions,
            CallReferenceNumber smsReferenceNumber, ISDNAddressString mscAddress, ISDNAddressString sgsnNumber,
            MSClassmark2 mSClassmark2, GPRSMSClass gprsMSClass, IMEI imei, ISDNAddressString calledPartyNumber)
            throws CAPException {
        return this.addInitialDPSMSRequest(_Timer_Default, serviceKey, destinationSubscriberNumber, callingPartyNumber,
                eventTypeSMS, imsi, locationInformationMSC, locationInformationGPRS, smscCAddress, timeAndTimezone,
                tPShortMessageSpecificInfo, tPProtocolIdentifier, tPDataCodingScheme, tPValidityPeriod, extensions,
                smsReferenceNumber, mscAddress, sgsnNumber, mSClassmark2, gprsMSClass, imei, calledPartyNumber);
    }

    @Override
    public Long addInitialDPSMSRequest(int customInvokeTimeout, int serviceKey,
            CalledPartyBCDNumber destinationSubscriberNumber, SMSAddressString callingPartyNumber,
            EventTypeSMS eventTypeSMS, IMSI imsi, LocationInformation locationInformationMSC,
            LocationInformationGPRS locationInformationGPRS, ISDNAddressString smscCAddress,
            TimeAndTimezone timeAndTimezone, TPShortMessageSpecificInfo tPShortMessageSpecificInfo,
            TPProtocolIdentifier tPProtocolIdentifier, TPDataCodingScheme tPDataCodingScheme,
            TPValidityPeriod tPValidityPeriod, CAPExtensions extensions, CallReferenceNumber smsReferenceNumber,
            ISDNAddressString mscAddress, ISDNAddressString sgsnNumber, MSClassmark2 mSClassmark2,
            GPRSMSClass gprsMSClass, IMEI imei, ISDNAddressString calledPartyNumber) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.initialDPSMS);
        invoke.setOperationCode(operationCode);

        InitialDPSMSRequestImpl initialDPSMSRequest = new InitialDPSMSRequestImpl(serviceKey, destinationSubscriberNumber,
                callingPartyNumber, eventTypeSMS, imsi, locationInformationMSC, locationInformationGPRS, smscCAddress,
                timeAndTimezone, tPShortMessageSpecificInfo, tPProtocolIdentifier, tPDataCodingScheme,
                tPValidityPeriod, extensions, smsReferenceNumber, mscAddress, sgsnNumber, mSClassmark2, gprsMSClass,
                imei, calledPartyNumber);

        AsnOutputStream aos = new AsnOutputStream();
        initialDPSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(initialDPSMSRequest.getTagClass());
        parameter.setPrimitive(initialDPSMSRequest.getIsPrimitive());
        parameter.setTag(initialDPSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addReleaseSMSRequest(RPCause rpCause) throws CAPException {
        return this.addReleaseSMSRequest(_Timer_Default, rpCause);
    }

    @Override
    public Long addReleaseSMSRequest(int customInvokeTimeout, RPCause rpCause) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.releaseSMS);
        invoke.setOperationCode(operationCode);

        ReleaseSMSRequestImpl releaseSMSRequest = new ReleaseSMSRequestImpl(rpCause);

        AsnOutputStream aos = new AsnOutputStream();
        releaseSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(releaseSMSRequest.getTagClass());
        parameter.setPrimitive(releaseSMSRequest.getIsPrimitive());
        parameter.setTag(releaseSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addRequestReportSMSEventRequest(ArrayList<SMSEvent> smsEvents, CAPExtensions extensions)
            throws CAPException {
        return this.addRequestReportSMSEventRequest(_Timer_Default, smsEvents, extensions);
    }

    @Override
    public Long addRequestReportSMSEventRequest(int customInvokeTimeout, ArrayList<SMSEvent> smsEvents,
            CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.requestReportSMSEvent);
        invoke.setOperationCode(operationCode);

        RequestReportSMSEventRequestImpl requestReportSMSEventRequest = new RequestReportSMSEventRequestImpl(smsEvents, extensions);

        AsnOutputStream aos = new AsnOutputStream();
        requestReportSMSEventRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(requestReportSMSEventRequest.getTagClass());
        parameter.setPrimitive(requestReportSMSEventRequest.getIsPrimitive());
        parameter.setTag(requestReportSMSEventRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addResetTimerSMSRequest(TimerID timerID, int timerValue, CAPExtensions extensions) throws CAPException {
        return this.addResetTimerSMSRequest(_Timer_Default, timerID, timerValue, extensions);
    }

    @Override
    public Long addResetTimerSMSRequest(int customInvokeTimeout, TimerID timerID, int timerValue,
            CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.resetTimerSMS);
        invoke.setOperationCode(operationCode);

        ResetTimerSMSRequestImpl resetTimerSMSRequest = new ResetTimerSMSRequestImpl(timerID, timerValue, extensions);

        AsnOutputStream aos = new AsnOutputStream();
        resetTimerSMSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(resetTimerSMSRequest.getTagClass());
        parameter.setPrimitive(resetTimerSMSRequest.getIsPrimitive());
        parameter.setTag(resetTimerSMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addContinueSMSRequest() throws CAPException {

        return addContinueSMSRequest(_Timer_Default);
    }

    @Override
    public Long addContinueSMSRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_cap3_sms && this.capApplicationContext != CAPApplicationContext.CapV4_cap4_sms)
            throw new CAPException("Bad application context name for ConnectSMSRequest: must be CapV3_cap3_sms or CapV4_cap4_sms");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerSmsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.continueSMS);
        invoke.setOperationCode(operationCode);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

}
