
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.CAPDialogImpl;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.CAPServiceBaseImpl;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPDialogCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCallListener;
import org.restcomm.protocols.ss7.cap.dialog.ServingCheckDataImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPServiceCircuitSwitchedCallImpl extends CAPServiceBaseImpl implements CAPServiceCircuitSwitchedCall {

    protected Logger loger = Logger.getLogger(CAPServiceCircuitSwitchedCallImpl.class);

    public CAPServiceCircuitSwitchedCallImpl(CAPProviderImpl capProviderImpl) {
        super(capProviderImpl);
    }

    @Override
    public CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) throws CAPException {
        return this.createNewDialog(capApplicationContext, sccpCallingPartyAddress, sccpCalledPartyAddress, null);
    }

    @Override
    public CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, Long localTrId)
            throws CAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new CAPException(
                    "Cannot create CAPDialogCircuitSwitchedCall because CAPServiceCircuitSwitchedCall is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        CAPDialogCircuitSwitchedCallImpl dialog = new CAPDialogCircuitSwitchedCallImpl(capApplicationContext, tcapDialog,
                this.capProviderImpl, this);

        this.putCAPDialogIntoCollection(dialog);

        return dialog;
    }

    @Override
    public void addCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceListener) {
        super.addCAPServiceListener(capServiceListener);
    }

    @Override
    public void removeCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceListener) {
        super.removeCAPServiceListener(capServiceListener);
    }

    @Override
    protected CAPDialogImpl createNewDialogIncoming(CAPApplicationContext capApplicationContext, Dialog tcapDialog) {
        return new CAPDialogCircuitSwitchedCallImpl(capApplicationContext, tcapDialog, this.capProviderImpl, this);
    }

    @Override
    public ServingCheckData isServingService(CAPApplicationContext capApplicationContext) {

        switch (capApplicationContext) {
            case CapV1_gsmSSF_to_gsmSCF:
            case CapV2_gsmSSF_to_gsmSCF:
            case CapV2_assistGsmSSF_to_gsmSCF:
            case CapV2_gsmSRF_to_gsmSCF:
            case CapV3_gsmSSF_scfGeneric:
            case CapV3_gsmSSF_scfAssistHandoff:
            case CapV3_gsmSRF_gsmSCF:
            case CapV4_gsmSSF_scfGeneric:
            case CapV4_gsmSSF_scfAssistHandoff:
            case CapV4_scf_gsmSSFGeneric:
            case CapV4_gsmSRF_gsmSCF:
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    public long[] getLinkedOperationList(long operationCode) {
        if (operationCode == CAPOperationCode.playAnnouncement || operationCode == CAPOperationCode.promptAndCollectUserInformation) {
            return new long[] { CAPOperationCode.specializedResourceReport };
        }

        return null;
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, CAPDialog capDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws CAPParsingComponentException {

        CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCallImpl = (CAPDialogCircuitSwitchedCallImpl) capDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        CAPApplicationContext acn = capDialog.getApplicationContext();
        int ocValueInt = (int) (long) operationCodeValue;

        switch (ocValueInt) {
            case CAPOperationCode.initialDP:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        this.initialDpRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.requestReportBCSMEvent:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        this.requestReportBCSMEventRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.applyCharging:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        this.applyChargingRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.eventReportBCSM:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        eventReportBCSMRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.continueCode:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        continueRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.continueWithArgument:
                if (acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        continueWithArgumentRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.applyChargingReport:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        applyChargingReportRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.releaseCall:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        releaseCallRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.connect:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        connectRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.callGap:
                if (acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        callGapRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.callInformationRequest:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        callInformationRequestRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.callInformationReport:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        callInformationReportRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.activityTest:
                if (acn == CAPApplicationContext.CapV1_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        activityTestRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        activityTestResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.assistRequestInstructions:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        assistRequestInstructionsRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.establishTemporaryConnection:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        establishTemporaryConnectionRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.disconnectForwardConnection:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        disconnectForwardConnectionRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.disconnectLeg:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {

                    if (componentType == ComponentType.Invoke) {
                        disconnectLegRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        disconnectLegResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.dFCWithArgument:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {

                    if (componentType == ComponentType.Invoke) {
                        dFCWithArgument(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.initiateCallAttempt:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {

                    if (componentType == ComponentType.Invoke) {
                        initiateCallAttemptRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        initiateCallAttemptResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.connectToResource:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        connectToResourceRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.resetTimer:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        resetTimerRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.furnishChargingInformation:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        furnishChargingInformationRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.sendChargingInformation:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        sendChargingInformationRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.specializedResourceReport:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        specializedResourceReportRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId, linkedId,
                                linkedInvoke);
                    }
                }
                break;

            case CAPOperationCode.playAnnouncement:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        playAnnouncementRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.promptAndCollectUserInformation:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        promptAndCollectUserInformationRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        promptAndCollectUserInformationResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.cancelCode:
                if (acn == CAPApplicationContext.CapV2_gsmSSF_to_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV2_assistGsmSSF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_gsmSSF_scfAssistHandoff
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric
                        || acn == CAPApplicationContext.CapV2_gsmSRF_to_gsmSCF
                        || acn == CAPApplicationContext.CapV3_gsmSRF_gsmSCF || acn == CAPApplicationContext.CapV4_gsmSRF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        cancelRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.moveLeg:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {

                    if (componentType == ComponentType.Invoke) {
                        moveLegRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        moveLegResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.splitLeg:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {

                    if (componentType == ComponentType.Invoke) {
                        splitLegRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        splitLegResponse(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            case CAPOperationCode.collectInformation:
                if (acn == CAPApplicationContext.CapV4_gsmSSF_scfGeneric
                        || acn == CAPApplicationContext.CapV4_scf_gsmSSFGeneric) {
                    if (componentType == ComponentType.Invoke) {
                        collectInformationRequest(parameter, capDialogCircuitSwitchedCallImpl, invokeId);
                    }
                }
                break;

            default:
                throw new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void initialDpRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding initialDpRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding initialDpRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        InitialDPRequestImpl ind = new InitialDPRequestImpl(
            capDialogCircuitSwitchedCall.getApplicationContext().getVersion().getVersion() >= 3);
        ind.decodeData(ais, buf.length);

        ind.setInvokeId(invokeId);
        ind.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(ind);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onInitialDPRequest(ind);
            } catch (Exception e) {
                loger.error("Error processing initialDpRequest: " + e.getMessage(), e);
            }
        }
    }

    private void requestReportBCSMEventRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding requestReportBCSMEventRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding requestReportBCSMEventRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        RequestReportBCSMEventRequestImpl requestReportBCSMEventRequestIndication = new RequestReportBCSMEventRequestImpl();
        requestReportBCSMEventRequestIndication.decodeData(asnInputStream, buf.length);

        requestReportBCSMEventRequestIndication.setInvokeId(invokeId);
        requestReportBCSMEventRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(requestReportBCSMEventRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onRequestReportBCSMEventRequest(requestReportBCSMEventRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing requestReportBCSMEventRequest: " + e.getMessage(), e);
            }
        }
    }

    private void applyChargingRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding applyChargingRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding applyChargingRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ApplyChargingRequestImpl applyChargingRequestIndication = new ApplyChargingRequestImpl();
        applyChargingRequestIndication.decodeData(asnInputStream, buf.length);

        applyChargingRequestIndication.setInvokeId(invokeId);
        applyChargingRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(applyChargingRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onApplyChargingRequest(applyChargingRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing requestReportBCSMEventRequest: " + e.getMessage(), e);
            }
        }
    }

    private void eventReportBCSMRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding eventReportBCSMRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding eventReportBCSMRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        EventReportBCSMRequestImpl eventReportBCSMRequestIndication = new EventReportBCSMRequestImpl();
        eventReportBCSMRequestIndication.decodeData(asnInputStream, buf.length);

        eventReportBCSMRequestIndication.setInvokeId(invokeId);
        eventReportBCSMRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(eventReportBCSMRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onEventReportBCSMRequest(eventReportBCSMRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing eventReportBCSMRequest: " + e.getMessage(), e);
            }
        }
    }

    private void continueRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        ContinueRequestImpl continueRequestIndication = new ContinueRequestImpl();

        continueRequestIndication.setInvokeId(invokeId);
        continueRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(continueRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onContinueRequest(continueRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing continueRequest: " + e.getMessage(), e);
            }
        }
    }

    private void continueWithArgumentRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding continueWithArgumentRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding continueWithArgumentRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        ContinueWithArgumentRequestImpl continueWithArgumentRequestIndication = new ContinueWithArgumentRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        continueWithArgumentRequestIndication.decodeData(asnInputStream, buf.length);

        continueWithArgumentRequestIndication.setInvokeId(invokeId);
        continueWithArgumentRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(continueWithArgumentRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onContinueWithArgumentRequest(continueWithArgumentRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing continueWithArgumentRequest: " + e.getMessage(), e);
            }
        }
    }

    private void applyChargingReportRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding applyChargingReportRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding applyChargingReportRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ApplyChargingReportRequestImpl applyChargingReportRequestIndication = new ApplyChargingReportRequestImpl();
        applyChargingReportRequestIndication.decodeData(asnInputStream, buf.length);

        applyChargingReportRequestIndication.setInvokeId(invokeId);
        applyChargingReportRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(applyChargingReportRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onApplyChargingReportRequest(applyChargingReportRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing applyChargingReportRequest: " + e.getMessage(), e);
            }
        }
    }

    private void releaseCallRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding releaseCallRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding releaseCallRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ReleaseCallRequestImpl releaseCallRequestIndication = new ReleaseCallRequestImpl();
        releaseCallRequestIndication.decodeData(asnInputStream, buf.length);

        releaseCallRequestIndication.setInvokeId(invokeId);
        releaseCallRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(releaseCallRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onReleaseCallRequest(releaseCallRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing applyChargingReportRequest: " + e.getMessage(), e);
            }
        }
    }

    private void connectRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding connectRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding connectRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ConnectRequestImpl connectRequestIndication = new ConnectRequestImpl();
        connectRequestIndication.decodeData(asnInputStream, buf.length);

        connectRequestIndication.setInvokeId(invokeId);
        connectRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(connectRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onConnectRequest(connectRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing eventReportBCSMRequest: " + e.getMessage(), e);
            }
        }
    }

    private void callGapRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {
        if (parameter == null) {
            throw new CAPParsingComponentException(
                    "Error while decoding callGapRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive()) {
            throw new CAPParsingComponentException(
                    "Error while decoding callGapRequest: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        CallGapRequestImpl callGapRequestIndication = new CallGapRequestImpl();
        callGapRequestIndication.decodeData(asnInputStream, buf.length);

        callGapRequestIndication.setInvokeId(invokeId);
        callGapRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(callGapRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onCallGapRequest(callGapRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing callGapRequest: " + e.getMessage(), e);
            }
        }
    }

    private void callInformationRequestRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding callInformationRequestRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding callInformationRequestRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        CallInformationRequestRequestImpl callInformationRequestRequestIndication = new CallInformationRequestRequestImpl();
        callInformationRequestRequestIndication.decodeData(asnInputStream, buf.length);

        callInformationRequestRequestIndication.setInvokeId(invokeId);
        callInformationRequestRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(callInformationRequestRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onCallInformationRequestRequest(callInformationRequestRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing eventReportBCSMRequest: " + e.getMessage(), e);
            }
        }
    }

    private void callInformationReportRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding callInformationReportRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding callInformationReportRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        CallInformationReportRequestImpl callInformationReportRequestIndication = new CallInformationReportRequestImpl();
        callInformationReportRequestIndication.decodeData(asnInputStream, buf.length);

        callInformationReportRequestIndication.setInvokeId(invokeId);
        callInformationReportRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(callInformationReportRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onCallInformationReportRequest(callInformationReportRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing eventReportBCSMRequest: " + e.getMessage(), e);
            }
        }
    }

    private void activityTestRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        ActivityTestRequestImpl activityTestRequestIndication = new ActivityTestRequestImpl();

        activityTestRequestIndication.setInvokeId(invokeId);
        activityTestRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(activityTestRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onActivityTestRequest(activityTestRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing activityTestRequest: " + e.getMessage(), e);
            }
        }
    }

    private void activityTestResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        ActivityTestResponseImpl activityTestResponseIndication = new ActivityTestResponseImpl();

        activityTestResponseIndication.setInvokeId(invokeId);
        activityTestResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(activityTestResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onActivityTestResponse(activityTestResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing activityTestResponse: " + e.getMessage(), e);
            }
        }
    }

    private void assistRequestInstructionsRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding assistRequestInstructionsRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding assistRequestInstructionsRequest: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        AssistRequestInstructionsRequestImpl assistRequestInstructionsRequestIndication = new AssistRequestInstructionsRequestImpl();
        assistRequestInstructionsRequestIndication.decodeData(asnInputStream, buf.length);

        assistRequestInstructionsRequestIndication.setInvokeId(invokeId);
        assistRequestInstructionsRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(assistRequestInstructionsRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onAssistRequestInstructionsRequest(assistRequestInstructionsRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing assistRequestInstructionsRequest: " + e.getMessage(), e);
            }
        }
    }

    private void establishTemporaryConnectionRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding establishTemporaryConnectionRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding establishTemporaryConnectionRequest: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        EstablishTemporaryConnectionRequestImpl establishTemporaryConnectionRequestIndication = new EstablishTemporaryConnectionRequestImpl(capDialogCircuitSwitchedCall
                .getApplicationContext().getVersion().getVersion() >= 3);
        establishTemporaryConnectionRequestIndication.decodeData(asnInputStream, buf.length);

        establishTemporaryConnectionRequestIndication.setInvokeId(invokeId);
        establishTemporaryConnectionRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(establishTemporaryConnectionRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onEstablishTemporaryConnectionRequest(establishTemporaryConnectionRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing establishTemporaryConnectionRequest: " + e.getMessage(), e);
            }
        }
    }

    private void disconnectForwardConnectionRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        DisconnectForwardConnectionRequestImpl disconnectForwardConnectionRequestIndication = new DisconnectForwardConnectionRequestImpl();

        disconnectForwardConnectionRequestIndication.setInvokeId(invokeId);
        disconnectForwardConnectionRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(disconnectForwardConnectionRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onDisconnectForwardConnectionRequest(disconnectForwardConnectionRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing disconnectForwardConnectionRequest: " + e.getMessage(), e);
            }
        }
    }

    private void disconnectLegRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding disconnectLegRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding disconnectLegRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        DisconnectLegRequestImpl disconnectLegRequestIndication = new DisconnectLegRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        disconnectLegRequestIndication.decodeData(asnInputStream, buf.length);

        disconnectLegRequestIndication.setInvokeId(invokeId);
        disconnectLegRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(disconnectLegRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onDisconnectLegRequest(disconnectLegRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing disconnectLegRequest: " + e.getMessage(), e);
            }
        }
    }

    private void disconnectLegResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        DisconnectLegResponseImpl disconnectLegResponseIndication = new DisconnectLegResponseImpl();

        disconnectLegResponseIndication.setInvokeId(invokeId);
        disconnectLegResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(disconnectLegResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onDisconnectLegResponse(disconnectLegResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing disconnectLegResponse: " + e.getMessage(), e);
            }
        }
    }

    private void dFCWithArgument(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding dFCWithArgument: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding dFCWithArgument: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        DisconnectForwardConnectionWithArgumentRequestImpl disconnectForwardConnectionWithArgumentRequestIndication = new DisconnectForwardConnectionWithArgumentRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        disconnectForwardConnectionWithArgumentRequestIndication.decodeData(asnInputStream, buf.length);

        disconnectForwardConnectionWithArgumentRequestIndication.setInvokeId(invokeId);
        disconnectForwardConnectionWithArgumentRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(disconnectForwardConnectionWithArgumentRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onDisconnectForwardConnectionWithArgumentRequest(disconnectForwardConnectionWithArgumentRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing dFCWithArgument: " + e.getMessage(), e);
            }
        }
    }

    private void initiateCallAttemptRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding initiateCallAttemptRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding initiateCallAttemptRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        InitiateCallAttemptRequestImpl initiateCallAttemptRequestIndication = new InitiateCallAttemptRequestImpl();

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        initiateCallAttemptRequestIndication.decodeData(asnInputStream, buf.length);

        initiateCallAttemptRequestIndication.setInvokeId(invokeId);
        initiateCallAttemptRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(initiateCallAttemptRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onInitiateCallAttemptRequest(initiateCallAttemptRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing initiateCallAttemptRequest: " + e.getMessage(), e);
            }
        }
    }

    private void initiateCallAttemptResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding initiateCallAttemptResponse: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding initiateCallAttemptResponse: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        InitiateCallAttemptResponseImpl initiateCallAttemptResponseIndication = new InitiateCallAttemptResponseImpl();

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        initiateCallAttemptResponseIndication.decodeData(asnInputStream, buf.length);

        initiateCallAttemptResponseIndication.setInvokeId(invokeId);
        initiateCallAttemptResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(initiateCallAttemptResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onInitiateCallAttemptResponse(initiateCallAttemptResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing initiateCallAttemptResponse: " + e.getMessage(), e);
            }
        }
    }

    private void connectToResourceRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding connectToResourceRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding connectToResourceRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ConnectToResourceRequestImpl connectToResourceRequestIndication = new ConnectToResourceRequestImpl();
        connectToResourceRequestIndication.decodeData(asnInputStream, buf.length);

        connectToResourceRequestIndication.setInvokeId(invokeId);
        connectToResourceRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(connectToResourceRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onConnectToResourceRequest(connectToResourceRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing connectToResourceRequest: " + e.getMessage(), e);
            }
        }
    }

    private void resetTimerRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding resetTimerRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding resetTimerRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        ResetTimerRequestImpl resetTimerRequestIndication = new ResetTimerRequestImpl();
        resetTimerRequestIndication.decodeData(asnInputStream, buf.length);

        resetTimerRequestIndication.setInvokeId(invokeId);
        resetTimerRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(resetTimerRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onResetTimerRequest(resetTimerRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing resetTimerRequest: " + e.getMessage(), e);
            }
        }
    }

    private void furnishChargingInformationRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding furnishChargingInformationRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding furnishChargingInformationRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        FurnishChargingInformationRequestImpl furnishChargingInformationRequestIndication = new FurnishChargingInformationRequestImpl();
        furnishChargingInformationRequestIndication.decodeData(asnInputStream, buf.length);

        furnishChargingInformationRequestIndication.setInvokeId(invokeId);
        furnishChargingInformationRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(furnishChargingInformationRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onFurnishChargingInformationRequest(furnishChargingInformationRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing furnishChargingInformationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void sendChargingInformationRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding sendChargingInformationRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding sendChargingInformationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        SendChargingInformationRequestImpl sendChargingInformationRequestIndication = new SendChargingInformationRequestImpl();
        sendChargingInformationRequestIndication.decodeData(asnInputStream, buf.length);

        sendChargingInformationRequestIndication.setInvokeId(invokeId);
        sendChargingInformationRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(sendChargingInformationRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onSendChargingInformationRequest(sendChargingInformationRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing sendChargingInformationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void specializedResourceReportRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding specializedResourceReportRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (capDialogCircuitSwitchedCall.getApplicationContext().getVersion().getVersion() < 4) {
            if (parameter.getTag() != Tag.NULL || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
                throw new CAPParsingComponentException("Error while decoding specializedResourceReportRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !parameter.isPrimitive())
                throw new CAPParsingComponentException("Error while decoding specializedResourceReportRequest: Bad tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        SpecializedResourceReportRequestImpl specializedResourceReportRequestIndication = new SpecializedResourceReportRequestImpl(capDialogCircuitSwitchedCall
                .getApplicationContext().getVersion().getVersion() >= 4);
        specializedResourceReportRequestIndication.decodeData(asnInputStream, buf.length);

        specializedResourceReportRequestIndication.setInvokeId(invokeId);
        specializedResourceReportRequestIndication.setLinkedId(linkedId);
        specializedResourceReportRequestIndication.setLinkedInvoke(linkedInvoke);
        specializedResourceReportRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(specializedResourceReportRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onSpecializedResourceReportRequest(specializedResourceReportRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing specializedResourceReportRequest: " + e.getMessage(), e);
            }
        }
    }

    private void playAnnouncementRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding playAnnouncementRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding playAnnouncementRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        PlayAnnouncementRequestImpl playAnnouncementRequestIndication = new PlayAnnouncementRequestImpl();
        playAnnouncementRequestIndication.decodeData(asnInputStream, buf.length);

        playAnnouncementRequestIndication.setInvokeId(invokeId);
        playAnnouncementRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(playAnnouncementRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onPlayAnnouncementRequest(playAnnouncementRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing playAnnouncementRequest: " + e.getMessage(), e);
            }
        }
    }

    private void promptAndCollectUserInformationRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding promptAndCollectUserInformationRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding playAnnouncementRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        PromptAndCollectUserInformationRequestImpl promptAndCollectUserInformationRequestIndication = new PromptAndCollectUserInformationRequestImpl();
        promptAndCollectUserInformationRequestIndication.decodeData(asnInputStream, buf.length);

        promptAndCollectUserInformationRequestIndication.setInvokeId(invokeId);
        promptAndCollectUserInformationRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(promptAndCollectUserInformationRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onPromptAndCollectUserInformationRequest(promptAndCollectUserInformationRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing promptAndCollectUserInformationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void promptAndCollectUserInformationResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall,
            Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding promptAndCollectUserInformationResponse: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
            throw new CAPParsingComponentException("Error while decoding promptAndCollectUserInformationResponse: bad tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        PromptAndCollectUserInformationResponseImpl promptAndCollectUserInformationResponseIndication = new PromptAndCollectUserInformationResponseImpl();
        promptAndCollectUserInformationResponseIndication.decodeData(asnInputStream, buf.length);

        promptAndCollectUserInformationResponseIndication.setInvokeId(invokeId);
        promptAndCollectUserInformationResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(promptAndCollectUserInformationResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onPromptAndCollectUserInformationResponse(promptAndCollectUserInformationResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing promptAndCollectUserInformationResponse: " + e.getMessage(), e);
            }
        }
    }

    private void moveLegRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding moveLegRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.getTag() != Tag.SEQUENCE || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding moveLegRequest: bad tagClass or tag",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        MoveLegRequestImpl moveLegRequestIndication = new MoveLegRequestImpl();

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        moveLegRequestIndication.decodeData(asnInputStream, buf.length);

        moveLegRequestIndication.setInvokeId(invokeId);
        moveLegRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(moveLegRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onMoveLegRequest(moveLegRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing moveLegRequest: " + e.getMessage(), e);
            }
        }
    }

    private void moveLegResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        MoveLegResponseImpl moveLegResponseIndication = new MoveLegResponseImpl();

        moveLegResponseIndication.setInvokeId(invokeId);
        moveLegResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(moveLegResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onMoveLegResponse(moveLegResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing moveLegResponse: " + e.getMessage(), e);
            }
        }
    }

    private void splitLegRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding splitLegRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.getTag() != Tag.SEQUENCE || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding splitLegRequest: bad tagClass or tag",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        SplitLegRequestImpl splitLegRequestIndication = new SplitLegRequestImpl();

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf);
        splitLegRequestIndication.decodeData(asnInputStream, buf.length);

        splitLegRequestIndication.setInvokeId(invokeId);
        splitLegRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(splitLegRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onSplitLegRequest(splitLegRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing splitLegRequest: " + e.getMessage(), e);
            }
        }
    }

    private void splitLegResponse(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        SplitLegResponseImpl splitLegResponseIndication = new SplitLegResponseImpl();

        splitLegResponseIndication.setInvokeId(invokeId);
        splitLegResponseIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(splitLegResponseIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onSplitLegResponse(splitLegResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing splitLegResponse: " + e.getMessage(), e);
            }
        }
    }

    private void cancelRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding cancelRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
            throw new CAPParsingComponentException("Error while decoding cancelRequest: bad tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream asnInputStream = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        CancelRequestImpl cancelRequestIndication = new CancelRequestImpl();
        cancelRequestIndication.decodeData(asnInputStream, buf.length);

        cancelRequestIndication.setInvokeId(invokeId);
        cancelRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(cancelRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onCancelRequest(cancelRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing cancelRequest: " + e.getMessage(), e);
            }
        }
    }

    private void collectInformationRequest(Parameter parameter, CAPDialogCircuitSwitchedCallImpl capDialogCircuitSwitchedCall, Long invokeId)
            throws CAPParsingComponentException {

        CollectInformationRequestImpl collectInformationRequestIndication = new CollectInformationRequestImpl();

        collectInformationRequestIndication.setInvokeId(invokeId);
        collectInformationRequestIndication.setCAPDialog(capDialogCircuitSwitchedCall);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(collectInformationRequestIndication);
                ((CAPServiceCircuitSwitchedCallListener) serLis).onCollectInformationRequest(collectInformationRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing collectInformationRequest: " + e.getMessage(), e);
            }
        }
    }
}
