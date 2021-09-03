
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 RoamingNotAllowedCause ::= ENUMERATED { plmnRoamingNotAllowed (0), operatorDeterminedBarring (3)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RoamingNotAllowedCause {

    plmnRoamingNotAllowed(0), operatorDeterminedBarring(3);

    private int code;

    private RoamingNotAllowedCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RoamingNotAllowedCause getInstance(int code) {
        switch (code) {
            case 0:
                return plmnRoamingNotAllowed;
            case 3:
                return operatorDeterminedBarring;
            default:
                return null;
        }
    }
}
