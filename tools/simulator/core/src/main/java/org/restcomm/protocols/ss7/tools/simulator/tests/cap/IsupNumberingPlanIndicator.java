
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

/**
 *
 * IsupNatureOfAddressIndicator ::= ENUMERATED { spare_0 (0), ISDN(1),
 * spare_2(2), data(3), telex(4), reservedForNationalUse_5(5),
 * reservedForNationalUse_6(5), spare_7(7); }
 *
 * @author mnowa
 *
 */
public enum IsupNumberingPlanIndicator {
    spare_0(0), ISDN(1), spare_2(2), data(3), telex(4), reservedForNationalUse_5(5),
    reservedForNationalUse_6(6), spare_7(7);

    private int code;

    private IsupNumberingPlanIndicator(int code) {
        this.code = code;
    }

    public static IsupNumberingPlanIndicator getInstance(int code) {
        switch (code) {
            case 0:
                return spare_0;
            case 1:
                return IsupNumberingPlanIndicator.ISDN;
            case 2:
                return IsupNumberingPlanIndicator.spare_2;
            case 3:
                return IsupNumberingPlanIndicator.data;
            case 4:
                return IsupNumberingPlanIndicator.telex;
            case 5:
                return IsupNumberingPlanIndicator.reservedForNationalUse_5;
            case 6:
                return IsupNumberingPlanIndicator.reservedForNationalUse_6;
            case 7:
                return IsupNumberingPlanIndicator.spare_7;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
