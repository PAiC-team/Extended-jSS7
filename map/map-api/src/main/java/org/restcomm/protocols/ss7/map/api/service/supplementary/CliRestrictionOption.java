
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 CliRestrictionOption ::= ENUMERATED { permanent (0), temporaryDefaultRestricted (1), temporaryDefaultAllowed (2)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CliRestrictionOption {

    permanent(0), temporaryDefaultRestricted(1), temporaryDefaultAllowed(2);

    private int code;

    private CliRestrictionOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CliRestrictionOption getInstance(int code) {
        switch (code) {
            case 0:
                return CliRestrictionOption.permanent;
            case 1:
                return CliRestrictionOption.temporaryDefaultRestricted;
            case 2:
                return CliRestrictionOption.temporaryDefaultAllowed;
            default:
                return null;
        }
    }
}
