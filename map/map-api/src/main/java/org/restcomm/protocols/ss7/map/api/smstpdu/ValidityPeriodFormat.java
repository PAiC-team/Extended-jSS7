package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 * The TP-Validity-Period-Format is a 2-bit field, located within bit no 3 and 4 of the first octet of SMS-SUBMIT, and to be
 * given the following values: bit4 bit3 0 0 TP-VP field not present 1 0 TP-VP field present - relative format 0 1 TP-VP field
 * present - enhanced format 1 1 TP-VP field present - absolute format
 *
 * @author sergey vetyutnev
 *
 */
public enum ValidityPeriodFormat {

    fieldNotPresent(0), fieldPresentEnhancedFormat(1), fieldPresentRelativeFormat(2), fieldPresentAbsoluteFormat(3);

    private int code;

    private ValidityPeriodFormat(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ValidityPeriodFormat getInstance(int code) {
        switch (code) {
            case 0:
                return fieldNotPresent;
            case 1:
                return fieldPresentEnhancedFormat;
            case 2:
                return fieldPresentRelativeFormat;
            default:
                return fieldPresentAbsoluteFormat;
        }
    }
}
