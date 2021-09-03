
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
 ControlType ::= ENUMERATED { sCPOverloaded (0), manuallyInitiated (1) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ControlType {

    sCPOverloaded(0), manuallyInitiated(1);

    private int code;

    private ControlType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ControlType getInstance(int code) {
        switch (code) {
            case 0:
                return ControlType.sCPOverloaded;
            case 1:
                return ControlType.manuallyInitiated;
            default:
                return null;
        }
    }
}
