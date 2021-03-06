
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.CAPDialogImpl;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.gap.GapCriteria;
import org.restcomm.protocols.ss7.cap.api.gap.GapIndicators;
import org.restcomm.protocols.ss7.cap.api.gap.GapTreatment;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.RedirectingPartyIDCap;
import org.restcomm.protocols.ss7.cap.api.primitives.AChChargingAddress;
import org.restcomm.protocols.ss7.cap.api.primitives.BCSMEvent;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.EventTypeBCSM;
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPDialogCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.BearerCapability;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAMELAChBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CGEncountered;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CallSegmentToCancel;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ContinueWithArgumentArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ControlType;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSM;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.FCIBCCCAMELsequence1;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.IPSSPCapabilities;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InitialDPArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformation;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformationType;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.SCIBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.TimeDurationChargingResult;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGIndex;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
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
 * @author alerant appngin
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sparameter. z o.o.)</a>
 *
 */
public class CAPDialogCircuitSwitchedCallImpl extends CAPDialogImpl implements CAPDialogCircuitSwitchedCall {

    protected CAPDialogCircuitSwitchedCallImpl(CAPApplicationContext appCntx, Dialog tcapDialog,
            CAPProviderImpl capProviderImpl, CAPServiceBase capService) {
        super(appCntx, tcapDialog, capProviderImpl, capService);
    }

    @Override
    public Long addInitialDPRequest(int serviceKey, CalledPartyNumberCap calledPartyNumber,
            CallingPartyNumberCap callingPartyNumber, CallingPartysCategoryInap callingPartysCategory,
            CGEncountered CGEncountered, IPSSPCapabilities IPSSPCapabilities, LocationNumberCap locationNumber,
            OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions,
            HighLayerCompatibilityInap highLayerCompatibility, Digits additionalCallingPartyNumber,
            BearerCapability bearerCapability, EventTypeBCSM eventTypeBCSM, RedirectingPartyIDCap redirectingPartyID,
            RedirectionInformationInap redirectionInformation, CauseCap cause,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Carrier carrier, CUGIndex cugIndex,
            CUGInterlock cugInterlock, boolean cugOutgoingAccess, IMSI imsi, SubscriberState subscriberState,
            LocationInformation locationInformation, ExtBasicServiceCode extBasicServiceCode,
            CallReferenceNumber callReferenceNumber, ISDNAddressString mscAddress, CalledPartyBCDNumber calledPartyBCDNumber,
            TimeAndTimezone timeAndTimezone, boolean callForwardingSSPending, InitialDPArgExtension initialDPArgExtension)
            throws CAPException {

        return addInitialDPRequest(_Timer_Default, serviceKey, calledPartyNumber, callingPartyNumber, callingPartysCategory,
                CGEncountered, IPSSPCapabilities, locationNumber, originalCalledPartyID, extensions, highLayerCompatibility,
                additionalCallingPartyNumber, bearerCapability, eventTypeBCSM, redirectingPartyID, redirectionInformation,
                cause, serviceInteractionIndicatorsTwo, carrier, cugIndex, cugInterlock, cugOutgoingAccess, imsi,
                subscriberState, locationInformation, extBasicServiceCode, callReferenceNumber, mscAddress,
                calledPartyBCDNumber, timeAndTimezone, callForwardingSSPending, initialDPArgExtension);
    }

