
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 PW-RegistrationFailureCause ::= ENUMERATED { undetermined (0), invalidFormat (1), newPasswordsMismatch (2)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum PWRegistrationFailureCause {

    undetermined(0), invalidFormat(1), newPasswordsMismatch(2);

    private int code;

    private PWRegistrationFailureCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PWRegistrationFailureCause getInstance(int code) {
        switch (code) {
            case 0:
                return undetermined;
            case 1:
                return invalidFormat;
            case 2:
                return newPasswordsMismatch;
            default:
                return null;
        }
    }
}
