
package org.restcomm.protocols.ss7.cap.functional;



/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 * @author tamas gyorgyey
 */
public enum EventType {
    // Dialog EventType
    DialogDelimiter, DialogRequest, DialogAccept, DialogUserAbort, DialogProviderAbort, DialogClose, DialogNotice, DialogRelease, DialogTimeout, // DialogReject,

    // Service EventType
    ErrorComponent, ProviderErrorComponent, RejectComponent, InvokeTimeout,

    // CircuitSwitchedCall EventType
    InitialDpRequest, ApplyChargingReportRequest, ApplyChargingRequest, CallInformationReportRequest, CallInformationRequestRequest, ConnectRequest, ContinueRequest, ContinueWithArgumentRequest, EventReportBCSMRequest, RequestReportBCSMEventRequest, ReleaseCallRequest, ActivityTestRequest, ActivityTestResponse, AssistRequestInstructionsRequest, EstablishTemporaryConnectionRequest, DisconnectForwardConnectionRequest, ConnectToResourceRequest, ResetTimerRequest, FurnishChargingInformationRequest, SendChargingInformationRequest, SpecializedResourceReportRequest, PlayAnnouncementRequest, PromptAndCollectUserInformationRequest, PromptAndCollectUserInformationResponse, CancelRequest, DisconnectLegRequest, DisconnectLegResponse, DisconnectForwardConnectionWithArgumentRequest, InitiateCallAttemptRequest, InitiateCallAttemptResponse, MoveLegRequest, MoveLegResponse, SplitLegRequest, SplitLegResponse, CollectInformationRequest, CallGapRequest,

    // gprs EventType
    InitialDpGprsRequest, RequestReportGPRSEventRequest, ApplyChargingGPRSRequest, EntityReleasedGPRSRequest, ConnectGPRSRequest, ContinueGPRSRequest, ReleaseGPRSRequest, ResetTimerGPRSRequest, FurnishChargingInformationGPRSRequest, CancelGPRSRequest, SendChargingInformationGPRSRequest, applyChargingReportGPRS, EventReportGPRSRequest, EventReportGPRSResponse, ApplyChargingReportGPRSResponse, ApplyChargingReportGPRSRequest, EntityReleasedGPRSResponse, ActivityTestGPRSRequest, ActivityTestGPRSResponse,

    // sms event Type
    ConnectSMSRequest, EventReportSMSRequest, FurnishChargingInformationSMSRequest, InitialDPSMSRequest, ReleaseSMSRequest, RequestReportSMSEventRequest, ResetTimerSMSRequest, ContinueSMSRequest
}
