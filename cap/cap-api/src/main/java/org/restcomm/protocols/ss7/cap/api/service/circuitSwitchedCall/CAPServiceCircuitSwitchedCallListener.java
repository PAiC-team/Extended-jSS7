
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceCircuitSwitchedCallListener extends CAPServiceListener {

    void onInitialDPRequest(InitialDPRequest ind);

    void onRequestReportBCSMEventRequest(RequestReportBCSMEventRequest ind);

    void onApplyChargingRequest(ApplyChargingRequest ind);

    void onEventReportBCSMRequest(EventReportBCSMRequest ind);

    void onContinueRequest(ContinueRequest ind);

    void onContinueWithArgumentRequest(ContinueWithArgumentRequest ind);

    void onApplyChargingReportRequest(ApplyChargingReportRequest ind);

    void onReleaseCallRequest(ReleaseCallRequest ind);

    void onConnectRequest(ConnectRequest ind);

    void onCallInformationRequestRequest(CallInformationRequestRequest ind);

    void onCallInformationReportRequest(CallInformationReportRequest ind);

    void onActivityTestRequest(ActivityTestRequest ind);

    void onActivityTestResponse(ActivityTestResponse ind);

    void onAssistRequestInstructionsRequest(AssistRequestInstructionsRequest ind);

    void onEstablishTemporaryConnectionRequest(EstablishTemporaryConnectionRequest ind);

    void onDisconnectForwardConnectionRequest(DisconnectForwardConnectionRequest ind);

    void onDisconnectLegRequest(DisconnectLegRequest ind);

    void onDisconnectLegResponse(DisconnectLegResponse ind);

    void onDisconnectForwardConnectionWithArgumentRequest(DisconnectForwardConnectionWithArgumentRequest ind);

    void onConnectToResourceRequest(ConnectToResourceRequest ind);

    void onResetTimerRequest(ResetTimerRequest ind);

    void onFurnishChargingInformationRequest(FurnishChargingInformationRequest ind);

    void onSendChargingInformationRequest(SendChargingInformationRequest ind);

    void onSpecializedResourceReportRequest(SpecializedResourceReportRequest ind);

    void onPlayAnnouncementRequest(PlayAnnouncementRequest ind);

    void onPromptAndCollectUserInformationRequest(PromptAndCollectUserInformationRequest ind);

    void onPromptAndCollectUserInformationResponse(PromptAndCollectUserInformationResponse ind);

    void onCancelRequest(CancelRequest ind);

    void onInitiateCallAttemptRequest(InitiateCallAttemptRequest initiateCallAttemptRequest);

    void onInitiateCallAttemptResponse(InitiateCallAttemptResponse initiateCallAttemptResponse);

    void onMoveLegRequest(MoveLegRequest ind);

    void onMoveLegResponse(MoveLegResponse ind);

    void onCollectInformationRequest(CollectInformationRequest ind);

    void onSplitLegRequest(SplitLegRequest ind);

    void onSplitLegResponse(SplitLegResponse ind);

    void onCallGapRequest(CallGapRequest ind);

}