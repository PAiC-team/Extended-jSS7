
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 AdditionalRoamingNotAllowedCause ::= ENUMERATED { supportedRAT-TypesNotAllowed (0), ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AdditionalRoamingNotAllowedCause {

    supportedRATTypesNotAllowed(0);

    private int code;

    private AdditionalRoamingNotAllowedCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AdditionalRoamingNotAllowedCause getInstance(int code) {
        switch (code) {
            case 0:
                return supportedRATTypesNotAllowed;
            default:
                return null;
        }
    }
}
