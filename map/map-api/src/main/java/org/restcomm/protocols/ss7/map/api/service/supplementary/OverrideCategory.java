
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 OverrideCategory ::= ENUMERATED { overrideEnabled (0), overrideDisabled (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum OverrideCategory {

    overrideEnabled(0), overrideDisabled(1);

    private int code;

    private OverrideCategory(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static OverrideCategory getInstance(int code) {
        switch (code) {
            case 0:
                return OverrideCategory.overrideEnabled;
            case 1:
                return OverrideCategory.overrideDisabled;
            default:
                return null;
        }
    }
}