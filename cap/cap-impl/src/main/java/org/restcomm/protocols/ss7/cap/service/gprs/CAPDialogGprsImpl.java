package org.restcomm.protocols.ss7.cap.service.gprs;

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.CAPDialogImpl;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPDialogGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELFCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingResult;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPInitiationType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.SGSNCapabilities;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
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
public class CAPDialogGprsImpl extends CAPDialogImpl implements CAPDialogGprs {

    protected CAPDialogGprsImpl(CAPApplicationContext capApplicationContext, Dialog tcapDialog, CAPProviderImpl capProviderImpl, CAPServiceBase capServiceBase) {
        super(capApplicationContext, tcapDialog, capProviderImpl, capServiceBase);
    }

    @Override
    public Long addInitialDpGprsRequest(int serviceKey, GPRSEventType gprsEventType, ISDNAddressString msisdn, IMSI imsi,
            TimeAndTimezone timeAndTimezone, GPRSMSClass gprsMSClass, EndUserAddress endUserAddress,
            QualityOfService qualityOfService, AccessPointName accessPointName, RAIdentity routeingAreaIdentity,
            GPRSChargingID chargingID, SGSNCapabilities sgsnCapabilities, LocationInformationGPRS locationInformationGPRS,
            PDPInitiationType pdpInitiationType, CAPExtensions extensions, GSNAddress gsnAddress, boolean secondaryPDPContext,
            IMEI imei) throws CAPException {
        return addInitialDpGprsRequest(_Timer_Default, serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone, gprsMSClass,
                endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID, sgsnCapabilities,
                locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);
    }

