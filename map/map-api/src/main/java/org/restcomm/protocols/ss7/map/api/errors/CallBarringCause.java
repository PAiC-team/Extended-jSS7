package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * CallBarringCause ::= ENUMERATED { barringServiceActive (0), operatorBarring (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CallBarringCause {

    barringServiceActive(0), operatorBarring(1);

    private int code;

    private CallBarringCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CallBarringCause getInstance(int code) {
        switch (code) {
            case 0:
                return barringServiceActive;
            case 1:
                return operatorBarring;
            default:
                return null;
        }
    }

}
