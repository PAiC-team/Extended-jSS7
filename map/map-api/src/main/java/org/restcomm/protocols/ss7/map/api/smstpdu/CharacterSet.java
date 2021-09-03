package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CharacterSet {

    GSM7(0), GSM8(1), UCS2(2), Reserved(3);

    private int code;

    private CharacterSet(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CharacterSet getInstance(int code) {
        switch (code) {
            case 0:
                return GSM7;
            case 1:
                return GSM8;
            case 2:
                return UCS2;
            default:
                return Reserved;
        }
    }
}