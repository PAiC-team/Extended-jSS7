
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

/**
 *
 EventTypeSMS ::= ENUMERATED { sms-CollectedInfo (1), o-smsFailure (2), o-smsSubmission (3), sms-DeliveryRequested (11),
 * t-smsFailure (12), t-smsDelivery (13) } -- Values sms-CollectedInfo and sms-DeliveryRequested may be used for TDPs only.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum EventTypeSMS {

    smsCollectedInfo(1), oSmsFailure(2), oSmsSubmission(3), smsDeliveryRequested(11), tSmsFailure(12), tSmsDelivery(13);

    private int code;

    private EventTypeSMS(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static EventTypeSMS getInstance(int code) {
        switch (code) {
            case 1:
                return EventTypeSMS.smsCollectedInfo;
            case 2:
                return EventTypeSMS.oSmsFailure;
            case 3:
                return EventTypeSMS.oSmsSubmission;
            case 11:
                return EventTypeSMS.smsDeliveryRequested;
            case 12:
                return EventTypeSMS.tSmsFailure;
            case 13:
                return EventTypeSMS.tSmsDelivery;
            default:
                return null;
        }
    }

}
