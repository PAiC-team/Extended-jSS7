
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 LSAOnlyAccessIndicator ::= ENUMERATED { accessOutsideLSAsAllowed (0), accessOutsideLSAsRestricted (1)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum LSAOnlyAccessIndicator {
    accessOutsideLSAsAllowed(0), accessOutsideLSAsRestricted(1);

    private int code;

    private LSAOnlyAccessIndicator(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static LSAOnlyAccessIndicator getInstance(int code) {
        switch (code) {
            case 0:
                return LSAOnlyAccessIndicator.accessOutsideLSAsAllowed;
            case 1:
                return LSAOnlyAccessIndicator.accessOutsideLSAsRestricted;
            default:
                return null;
        }
    }
}
