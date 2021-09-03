
package org.restcomm.protocols.ss7.map.api.datacoding;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum NationalLanguageIdentifier {

    Reserved(0), Turkish(1), Spanish(2), Portuguese(3), Bengali(4), Gujarati(5), Hindi(6),
    Kannada(7), Malayalam(8), Oriya(9), Punjabi(10), Tamil(11), Telugu(12), Urdu(13);

    private int code;

    private NationalLanguageIdentifier(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NationalLanguageIdentifier getInstance(int code) {
        switch (code) {
            case 1:
                return Turkish;
            case 2:
                return Spanish;
            case 3:
                return Portuguese;
            case 4:
                return Bengali;
            case 5:
                return Gujarati;
            case 6:
                return Hindi;
            case 7:
                return Kannada;
            case 8:
                return Malayalam;
            case 9:
                return Oriya;
            case 10:
                return Punjabi;
            case 11:
                return Tamil;
            case 12:
                return Telugu;
            case 13:
                return Urdu;
            default:
                return Reserved;
        }
    }
}
