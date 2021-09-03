package org.restcomm.protocols.ss7.map.api.service.sms;

/**
 *
 * SM-DeliveryOutcome ::= ENUMERATED { memoryCapacityExceeded (0), absentSubscriber (1), successfulTransfer (2)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum SMDeliveryOutcome {

    memoryCapacityExceeded(0), absentSubscriber(1), successfulTransfer(2);

    private int code;

    private SMDeliveryOutcome(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SMDeliveryOutcome getInstance(int code) {
        switch (code) {
            case 0:
                return memoryCapacityExceeded;
            case 1:
                return absentSubscriber;
            case 2:
                return successfulTransfer;
            default:
                return null;
        }
    }

}
