
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

/**
 *
 MO-SMSCause ::= ENUMERATED { systemFailure (0), unexpectedDataValue (1), facilityNotSupported (2), sM-DeliveryFailure (3),
 * releaseFromRadioInterface (4) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MOSMSCause {

    systemFailure(0), unexpectedDataValue(1), facilityNotSupported(2), sMDeliveryFailure(3), releaseFromRadioInterface(4);

    private int code;

    private MOSMSCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static MOSMSCause getInstance(int code) {
        switch (code) {
            case 0:
                return MOSMSCause.systemFailure;
            case 1:
                return MOSMSCause.unexpectedDataValue;
            case 2:
                return MOSMSCause.facilityNotSupported;
            case 3:
                return MOSMSCause.sMDeliveryFailure;
            case 4:
                return MOSMSCause.releaseFromRadioInterface;
            default:
                return null;
        }
    }

}
