
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

/**
 *
 GPRSEventType ::= ENUMERATED { attach (1), attachChangeOfPosition (2), detached (3), pdp-ContextEstablishment (11),
 * pdp-ContextEstablishmentAcknowledgement (12), disonnect (13), pdp-ContextChangeOfPosition (14) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum GPRSEventType {

    attach(1), attachChangeOfPosition(2), detached(3), pdpContextEstablishment(11), pdpContextEstablishmentAcknowledgement(12),
    disconnect(13), pdpContextChangeOfPosition(14);

    private int code;

    private GPRSEventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static GPRSEventType getInstance(int code) {
        switch (code) {
            case 1:
                return GPRSEventType.attach;
            case 2:
                return GPRSEventType.attachChangeOfPosition;
            case 3:
                return GPRSEventType.detached;
            case 11:
                return GPRSEventType.pdpContextEstablishment;
            case 12:
                return GPRSEventType.pdpContextEstablishmentAcknowledgement;
            case 13:
                return GPRSEventType.disconnect;
            case 14:
                return GPRSEventType.pdpContextChangeOfPosition;
            default:
                return null;
        }
    }

}
