
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceGprsListener extends CAPServiceListener {

    void onInitialDpGprsRequest(InitialDpGprsRequest initialDpGprsRequestIndication);

    void onRequestReportGPRSEventRequest(RequestReportGPRSEventRequest requestReportGPRSEventRequestIndication);

    void onApplyChargingGPRSRequest(ApplyChargingGPRSRequest applyChargingGPRSRequestInd);

    void onEntityReleasedGPRSRequest(EntityReleasedGPRSRequest entityReleasedGPRSRequestInd);

    void onEntityReleasedGPRSResponse(EntityReleasedGPRSResponse entityReleasedGPRSResponseIndication);

    void onConnectGPRSRequest(ConnectGPRSRequest connectGPRSRequestIndication);

    void onContinueGPRSRequest(ContinueGPRSRequest continueGPRSRequestIndication);

    void onReleaseGPRSRequest(ReleaseGPRSRequest releaseGPRSRequestIndication);

    void onResetTimerGPRSRequest(ResetTimerGPRSRequest resetTimerGPRSRequestIndication);

    void onFurnishChargingInformationGPRSRequest(FurnishChargingInformationGPRSRequest furnishChargingInformationGPRSRequestIndication);

    void onCancelGPRSRequest(CancelGPRSRequest cancelGPRSRequestIndication);

    void onSendChargingInformationGPRSRequest(SendChargingInformationGPRSRequest sendChargingInformationGPRSRequestIndication);

    void onApplyChargingReportGPRSRequest(ApplyChargingReportGPRSRequest applyChargingReportGPRSRequestIndication);

    void onApplyChargingReportGPRSResponse(ApplyChargingReportGPRSResponse applyChargingReportGPRSResponseIndication);

    void onEventReportGPRSRequest(EventReportGPRSRequest eventReportGPRSRequestIndication);

    void onEventReportGPRSResponse(EventReportGPRSResponse eventReportGPRSResponseIndication);

    void onActivityTestGPRSRequest(ActivityTestGPRSRequest activityTestGPRSRequestIndication);

    void onActivityTestGPRSResponse(ActivityTestGPRSResponse activityTestGPRSResponseIndication);

}