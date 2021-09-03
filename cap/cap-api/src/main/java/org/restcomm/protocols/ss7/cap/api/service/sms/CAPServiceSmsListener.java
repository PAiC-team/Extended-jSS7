
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceSmsListener extends CAPServiceListener {

    void onConnectSMSRequest(ConnectSMSRequest connectSMSRequestIndication);

    void onEventReportSMSRequest(EventReportSMSRequest eventReportSMSRequestIndication);

    void onFurnishChargingInformationSMSRequest(FurnishChargingInformationSMSRequest furnishChargingInformationSMSRequestIndication);

    void onInitialDPSMSRequest(InitialDPSMSRequest initialDPSMSRequestIndication);

    void onReleaseSMSRequest(ReleaseSMSRequest releaseSMSRequestIndication);

    void onRequestReportSMSEventRequest(RequestReportSMSEventRequest requestReportSMSEventRequestIndication);

    void onResetTimerSMSRequest(ResetTimerSMSRequest resetTimerSMSRequestIndication);

    void onContinueSMSRequest(ContinueSMSRequest continueSMSRequestIndication);

}
