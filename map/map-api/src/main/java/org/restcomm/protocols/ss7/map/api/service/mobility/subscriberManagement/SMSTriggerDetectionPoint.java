
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 SMS-TriggerDetectionPoint ::= ENUMERATED { sms-CollectedInfo (1), ..., sms-DeliveryRequest (2) } -- exception handling: --
 * For SMS-CAMEL-TDP-Data and MT-smsCAMELTDP-Criteria sequences containing this -- parameter with any other value than the ones
 * listed the receiver shall ignore -- the whole sequence. -- -- If this parameter is received with any other value than
 * sms-CollectedInfo -- in an SMS-CAMEL-TDP-Data sequence contained in mo-sms-CSI, then the receiver shall -- ignore the whole
 * SMS-CAMEL-TDP-Data sequence. -- -- If this parameter is received with any other value than sms-DeliveryRequest -- in an
 * SMS-CAMEL-TDP-Data sequence contained in mt-sms-CSI then the receiver shall -- ignore the whole SMS-CAMEL-TDP-Data sequence.
 * -- -- If this parameter is received with any other value than sms-DeliveryRequest -- in an MT-smsCAMELTDP-Criteria sequence
 * then the receiver shall -- ignore the whole MT-smsCAMELTDP-Criteria sequence.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum SMSTriggerDetectionPoint {
    smsCollectedInfo(1), smsDeliveryRequest(2);

    private int code;

    private SMSTriggerDetectionPoint(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static SMSTriggerDetectionPoint getInstance(int code) {
        switch (code) {
            case 1:
                return SMSTriggerDetectionPoint.smsCollectedInfo;
            case 2:
                return SMSTriggerDetectionPoint.smsDeliveryRequest;
            default:
                return null;
        }
    }
}
