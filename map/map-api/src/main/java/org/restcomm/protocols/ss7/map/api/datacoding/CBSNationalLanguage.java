
package org.restcomm.protocols.ss7.map.api.datacoding;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CBSNationalLanguage {

    German(0), English(1), Italian(2), French(3), Spanish(4), Dutch(5), Swedish(6),
    Danish(7), Portuguese(8), Finnish(9), Norwegian(10), Greek(11), Turkish(12), Hungarian(13),
    Polish(14), LanguageUnspecified(15), Czech(32 + 0), Hebrew(32 + 1), Arabic(32 + 2), Russian(32 + 3),
    Icelandic(32 + 4);

    private int code;

    private CBSNationalLanguage(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getCBSCodingGroupBits() {
        return (code & 0xF0) >> 4;
    }

    public int getCBSLowBits() {
        return (code & 0x0F);
    }

    public static CBSNationalLanguage getInstance(int codingGroupBits, int lowBits) {
        if (codingGroupBits == 0) {
            switch (lowBits) {
                case 0:
                    return German;
                case 1:
                    return English;
                case 2:
                    return Italian;
                case 3:
                    return French;
                case 4:
                    return Spanish;
                case 5:
                    return Dutch;
                case 6:
                    return Swedish;
                case 7:
                    return Danish;
                case 8:
                    return Portuguese;
                case 9:
                    return Finnish;
                case 10:
                    return Norwegian;
                case 11:
                    return Greek;
                case 12:
                    return Turkish;
                case 13:
                    return Hungarian;
                case 14:
                    return Polish;
                default:
                    return LanguageUnspecified;
            }
        } else if (codingGroupBits == 2) {
            switch (lowBits) {
                case 0:
                    return Czech;
                case 1:
                    return Hebrew;
                case 2:
                    return Arabic;
                case 3:
                    return Russian;
                case 4:
                    return Icelandic;
                default:
                    return LanguageUnspecified;
            }
        } else {
            return LanguageUnspecified;
        }
    }
}