    @Override
    public Long addInitialDPRequest(int customInvokeTimeout, int serviceKey, CalledPartyNumberCap calledPartyNumber,
            CallingPartyNumberCap callingPartyNumber, CallingPartysCategoryInap callingPartysCategory,
            CGEncountered CGEncountered, IPSSPCapabilities IPSSPCapabilities, LocationNumberCap locationNumber,
            OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions,
            HighLayerCompatibilityInap highLayerCompatibility, Digits additionalCallingPartyNumber,
            BearerCapability bearerCapability, EventTypeBCSM eventTypeBCSM, RedirectingPartyIDCap redirectingPartyID,
            RedirectionInformationInap redirectionInformation, CauseCap cause,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Carrier carrier, CUGIndex cugIndex,
            CUGInterlock cugInterlock, boolean cugOutgoingAccess, IMSI imsi, SubscriberState subscriberState,
            LocationInformation locationInformation, ExtBasicServiceCode extBasicServiceCode,
            CallReferenceNumber callReferenceNumber, ISDNAddressString mscAddress, CalledPartyBCDNumber calledPartyBCDNumber,
            TimeAndTimezone timeAndTimezone, boolean callForwardingSSPending, InitialDPArgExtension initialDPArgExtension)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric)
            throw new CAPException(
                    "Bad application context name for addInitialDPRequest: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.initialDP);
        invoke.setOperationCode(operationCode);

        InitialDPRequestImpl initialDPRequest = new InitialDPRequestImpl(serviceKey, calledPartyNumber, callingPartyNumber,
                callingPartysCategory, CGEncountered, IPSSPCapabilities, locationNumber, originalCalledPartyID, extensions,
                highLayerCompatibility, additionalCallingPartyNumber, bearerCapability, eventTypeBCSM, redirectingPartyID,
                redirectionInformation, cause, serviceInteractionIndicatorsTwo, carrier, cugIndex, cugInterlock,
                cugOutgoingAccess, imsi, subscriberState, locationInformation, extBasicServiceCode, callReferenceNumber,
                mscAddress, calledPartyBCDNumber, timeAndTimezone, callForwardingSSPending, initialDPArgExtension, this.capApplicationContext
                        .getVersion().getVersion() >= 3);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        initialDPRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(initialDPRequest.getTagClass());
        parameter.setPrimitive(initialDPRequest.getIsPrimitive());
        parameter.setTag(initialDPRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addApplyChargingReportRequest(TimeDurationChargingResult timeDurationChargingResult) throws CAPException {
        return addApplyChargingReportRequest(_Timer_Default, timeDurationChargingResult);
    }

    @Override
    public Long addApplyChargingReportRequest(int customInvokeTimeout, TimeDurationChargingResult timeDurationChargingResult)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addApplyChargingReportRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.applyChargingReport);
        invoke.setOperationCode(operationCode);

        ApplyChargingReportRequestImpl applyChargingReportRequest = new ApplyChargingReportRequestImpl(timeDurationChargingResult);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        applyChargingReportRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(applyChargingReportRequest.getTagClass());
        parameter.setPrimitive(applyChargingReportRequest.getIsPrimitive());
        parameter.setTag(applyChargingReportRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addApplyChargingRequest(CAMELAChBillingChargingCharacteristics aChBillingChargingCharacteristics,
            SendingSideID partyToCharge, CAPExtensions extensions, AChChargingAddress aChChargingAddress) throws CAPException {

        return addApplyChargingRequest(_Timer_Default, aChBillingChargingCharacteristics, partyToCharge, extensions,
                aChChargingAddress);
    }

    @Override
    public Long addApplyChargingRequest(int customInvokeTimeout, CAMELAChBillingChargingCharacteristics aChBillingChargingCharacteristics,
            SendingSideID partyToCharge, CAPExtensions extensions, AChChargingAddress aChChargingAddress) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addApplyChargingRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.applyCharging);
        invoke.setOperationCode(operationCode);

        ApplyChargingRequestImpl applyChargingRequest = new ApplyChargingRequestImpl(aChBillingChargingCharacteristics, partyToCharge,
                extensions, aChChargingAddress);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        applyChargingRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(applyChargingRequest.getTagClass());
        parameter.setPrimitive(applyChargingRequest.getIsPrimitive());
        parameter.setTag(applyChargingRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addCallInformationReportRequest(ArrayList<RequestedInformation> requestedInformationList,
            CAPExtensions extensions, ReceivingSideID legID) throws CAPException {

        return addCallInformationReportRequest(_Timer_Default, requestedInformationList, extensions, legID);
    }

    @Override
    public Long addCallInformationReportRequest(int customInvokeTimeout,
            ArrayList<RequestedInformation> requestedInformationList, CAPExtensions extensions, ReceivingSideID legID)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addCallInformationReportRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.callInformationReport);
        invoke.setOperationCode(operationCode);

        CallInformationReportRequestImpl callInformationReportRequest = new CallInformationReportRequestImpl(requestedInformationList, extensions, legID);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        callInformationReportRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(callInformationReportRequest.getTagClass());
        parameter.setPrimitive(callInformationReportRequest.getIsPrimitive());
        parameter.setTag(callInformationReportRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addCallInformationRequestRequest(ArrayList<RequestedInformationType> requestedInformationTypeList,
            CAPExtensions extensions, SendingSideID legID) throws CAPException {

        return addCallInformationRequestRequest(_Timer_Default, requestedInformationTypeList, extensions, legID);
    }

    @Override
    public Long addCallInformationRequestRequest(int customInvokeTimeout,
            ArrayList<RequestedInformationType> requestedInformationTypeList, CAPExtensions extensions, SendingSideID legID)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addCallInformationRequestRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.callInformationRequest);
        invoke.setOperationCode(operationCode);

        CallInformationRequestRequestImpl callInformationRequestRequest = new CallInformationRequestRequestImpl(requestedInformationTypeList, extensions,
                legID);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        callInformationRequestRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(callInformationRequestRequest.getTagClass());
        parameter.setPrimitive(callInformationRequestRequest.getIsPrimitive());
        parameter.setTag(callInformationRequestRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addConnectRequest(DestinationRoutingAddress destinationRoutingAddress, AlertingPatternCap alertingPattern,
            OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions, Carrier carrier,
            CallingPartysCategoryInap callingPartysCategory, RedirectingPartyIDCap redirectingPartyID,
            RedirectionInformationInap redirectionInformation, ArrayList<GenericNumberCap> genericNumbers,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, LocationNumberCap chargeNumber,
            LegID legToBeConnected, CUGInterlock cugInterlock, boolean cugOutgoingAccess, boolean suppressionOfAnnouncement,
            boolean ocsIApplicable, NAOliInfo naoliInfo, boolean borInterrogationRequested, boolean suppressNCSI) throws CAPException {

        return addConnectRequest(_Timer_Default, destinationRoutingAddress, alertingPattern, originalCalledPartyID, extensions,
                carrier, callingPartysCategory, redirectingPartyID, redirectionInformation, genericNumbers,
                serviceInteractionIndicatorsTwo, chargeNumber, legToBeConnected, cugInterlock, cugOutgoingAccess,
                suppressionOfAnnouncement, ocsIApplicable, naoliInfo, borInterrogationRequested, suppressNCSI);
    }

    @Override
    public Long addConnectRequest(int customInvokeTimeout, DestinationRoutingAddress destinationRoutingAddress,
            AlertingPatternCap alertingPattern, OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions,
            Carrier carrier, CallingPartysCategoryInap callingPartysCategory, RedirectingPartyIDCap redirectingPartyID,
            RedirectionInformationInap redirectionInformation, ArrayList<GenericNumberCap> genericNumbers,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, LocationNumberCap chargeNumber,
            LegID legToBeConnected, CUGInterlock cugInterlock, boolean cugOutgoingAccess, boolean suppressionOfAnnouncement,
            boolean ocsIApplicable, NAOliInfo naoliInfo, boolean borInterrogationRequested, boolean suppressNCSI) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addConnectRequest: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.connect);
        invoke.setOperationCode(operationCode);

        ConnectRequestImpl connectRequest = new ConnectRequestImpl(destinationRoutingAddress, alertingPattern, originalCalledPartyID,
                extensions, carrier, callingPartysCategory, redirectingPartyID, redirectionInformation, genericNumbers,
                serviceInteractionIndicatorsTwo, chargeNumber, legToBeConnected, cugInterlock, cugOutgoingAccess,
                suppressionOfAnnouncement, ocsIApplicable, naoliInfo, borInterrogationRequested, suppressNCSI);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        connectRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(connectRequest.getTagClass());
        parameter.setPrimitive(connectRequest.getIsPrimitive());
        parameter.setTag(connectRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addContinueRequest() throws CAPException {

        return addContinueRequest(_Timer_Default);
    }

    @Override
    public Long addContinueRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addContinueRequest: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.continueCode);
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
    public Long addContinueWithArgumentRequest(AlertingPatternCap alertingPattern, CAPExtensions extensions,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo,
            CallingPartysCategoryInap callingPartysCategory, ArrayList<GenericNumberCap> genericNumbers,
            CUGInterlock cugInterlock, boolean cugOutgoingAccess, LocationNumberCap chargeNumber, Carrier carrier,
            boolean suppressionOfAnnouncement, NAOliInfo naOliInfo, boolean borInterrogationRequested,
            boolean suppressOCsi, ContinueWithArgumentArgExtension continueWithArgumentArgExtension)
            throws CAPException {

        return addContinueWithArgumentRequest(_Timer_Default, alertingPattern, extensions,
                serviceInteractionIndicatorsTwo, callingPartysCategory, genericNumbers, cugInterlock,
                cugOutgoingAccess, chargeNumber, carrier, suppressionOfAnnouncement, naOliInfo,
                borInterrogationRequested, suppressOCsi, continueWithArgumentArgExtension);
    }

    @Override
    public Long addContinueWithArgumentRequest(int customInvokeTimeout, AlertingPatternCap alertingPattern,
            CAPExtensions capExtensions, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo,
            CallingPartysCategoryInap callingPartysCategory, ArrayList<GenericNumberCap> genericNumbers,
            CUGInterlock cugInterlock, boolean cugOutgoingAccess, LocationNumberCap chargeNumber, Carrier carrier,
            boolean suppressionOfAnnouncement, NAOliInfo naOliInfo, boolean borInterrogationRequested,
            boolean suppressOCsi, ContinueWithArgumentArgExtension continueWithArgumentArgExtension)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addContinueWithArgumentRequest: must be CapV3_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.continueWithArgument);
        invoke.setOperationCode(operationCode);

        ContinueWithArgumentRequestImpl continueWithArgumentRequest = new ContinueWithArgumentRequestImpl(alertingPattern, capExtensions,
                serviceInteractionIndicatorsTwo, callingPartysCategory, genericNumbers, cugInterlock,
                cugOutgoingAccess, chargeNumber, carrier, suppressionOfAnnouncement, naOliInfo,
                borInterrogationRequested, suppressOCsi, continueWithArgumentArgExtension);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        continueWithArgumentRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(continueWithArgumentRequest.getTagClass());
        parameter.setPrimitive(continueWithArgumentRequest.getIsPrimitive());
        parameter.setTag(continueWithArgumentRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addEventReportBCSMRequest(EventTypeBCSM eventTypeBCSM, EventSpecificInformationBCSM eventSpecificInformationBCSM,
            ReceivingSideID legID, MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {

        return addEventReportBCSMRequest(_Timer_Default, eventTypeBCSM, eventSpecificInformationBCSM, legID, miscCallInfo,
                extensions);
    }

    @Override
    public Long addEventReportBCSMRequest(int customInvokeTimeout, EventTypeBCSM eventTypeBCSM, EventSpecificInformationBCSM eventSpecificInformationBCSM,
            ReceivingSideID legID, MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException("Bad application context name for addEventReportBCSMRequest: " +
                "must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or " +
                "CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.eventReportBCSM);
        invoke.setOperationCode(operationCode);

        EventReportBCSMRequestImpl eventReportBCSMRequest = new EventReportBCSMRequestImpl(eventTypeBCSM, eventSpecificInformationBCSM, legID,
                miscCallInfo, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        eventReportBCSMRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(eventReportBCSMRequest.getTagClass());
        parameter.setPrimitive(eventReportBCSMRequest.getIsPrimitive());
        parameter.setTag(eventReportBCSMRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addRequestReportBCSMEventRequest(ArrayList<BCSMEvent> bcsmEventList, CAPExtensions extensions) throws CAPException {

        return addRequestReportBCSMEventRequest(_Timer_Default, bcsmEventList, extensions);
    }

    @Override
    public Long addRequestReportBCSMEventRequest(int customInvokeTimeout, ArrayList<BCSMEvent> bcsmEventList, CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addRequestReportBCSMEventRequest: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.requestReportBCSMEvent);
        invoke.setOperationCode(operationCode);

        RequestReportBCSMEventRequestImpl requestReportBCSMEventRequest = new RequestReportBCSMEventRequestImpl(bcsmEventList, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        requestReportBCSMEventRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(requestReportBCSMEventRequest.getTagClass());
        parameter.setPrimitive(requestReportBCSMEventRequest.getIsPrimitive());
        parameter.setTag(requestReportBCSMEventRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addReleaseCallRequest(CauseCap cause) throws CAPException {

        return addReleaseCallRequest(_Timer_Default, cause);
    }

    @Override
    public Long addReleaseCallRequest(int customInvokeTimeout, CauseCap cause) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException("Bad application context name for addReleaseCallRequest: " +
                "must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or " +
                "CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.releaseCall);
        invoke.setOperationCode(operationCode);

        ReleaseCallRequestImpl releaseCallRequest = new ReleaseCallRequestImpl(cause);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        releaseCallRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(releaseCallRequest.getTagClass());
        parameter.setPrimitive(releaseCallRequest.getIsPrimitive());
        parameter.setTag(releaseCallRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addActivityTestRequest() throws CAPException {

        return addActivityTestRequest(_Timer_Default);
    }

    @Override
    public Long addActivityTestRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addActivityTestRequest: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF, CapV4_gsmSRF_gsmSCF "
                            + "or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class3);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.activityTest);
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

    public void addActivityTestResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addActivityTestResponse: must be CapV1_gsmSSF_to_gsmSCF, CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF, CapV4_gsmSRF_gsmSCF "
                            + "or CapV4_scf_gsmSSFGeneric");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addAssistRequestInstructionsRequest(Digits correlationID, IPSSPCapabilities ipSSPCapabilities, CAPExtensions extensions) throws CAPException {

        return addAssistRequestInstructionsRequest(_Timer_Default, correlationID, ipSSPCapabilities, extensions);
    }

    @Override
    public Long addAssistRequestInstructionsRequest(int customInvokeTimeout, Digits correlationID, IPSSPCapabilities ipSSPCapabilities,
            CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addAssistRequestInstructionsRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF  "
                            + "or CapV4_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.assistRequestInstructions);
        invoke.setOperationCode(operationCode);

        AssistRequestInstructionsRequestImpl assistRequestInstructionsRequest = new AssistRequestInstructionsRequestImpl(correlationID, ipSSPCapabilities,
                extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        assistRequestInstructionsRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(assistRequestInstructionsRequest.getTagClass());
        parameter.setPrimitive(assistRequestInstructionsRequest.getIsPrimitive());
        parameter.setTag(assistRequestInstructionsRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addEstablishTemporaryConnectionRequest(Digits assistingSSPIPRoutingAddress, Digits correlationID, ScfID scfID,
            CAPExtensions extensions, Carrier carrier, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo,
            Integer callSegmentID, NAOliInfo naOliInfo, LocationNumberCap chargeNumber,
            OriginalCalledNumberCap originalCalledPartyID, CallingPartyNumberCap callingPartyNumber) throws CAPException {

        return addEstablishTemporaryConnectionRequest(_Timer_Default, assistingSSPIPRoutingAddress, correlationID, scfID,
                extensions, carrier, serviceInteractionIndicatorsTwo, callSegmentID, naOliInfo, chargeNumber,
                originalCalledPartyID, callingPartyNumber);
    }

    @Override
    public Long addEstablishTemporaryConnectionRequest(int customInvokeTimeout, Digits assistingSSPIPRoutingAddress,
            Digits correlationID, ScfID scfID, CAPExtensions extensions, Carrier carrier,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID, NAOliInfo naOliInfo,
            LocationNumberCap chargeNumber, OriginalCalledNumberCap originalCalledPartyID,
            CallingPartyNumberCap callingPartyNumber) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addEstablishTemporaryConnectionRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlMedium());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.establishTemporaryConnection);
        invoke.setOperationCode(operationCode);

        EstablishTemporaryConnectionRequestImpl establishTemporaryConnectionRequest = new EstablishTemporaryConnectionRequestImpl(assistingSSPIPRoutingAddress,
                correlationID, scfID, extensions, carrier, serviceInteractionIndicatorsTwo, callSegmentID, naOliInfo,
                chargeNumber, originalCalledPartyID, callingPartyNumber, this.capApplicationContext.getVersion().getVersion() >= 3);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        establishTemporaryConnectionRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(establishTemporaryConnectionRequest.getTagClass());
        parameter.setPrimitive(establishTemporaryConnectionRequest.getIsPrimitive());
        parameter.setTag(establishTemporaryConnectionRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addDisconnectForwardConnectionRequest() throws CAPException {

        return addDisconnectForwardConnectionRequest(_Timer_Default);
    }

    @Override
    public Long addDisconnectForwardConnectionRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addDisconnectForwardConnectionRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.disconnectForwardConnection);
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
    public Long addDisconnectForwardConnectionWithArgumentRequest(Integer callSegmentID, CAPExtensions extensions) throws CAPException {
        return addDisconnectForwardConnectionWithArgumentRequest(_Timer_Default, callSegmentID, extensions);
    }

    @Override
    public Long addDisconnectForwardConnectionWithArgumentRequest(int customInvokeTimeout, Integer callSegmentID,
            CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addDisconnectForwardConnectionWithArgumentRequest: must be CapV4_gsmSSF_scfGeneric, " +
                    "CapV4_gsmSSF_scfAssistHandoff or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.dFCWithArgument);
        invoke.setOperationCode(operationCode);

        DisconnectForwardConnectionWithArgumentRequestImpl disconnectForwardConnectionWithArgumentRequest = new DisconnectForwardConnectionWithArgumentRequestImpl(
                callSegmentID, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        disconnectForwardConnectionWithArgumentRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(disconnectForwardConnectionWithArgumentRequest.getTagClass());
        parameter.setPrimitive(disconnectForwardConnectionWithArgumentRequest.getIsPrimitive());
        parameter.setTag(disconnectForwardConnectionWithArgumentRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addConnectToResourceRequest(CalledPartyNumberCap resourceAddress_IPRoutingAddress, boolean resourceAddress_Null,
            CAPExtensions extensions, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID)
            throws CAPException {

        return addConnectToResourceRequest(_Timer_Default, resourceAddress_IPRoutingAddress, resourceAddress_Null, extensions,
                serviceInteractionIndicatorsTwo, callSegmentID);
    }

    @Override
    public Long addConnectToResourceRequest(int customInvokeTimeout, CalledPartyNumberCap resourceAddress_IPRoutingAddress,
            boolean resourceAddress_Null, CAPExtensions extensions,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addConnectToResourceRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.connectToResource);
        invoke.setOperationCode(operationCode);

        ConnectToResourceRequestImpl connectToResourceRequest = new ConnectToResourceRequestImpl(resourceAddress_IPRoutingAddress,
                resourceAddress_Null, extensions, serviceInteractionIndicatorsTwo, callSegmentID);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        connectToResourceRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(connectToResourceRequest.getTagClass());
        parameter.setPrimitive(connectToResourceRequest.getIsPrimitive());
        parameter.setTag(connectToResourceRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addResetTimerRequest(TimerID timerID, int timerValue, CAPExtensions extensions, Integer callSegmentID) throws CAPException {

        return addResetTimerRequest(_Timer_Default, timerID, timerValue, extensions, callSegmentID);
    }

    @Override
    public Long addResetTimerRequest(int customInvokeTimeout, TimerID timerID, int timerValue, CAPExtensions extensions,
            Integer callSegmentID) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addResetTimerRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.resetTimer);
        invoke.setOperationCode(operationCode);

        ResetTimerRequestImpl resetTimerRequest = new ResetTimerRequestImpl(timerID, timerValue, extensions, callSegmentID);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        resetTimerRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(resetTimerRequest.getTagClass());
        parameter.setPrimitive(resetTimerRequest.getIsPrimitive());
        parameter.setTag(resetTimerRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addFurnishChargingInformationRequest(FCIBCCCAMELsequence1 FCIBCCCAMELsequence1) throws CAPException {

        return addFurnishChargingInformationRequest(_Timer_Default, FCIBCCCAMELsequence1);
    }

    @Override
    public Long addFurnishChargingInformationRequest(int customInvokeTimeout, FCIBCCCAMELsequence1 FCIBCCCAMELsequence1)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addFurnishChargingInformationRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.furnishChargingInformation);
        invoke.setOperationCode(operationCode);

        FurnishChargingInformationRequestImpl furnishChargingInformationRequest = new FurnishChargingInformationRequestImpl(FCIBCCCAMELsequence1);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        furnishChargingInformationRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(furnishChargingInformationRequest.getTagClass());
        parameter.setPrimitive(furnishChargingInformationRequest.getIsPrimitive());
        parameter.setTag(furnishChargingInformationRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addSendChargingInformationRequest(SCIBillingChargingCharacteristics sciBillingChargingCharacteristics,
            SendingSideID partyToCharge, CAPExtensions extensions) throws CAPException {

        return addSendChargingInformationRequest(_Timer_Default, sciBillingChargingCharacteristics, partyToCharge, extensions);
    }

    @Override
    public Long addSendChargingInformationRequest(int customInvokeTimeout, SCIBillingChargingCharacteristics sciBillingChargingCharacteristics,
            SendingSideID partyToCharge, CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric)
            throw new CAPException(
                    "Bad application context name for addSendChargingInformationRequest: must be CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.sendChargingInformation);
        invoke.setOperationCode(operationCode);

        SendChargingInformationRequestImpl sendChargingInformationRequest = new SendChargingInformationRequestImpl(sciBillingChargingCharacteristics,
                partyToCharge, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        sendChargingInformationRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendChargingInformationRequest.getTagClass());
        parameter.setPrimitive(sendChargingInformationRequest.getIsPrimitive());
        parameter.setTag(sendChargingInformationRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addSpecializedResourceReportRequest_CapV23(Long linkedId) throws CAPException {

        return addSpecializedResourceReportRequest_CapV23(linkedId, _Timer_Default);
    }

    @Override
    public Long addSpecializedResourceReportRequest_CapV4(Long linkedId, boolean isAllAnnouncementsComplete,
            boolean isFirstAnnouncementStarted) throws CAPException {

        return addSpecializedResourceReportRequest_CapV4(linkedId, _Timer_Default, isAllAnnouncementsComplete,
                isFirstAnnouncementStarted);
    }

    @Override
    public Long addSpecializedResourceReportRequest_CapV23(Long linkedId, int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addSpecializedResourceReportRequest_CapV23: must be "
                            + "CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV2_gsmSRF_to_gsmSCF or CapV3_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.specializedResourceReport);
        invoke.setOperationCode(operationCode);

        SpecializedResourceReportRequestImpl specializedResourceReportRequest = new SpecializedResourceReportRequestImpl(false);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        specializedResourceReportRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(specializedResourceReportRequest.getTagClass());
        parameter.setPrimitive(specializedResourceReportRequest.getIsPrimitive());
        parameter.setTag(specializedResourceReportRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        invoke.setLinkedId(linkedId);

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addSpecializedResourceReportRequest_CapV4(Long linkedId, int customInvokeTimeout,
            boolean isAllAnnouncementsComplete, boolean isFirstAnnouncementStarted) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addSpecializedResourceReportRequest_CapV4: "
                            + "must be CapV4_gsmSSF_scfGeneric, CapV4_gsmSSF_scfAssistHandoff, CapV4_scf_gsmSSFGeneric or CapV4_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.specializedResourceReport);
        invoke.setOperationCode(operationCode);

        SpecializedResourceReportRequestImpl specializedResourceReportRequest = new SpecializedResourceReportRequestImpl(isAllAnnouncementsComplete,
                isFirstAnnouncementStarted, true);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        specializedResourceReportRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(specializedResourceReportRequest.getTagClass());
        parameter.setPrimitive(specializedResourceReportRequest.getIsPrimitive());
        parameter.setTag(specializedResourceReportRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }

        invoke.setLinkedId(linkedId);

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addPlayAnnouncementRequest(InformationToSend informationToSend, Boolean disconnectFromIPForbidden,
            Boolean requestAnnouncementCompleteNotification, CAPExtensions extensions, Integer callSegmentID,
            Boolean requestAnnouncementStartedNotification) throws CAPException {

        return addPlayAnnouncementRequest(_Timer_Default, informationToSend, disconnectFromIPForbidden,
                requestAnnouncementCompleteNotification, extensions, callSegmentID, requestAnnouncementStartedNotification);
    }

    @Override
    public Long addPlayAnnouncementRequest(int customInvokeTimeout, InformationToSend informationToSend,
            Boolean disconnectFromIPForbidden, Boolean requestAnnouncementCompleteNotification, CAPExtensions extensions,
            Integer callSegmentID, Boolean requestAnnouncementStartedNotification) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addPlayAnnouncementRequest: must be "
                            + "CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV4_scf_gsmSSFGeneric"
                            + ", CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF or CapV4_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlLong());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.playAnnouncement);
        invoke.setOperationCode(operationCode);

        PlayAnnouncementRequestImpl playAnnouncementRequest = new PlayAnnouncementRequestImpl(informationToSend, disconnectFromIPForbidden,
                requestAnnouncementCompleteNotification, extensions, callSegmentID, requestAnnouncementStartedNotification);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        playAnnouncementRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(playAnnouncementRequest.getTagClass());
        parameter.setPrimitive(playAnnouncementRequest.getIsPrimitive());
        parameter.setTag(playAnnouncementRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addPromptAndCollectUserInformationRequest(CollectedInfo collectedInfo, Boolean disconnectFromIPForbidden,
            InformationToSend informationToSend, CAPExtensions extensions, Integer callSegmentID,
            Boolean requestAnnouncementStartedNotification) throws CAPException {

        return addPromptAndCollectUserInformationRequest(_Timer_Default, collectedInfo, disconnectFromIPForbidden,
                informationToSend, extensions, callSegmentID, requestAnnouncementStartedNotification);
    }

    @Override
    public Long addPromptAndCollectUserInformationRequest(int customInvokeTimeout, CollectedInfo collectedInfo,
            Boolean disconnectFromIPForbidden, InformationToSend informationToSend, CAPExtensions extensions,
            Integer callSegmentID, Boolean requestAnnouncementStartedNotification) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addPromptAndCollectUserInformationRequest: must be "
                            + "CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV4_scf_gsmSSFGeneric"
                            + ", CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF or CapV4_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlLong());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.promptAndCollectUserInformation);
        invoke.setOperationCode(operationCode);

        PromptAndCollectUserInformationRequestImpl promptAndCollectUserInformationRequest = new PromptAndCollectUserInformationRequestImpl(collectedInfo,
                disconnectFromIPForbidden, informationToSend, extensions, callSegmentID, requestAnnouncementStartedNotification);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        promptAndCollectUserInformationRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(promptAndCollectUserInformationRequest.getTagClass());
        parameter.setPrimitive(promptAndCollectUserInformationRequest.getIsPrimitive());
        parameter.setTag(promptAndCollectUserInformationRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public void addPromptAndCollectUserInformationResponse_DigitsResponse(long invokeId, Digits digitsResponse)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addPromptAndCollectUserInformationResponse: must be "
                            + "CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV4_scf_gsmSSFGeneric"
                            + ", CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF or CapV4_gsmSRF_gsmSCF");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.promptAndCollectUserInformation);
        resultLast.setOperationCode(operationCode);

        PromptAndCollectUserInformationResponseImpl promptAndCollectUserInformationResponse = new PromptAndCollectUserInformationResponseImpl(digitsResponse);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        promptAndCollectUserInformationResponse.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(promptAndCollectUserInformationResponse.getTagClass());
        parameter.setPrimitive(promptAndCollectUserInformationResponse.getIsPrimitive());
        parameter.setTag(promptAndCollectUserInformationResponse.getTag());
        parameter.setData(asnOutputStream.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addCancelRequest_InvokeId(Integer invokeID) throws CAPException {

        return addCancelRequest_InvokeId(_Timer_Default, invokeID);
    }

    @Override
    public Long addCancelRequest_AllRequests() throws CAPException {

        return addCancelRequest_AllRequests(_Timer_Default);
    }

    @Override
    public Long addCancelRequest_CallSegmentToCancel(CallSegmentToCancel callSegmentToCancel) throws CAPException {

        return addCancelRequest_CallSegmentToCancel(_Timer_Default, callSegmentToCancel);
    }

    @Override
    public Long addCancelRequest_InvokeId(int customInvokeTimeout, Integer invokeID) throws CAPException {

        CancelRequestImpl cancelRequest = new CancelRequestImpl(invokeID);
        return addCancelRequest(customInvokeTimeout, cancelRequest);
    }

    @Override
    public Long addCancelRequest_AllRequests(int customInvokeTimeout) throws CAPException {

        CancelRequestImpl cancelRequest = new CancelRequestImpl(true);
        return addCancelRequest(customInvokeTimeout, cancelRequest);
    }

    @Override
    public Long addCancelRequest_CallSegmentToCancel(int customInvokeTimeout, CallSegmentToCancel callSegmentToCancel)
            throws CAPException {

        CancelRequestImpl cancelRequest = new CancelRequestImpl(callSegmentToCancel);
        return addCancelRequest(customInvokeTimeout, cancelRequest);
    }

    private Long addCancelRequest(int customInvokeTimeout, CancelRequestImpl cancelRequest) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV3_gsmSRF_gsmSCF
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSRF_gsmSCF)
            throw new CAPException(
                    "Bad application context name for addCancelRequest: must be "
                            + "CapV2_gsmSSF_to_gsmSCF, CapV3_gsmSSF_scfGeneric, CapV4_gsmSSF_scfGeneric, "
                            + "CapV2_assistGsmSSF_to_gsmSCF, CapV3_gsmSSF_scfAssistHandoff, CapV4_gsmSSF_scfAssistHandoff, CapV4_scf_gsmSSFGeneric"
                            + ", CapV2_gsmSRF_to_gsmSCF, CapV3_gsmSRF_gsmSCF or CapV4_gsmSRF_gsmSCF");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class2);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.cancelCode);
        invoke.setOperationCode(operationCode);

        AsnOutputStream asnOutputStream = new AsnOutputStream();
        cancelRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(cancelRequest.getTagClass());
        parameter.setPrimitive(cancelRequest.getIsPrimitive());
        parameter.setTag(cancelRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public Long addDisconnectLegRequest(LegID logToBeReleased, CauseCap releaseCause, CAPExtensions extensions)
            throws CAPException {
        return addDisconnectLegRequest(_Timer_Default, logToBeReleased, releaseCause, extensions);
    }

    @Override
    public Long addDisconnectLegRequest(int customInvokeTimeout, LegID logToBeReleased, CauseCap releaseCause,
            CAPExtensions extensions) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addDisconnectLegRequest: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.disconnectLeg);
        invoke.setOperationCode(operationCode);

        DisconnectLegRequestImpl disconnectLegRequest = new DisconnectLegRequestImpl(logToBeReleased, releaseCause, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        disconnectLegRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(disconnectLegRequest.getTagClass());
        parameter.setPrimitive(disconnectLegRequest.getIsPrimitive());
        parameter.setTag(disconnectLegRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public void addDisconnectLegResponse(long invokeId) throws CAPException {
        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException("Bad application context name for addDisconnectLegResponse: must be " + "CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.disconnectLeg);
        resultLast.setOperationCode(operationCode);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addInitiateCallAttemptRequest(DestinationRoutingAddress destinationRoutingAddress,
            CAPExtensions extensions, LegID legToBeCreated, Integer newCallSegment,
            CallingPartyNumberCap callingPartyNumber, CallReferenceNumber callReferenceNumber,
            ISDNAddressString gsmSCFAddress, boolean suppressTCsi) throws CAPException {
        return addInitiateCallAttemptRequest(_Timer_Default, destinationRoutingAddress, extensions, legToBeCreated,
                newCallSegment, callingPartyNumber, callReferenceNumber, gsmSCFAddress, suppressTCsi);
    }

    @Override
    public Long addInitiateCallAttemptRequest(int customInvokeTimeout,
            DestinationRoutingAddress destinationRoutingAddress, CAPExtensions extensions, LegID legToBeCreated,
            Integer newCallSegment, CallingPartyNumberCap callingPartyNumber, CallReferenceNumber callReferenceNumber,
            ISDNAddressString gsmSCFAddress, boolean suppressTCsi) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addInitiateCallAttemptRequest: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.initiateCallAttempt);
        invoke.setOperationCode(operationCode);

        InitiateCallAttemptRequestImpl initiateCallAttemptRequest = new InitiateCallAttemptRequestImpl(destinationRoutingAddress, extensions,
                legToBeCreated, newCallSegment, callingPartyNumber, callReferenceNumber, gsmSCFAddress, suppressTCsi);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        initiateCallAttemptRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(initiateCallAttemptRequest.getTagClass());
        parameter.setPrimitive(initiateCallAttemptRequest.getIsPrimitive());
        parameter.setTag(initiateCallAttemptRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public void addInitiateCallAttemptResponse(long invokeId, SupportedCamelPhases supportedCamelPhases,
            OfferedCamel4Functionalities offeredCamel4Functionalities, CAPExtensions extensions,
            boolean releaseCallArgExtensionAllowed) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addInitiateCallAttemptResponse: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.initiateCallAttempt);
        resultLast.setOperationCode(operationCode);

        InitiateCallAttemptResponseImpl initiateCallAttemptResponse = new InitiateCallAttemptResponseImpl(supportedCamelPhases,
                offeredCamel4Functionalities, extensions, releaseCallArgExtensionAllowed);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        initiateCallAttemptResponse.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(initiateCallAttemptResponse.getTagClass());
        parameter.setPrimitive(initiateCallAttemptResponse.getIsPrimitive());
        parameter.setTag(initiateCallAttemptResponse.getTag());
        parameter.setData(asnOutputStream.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addMoveLegRequest(LegID logIDToMove, CAPExtensions extensions) throws CAPException {
        return addMoveLegRequest(_Timer_Default, logIDToMove, extensions);
    }

    @Override
    public Long addMoveLegRequest(int customInvokeTimeout, LegID logIDToMove, CAPExtensions extensions)
            throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addMoveLegRequest: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.moveLeg);
        invoke.setOperationCode(operationCode);

        MoveLegRequestImpl moveLegRequest = new MoveLegRequestImpl(logIDToMove, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        moveLegRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(moveLegRequest.getTagClass());
        parameter.setPrimitive(moveLegRequest.getIsPrimitive());
        parameter.setTag(moveLegRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
    public void addMoveLegResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException("Bad application context name for addMoveLegResponse: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.moveLeg);
        resultLast.setOperationCode(operationCode);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addCollectInformationRequest() throws CAPException {

        return addCollectInformationRequest(_Timer_Default);
    }

    @Override
    public Long addCollectInformationRequest(int customInvokeTimeout) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context name for addContinueRequest: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.collectInformation);
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

    public Long addSplitLegRequest(LegID legIDToSplit, Integer newCallSegmentId, CAPExtensions extensions)
            throws CAPException {
        return addSplitLegRequest(_Timer_Default, legIDToSplit, newCallSegmentId, extensions);
    }

    public Long addSplitLegRequest(int customInvokeTimeout, LegID legIDToSplit, Integer newCallSegmentId,
            CAPExtensions extensions) throws CAPException {
        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context for addSplitLegRequest: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.splitLeg);
        invoke.setOperationCode(operationCode);

        SplitLegRequestImpl splitLegRequest = new SplitLegRequestImpl(legIDToSplit, newCallSegmentId, extensions);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        splitLegRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(splitLegRequest.getTagClass());
        parameter.setPrimitive(splitLegRequest.getIsPrimitive());
        parameter.setTag(splitLegRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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

    public void addSplitLegResponse(long invokeId) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_scf_gsmSSFGeneric)
            throw new CAPException(
                    "Bad application context for addSplitLegResponse: must be CapV4_gsmSSF_scfGeneric or CapV4_scf_gsmSSFGeneric");

        ReturnResultLast resultLast = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.splitLeg);
        resultLast.setOperationCode(operationCode);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addCallGapRequest(GapCriteria gapCriteria, GapIndicators gapIndicators, ControlType controlType, GapTreatment gapTreatment, CAPExtensions capExtension) throws CAPException {
        return addCallGapRequest(_Timer_Default, gapCriteria, gapIndicators, controlType, gapTreatment, capExtension);
    }

    @Override
    public Long addCallGapRequest(int customInvokeTimeout, GapCriteria gapCriteria, GapIndicators gapIndicators, ControlType controlType, GapTreatment gapTreatment, CAPExtensions capExtension) throws CAPException {

        if (this.capApplicationContext != CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                && this.capApplicationContext != CAPApplicationContext.CapV4_gsmSSF_scfGeneric) {
            throw new CAPException(
                    "Bad application context name for addApplyChargingReportRequest: must be CapV3_gsmSSF_scfGeneric or CapV4_gsmSSF_scfGeneric");
        }

        Invoke invoke = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default) {
            invoke.setTimeout(getTimerCircuitSwitchedCallControlShort());
        } else {
            invoke.setTimeout(customInvokeTimeout);
        }

        OperationCode operationCode = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) CAPOperationCode.callGap);
        invoke.setOperationCode(operationCode);

        CallGapRequestImpl callGapRequest = new CallGapRequestImpl(gapCriteria, gapIndicators, controlType, gapTreatment, capExtension);
        AsnOutputStream asnOutputStream = new AsnOutputStream();
        callGapRequest.encodeData(asnOutputStream);

        Parameter parameter = this.capProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(callGapRequest.getTagClass());
        parameter.setPrimitive(callGapRequest.getIsPrimitive());
        parameter.setTag(callGapRequest.getTag());
        parameter.setData(asnOutputStream.toByteArray());
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
}
