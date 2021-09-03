
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceSmsListener extends MAPServiceListener {

    void onForwardShortMessageRequest(ForwardShortMessageRequest forwardShortMessageRequestIndication);

    void onForwardShortMessageResponse(ForwardShortMessageResponse forwardShortMessageResponseIndication);

    void onMoForwardShortMessageRequest(MoForwardShortMessageRequest moForwardShortMessageRequestIndication);

    void onMoForwardShortMessageResponse(MoForwardShortMessageResponse moForwardShortMessageResponseIndication);

    void onMtForwardShortMessageRequest(MtForwardShortMessageRequest mtForwardShortMessageRequestIndication);

    void onMtForwardShortMessageResponse(MtForwardShortMessageResponse mtForwardShortMessageResponseIndication);

    void onSendRoutingInfoForSMRequest(SendRoutingInfoForSMRequest sendRoutingInfoForSMRequestIndication);

    void onSendRoutingInfoForSMResponse(SendRoutingInfoForSMResponse sendRoutingInfoForSMResponseIndication);

    void onReportSMDeliveryStatusRequest(ReportSMDeliveryStatusRequest reportSMDeliveryStatusRequestIndication);

    void onReportSMDeliveryStatusResponse(ReportSMDeliveryStatusResponse reportSMDeliveryStatusResponseIndication);

    void onInformServiceCentreRequest(InformServiceCentreRequest informServiceCentreRequestIndication);

    void onAlertServiceCentreRequest(AlertServiceCentreRequest alertServiceCentreRequestIndication);

    void onAlertServiceCentreResponse(AlertServiceCentreResponse alertServiceCentreResponseIndication);

    void onReadyForSMRequest(ReadyForSMRequest readyForSMRequest);

    void onReadyForSMResponse(ReadyForSMResponse readyForSMResponse);

    void onNoteSubscriberPresentRequest(NoteSubscriberPresentRequest noteSubscriberPresentRequest);

}