    @Override
    public Long addInitialDpGprsRequest(int customInvokeTimeout, int serviceKey, GPRSEventType gprsEventType,
            ISDNAddressString msisdn, IMSI imsi, TimeAndTimezone timeAndTimezone, GPRSMSClass gprsMSClass,
            EndUserAddress endUserAddress, QualityOfService qualityOfService, AccessPointName accessPointName,
            RAIdentity routeingAreaIdentity, GPRSChargingID chargingID, SGSNCapabilities sgsnCapabilities,
            LocationInformationGPRS locationInformationGPRS, PDPInitiationType pdpInitiationType, CAPExtensions extensions,
            GSNAddress gsnAddress, boolean secondaryPDPContext, IMEI imei) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF)
            throw new CAPException("Bad application context name for InitialDpGprsRequest: must be CapV3_gprsSSF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.initialDPGPRS);
        invoke.setOperationCode(operationCode);

        InitialDpGprsRequestImpl initialDpGprsRequest = new InitialDpGprsRequestImpl(serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone,
                gprsMSClass, endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID,
                sgsnCapabilities, locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);

        AsnOutputStream aos = new AsnOutputStream();
        initialDpGprsRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(initialDpGprsRequest.getTagClass());
        parameter.setPrimitive(initialDpGprsRequest.getIsPrimitive());
        parameter.setTag(initialDpGprsRequest.getTag());
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
    public Long addRequestReportGPRSEventRequest(ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) throws CAPException {
        return addRequestReportGPRSEventRequest(_Timer_Default, gprsEvent, pdpID);
    }

    @Override
    public Long addRequestReportGPRSEventRequest(int customInvokeTimeout, ArrayList<GPRSEvent> gprsEvent, PDPID pdpID)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for RequestReportGPRSEventRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.requestReportGPRSEvent);
        invoke.setOperationCode(operationCode);

        RequestReportGPRSEventRequestImpl requestReportGPRSEventRequest = new RequestReportGPRSEventRequestImpl(gprsEvent, pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        requestReportGPRSEventRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(requestReportGPRSEventRequest.getTagClass());
        parameter.setPrimitive(requestReportGPRSEventRequest.getIsPrimitive());
        parameter.setTag(requestReportGPRSEventRequest.getTag());
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
    public Long addApplyChargingGPRSRequest(ChargingCharacteristics chargingCharacteristics, Integer tariffSwitchInterval,
            PDPID pdpID) throws CAPException {

        return addApplyChargingGPRSRequest(_Timer_Default, chargingCharacteristics, tariffSwitchInterval, pdpID);
    }

    @Override
    public Long addApplyChargingGPRSRequest(int customInvokeTimeout, ChargingCharacteristics chargingCharacteristics,
            Integer tariffSwitchInterval, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ApplyChargingGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.applyChargingGPRS);
        invoke.setOperationCode(operationCode);

        ApplyChargingGPRSRequestImpl applyChargingGPRSRequest = new ApplyChargingGPRSRequestImpl(chargingCharacteristics, tariffSwitchInterval,
                pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        applyChargingGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(applyChargingGPRSRequest.getTagClass());
        parameter.setPrimitive(applyChargingGPRSRequest.getIsPrimitive());
        parameter.setTag(applyChargingGPRSRequest.getTag());
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
    public Long addEntityReleasedGPRSRequest(GPRSCause gprsCause, PDPID pdpID) throws CAPException {

        return addEntityReleasedGPRSRequest(_Timer_Default, gprsCause, pdpID);
    }

    @Override
    public Long addEntityReleasedGPRSRequest(int customInvokeTimeout, GPRSCause gprsCause, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for EntityReleasedGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.entityReleasedGPRS);
        invoke.setOperationCode(operationCode);

        EntityReleasedGPRSRequestImpl entityReleasedGPRSRequest = new EntityReleasedGPRSRequestImpl(gprsCause, pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        entityReleasedGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(entityReleasedGPRSRequest.getTagClass());
        parameter.setPrimitive(entityReleasedGPRSRequest.getIsPrimitive());
        parameter.setTag(entityReleasedGPRSRequest.getTag());
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
    public void addEntityReleasedGPRSResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for EntityReleasedGPRSResponse: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addConnectGPRSRequest(AccessPointName accessPointName, PDPID pdpID) throws CAPException {

        return addConnectGPRSRequest(_Timer_Default, accessPointName, pdpID);
    }

    @Override
    public Long addConnectGPRSRequest(int customInvokeTimeout, AccessPointName accessPointName, PDPID pdpID)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ConnectGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.connectGPRS);
        invoke.setOperationCode(operationCode);

        ConnectGPRSRequestImpl connectGPRSRequest = new ConnectGPRSRequestImpl(accessPointName, pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        connectGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(connectGPRSRequest.getTagClass());
        parameter.setPrimitive(connectGPRSRequest.getIsPrimitive());
        parameter.setTag(connectGPRSRequest.getTag());
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
    public Long addContinueGPRSRequest(PDPID pdpID) throws CAPException {

        return addContinueGPRSRequest(_Timer_Default, pdpID);
    }

    @Override
    public Long addContinueGPRSRequest(int customInvokeTimeout, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ContinueGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.continueGPRS);
        invoke.setOperationCode(operationCode);

        ContinueGPRSRequestImpl continueGPRSRequest = new ContinueGPRSRequestImpl(pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        continueGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(continueGPRSRequest.getTagClass());
        parameter.setPrimitive(continueGPRSRequest.getIsPrimitive());
        parameter.setTag(continueGPRSRequest.getTag());
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
    public Long addReleaseGPRSRequest(GPRSCause gprsCause, PDPID pdpID) throws CAPException {

        return addReleaseGPRSRequest(_Timer_Default, gprsCause, pdpID);
    }

    @Override
    public Long addReleaseGPRSRequest(int customInvokeTimeout, GPRSCause gprsCause, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ReleaseGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.releaseGPRS);
        invoke.setOperationCode(operationCode);

        ReleaseGPRSRequestImpl releaseGPRSRequest = new ReleaseGPRSRequestImpl(gprsCause, pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        releaseGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(releaseGPRSRequest.getTagClass());
        parameter.setPrimitive(releaseGPRSRequest.getIsPrimitive());
        parameter.setTag(releaseGPRSRequest.getTag());
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
    public Long addResetTimerGPRSRequest(TimerID timerID, int timerValue) throws CAPException {

        return addResetTimerGPRSRequest(_Timer_Default, timerID, timerValue);
    }

    @Override
    public Long addResetTimerGPRSRequest(int customInvokeTimeout, TimerID timerID, int timerValue) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ResetTimerGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.resetTimerGPRS);
        invoke.setOperationCode(operationCode);

        ResetTimerGPRSRequestImpl releaseGPRSRequest = new ResetTimerGPRSRequestImpl(timerID, timerValue);

        AsnOutputStream aos = new AsnOutputStream();
        releaseGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(releaseGPRSRequest.getTagClass());
        parameter.setPrimitive(releaseGPRSRequest.getIsPrimitive());
        parameter.setTag(releaseGPRSRequest.getTag());
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
    public Long addFurnishChargingInformationGPRSRequest(CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics) throws CAPException {

        return addFurnishChargingInformationGPRSRequest(_Timer_Default, fciGPRSBillingChargingCharacteristics);
    }

    @Override
    public Long addFurnishChargingInformationGPRSRequest(int customInvokeTimeout,
            CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for FurnishChargingInformationGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.furnishChargingInformationGPRS);
        invoke.setOperationCode(operationCode);

        FurnishChargingInformationGPRSRequestImpl furnishChargingInformationGPRSRequest = new FurnishChargingInformationGPRSRequestImpl(
                fciGPRSBillingChargingCharacteristics);

        AsnOutputStream aos = new AsnOutputStream();
        furnishChargingInformationGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(furnishChargingInformationGPRSRequest.getTagClass());
        parameter.setPrimitive(furnishChargingInformationGPRSRequest.getIsPrimitive());
        parameter.setTag(furnishChargingInformationGPRSRequest.getTag());
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
    public Long addCancelGPRSRequest(PDPID pdpID) throws CAPException {

        return addCancelGPRSRequest(_Timer_Default, pdpID);
    }

    @Override
    public Long addCancelGPRSRequest(int customInvokeTimeout, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException("Bad application context name for CancelGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.cancelGPRS);
        invoke.setOperationCode(operationCode);

        CancelGPRSRequestImpl cancelGPRSRequest = new CancelGPRSRequestImpl(pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        cancelGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(cancelGPRSRequest.getTagClass());
        parameter.setPrimitive(cancelGPRSRequest.getIsPrimitive());
        parameter.setTag(cancelGPRSRequest.getTag());
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
    public Long addSendChargingInformationGPRSRequest(CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics) throws CAPException {

        return addSendChargingInformationGPRSRequest(_Timer_Default, sciGPRSBillingChargingCharacteristics);
    }

    @Override
    public Long addSendChargingInformationGPRSRequest(int customInvokeTimeout,
            CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for SendChargingInformationGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.sendChargingInformationGPRS);
        invoke.setOperationCode(operationCode);

        SendChargingInformationGPRSRequestImpl sendChargingInformationGPRSRequest = new SendChargingInformationGPRSRequestImpl(
                sciGPRSBillingChargingCharacteristics);

        AsnOutputStream aos = new AsnOutputStream();
        sendChargingInformationGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendChargingInformationGPRSRequest.getTagClass());
        parameter.setPrimitive(sendChargingInformationGPRSRequest.getIsPrimitive());
        parameter.setTag(sendChargingInformationGPRSRequest.getTag());
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
    public Long addApplyChargingReportGPRSRequest(ChargingResult chargingResult, QualityOfService qualityOfService,
            boolean active, PDPID pdpID, ChargingRollOver chargingRollOver) throws CAPException {

        return addApplyChargingReportGPRSRequest(_Timer_Default, chargingResult, qualityOfService, active, pdpID,
                chargingRollOver);
    }

    @Override
    public Long addApplyChargingReportGPRSRequest(int customInvokeTimeout, ChargingResult chargingResult,
            QualityOfService qualityOfService, boolean active, PDPID pdpID, ChargingRollOver chargingRollOver)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ApplyChargingReportGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.applyChargingReportGPRS);
        invoke.setOperationCode(operationCode);

        ApplyChargingReportGPRSRequestImpl applyChargingReportGPRSRequest = new ApplyChargingReportGPRSRequestImpl(chargingResult, qualityOfService,
                active, pdpID, chargingRollOver);

        AsnOutputStream aos = new AsnOutputStream();
        applyChargingReportGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(applyChargingReportGPRSRequest.getTagClass());
        parameter.setPrimitive(applyChargingReportGPRSRequest.getIsPrimitive());
        parameter.setTag(applyChargingReportGPRSRequest.getTag());
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
    public void addApplyChargingReportGPRSResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ApplyChargingReportGPRSResponse: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addEventReportGPRSRequest(GPRSEventType gprsEventType, MiscCallInfo miscGPRSInfo,
            GPRSEventSpecificInformation gprsEventSpecificInformation, PDPID pdpID) throws CAPException {

        return addEventReportGPRSRequest(_Timer_Default, gprsEventType, miscGPRSInfo, gprsEventSpecificInformation, pdpID);
    }

    @Override
    public Long addEventReportGPRSRequest(int customInvokeTimeout, GPRSEventType gprsEventType, MiscCallInfo miscGPRSInfo,
            GPRSEventSpecificInformation gprsEventSpecificInformation, PDPID pdpID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for EventReportGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.eventReportGPRS);
        invoke.setOperationCode(operationCode);

        EventReportGPRSRequestImpl eventReportGPRSRequest = new EventReportGPRSRequestImpl(gprsEventType, miscGPRSInfo,
                gprsEventSpecificInformation, pdpID);

        AsnOutputStream aos = new AsnOutputStream();
        eventReportGPRSRequest.encodeData(aos);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(eventReportGPRSRequest.getTagClass());
        parameter.setPrimitive(eventReportGPRSRequest.getIsPrimitive());
        parameter.setTag(eventReportGPRSRequest.getTag());
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
    public void addEventReportGPRSResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for RequestReportGPRSEventRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addActivityTestGPRSRequest() throws CAPException {
        return addActivityTestGPRSRequest(_Timer_Default);
    }

    @Override
    public Long addActivityTestGPRSRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException("Bad application context name for ActivityTestGPRSRequest: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class3);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerGprsShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.activityTestGPRS);
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

    @Override
    public void addActivityTestGPRSResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gprsSSF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSCF_gprsSSF)
            throw new CAPException(
                    "Bad application context name for ActivityTestGPRSResponse: must be CapV3_gsmSCF_gprsSSF or CapV3_gsmSCF_gprsSSF");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

}
