package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * SM-DeliveryFailureCause ::= SEQUENCE { sm-EnumeratedDeliveryFailureCause SM-EnumeratedDeliveryFailureCause, diagnosticInfo
 * SignalInfo OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum SMEnumeratedDeliveryFailureCause {

    memoryCapacityExceeded(0), equipmentProtocolError(1), equipmentNotSMEquipped(2), unknownServiceCentre(3),
    scCongestion(4), invalidSMEAddress(5), subscriberNotSCSubscriber(6);

    private int code;

    private SMEnumeratedDeliveryFailureCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SMEnumeratedDeliveryFailureCause getInstance(int code) {
        switch (code) {
            case 0:
                return memoryCapacityExceeded;
            case 1:
                return equipmentProtocolError;
            case 2:
                return equipmentNotSMEquipped;
            case 3:
                return unknownServiceCentre;
            case 4:
                return scCongestion;
            case 5:
                return invalidSMEAddress;
            case 6:
                return subscriberNotSCSubscriber;
            default:
                return null;
        }
    }
}
