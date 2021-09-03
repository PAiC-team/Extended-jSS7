
package org.restcomm.protocols.ss7.sccp;

/**
 * LoadSharingAlgorithm defines bit in SLS to share message between Primary Address and Secondary Address
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public enum LoadSharingAlgorithm {

    Undefined("Undefined"), Bit0("Bit0"), Bit1("Bit1"), Bit2("Bit2"), Bit3("Bit3"), Bit4("Bit4");

    private static final String UNDEFINED = "Undefined";
    private static final String BIT_0 = "Bit0";
    private static final String BIT_1 = "Bit1";
    private static final String BIT_2 = "Bit2";
    private static final String BIT_3 = "Bit3";
    private static final String BIT_4 = "Bit4";

    private final String algo;

    private LoadSharingAlgorithm(String type) {
        this.algo = type;
    }

    public static LoadSharingAlgorithm getInstance(String type) {
        if (UNDEFINED.equalsIgnoreCase(type)) {
            return Undefined;
        } else if (BIT_0.equalsIgnoreCase(type)) {
            return Bit0;
        } else if (BIT_1.equalsIgnoreCase(type)) {
            return Bit1;
        } else if (BIT_2.equalsIgnoreCase(type)) {
            return Bit2;
        } else if (BIT_3.equalsIgnoreCase(type)) {
            return Bit3;
        } else if (BIT_4.equalsIgnoreCase(type)) {
            return Bit4;
        }

        return null;
    }

    public String getValue() {
        return this.algo;
    }
}
