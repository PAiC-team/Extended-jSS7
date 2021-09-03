
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 GPRS-TriggerDetectionPoint ::= ENUMERATED { attach (1), attachChangeOfPosition (2), pdp-ContextEstablishment (11),
 * pdp-ContextEstablishmentAcknowledgement (12), pdp-ContextChangeOfPosition (14), ... } -- exception handling: -- For
 * GPRS-CamelTDPData sequences containing this parameter with any -- other value than the ones listed the receiver shall ignore
 * the whole -- GPRS-CamelTDPDatasequence.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum GPRSTriggerDetectionPoint {
    attach(1), attachChangeOfPosition(2), pdpContextEstablishment(11), pdpContextEstablishmentAcknowledgement(12), pdpContextChangeOfPosition(
            14);

    private int code;

    private GPRSTriggerDetectionPoint(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static GPRSTriggerDetectionPoint getInstance(int code) {
        switch (code) {
            case 1:
                return GPRSTriggerDetectionPoint.attach;
            case 2:
                return GPRSTriggerDetectionPoint.attachChangeOfPosition;
            case 11:
                return GPRSTriggerDetectionPoint.pdpContextEstablishment;
            case 12:
                return GPRSTriggerDetectionPoint.pdpContextEstablishmentAcknowledgement;
            case 14:
                return GPRSTriggerDetectionPoint.pdpContextChangeOfPosition;
            default:
                return null;
        }
    }
}
