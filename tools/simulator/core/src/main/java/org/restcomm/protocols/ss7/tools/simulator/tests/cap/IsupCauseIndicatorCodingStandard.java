
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

/**
 *
 * IsupCauseIndicatorCodingStandard ::= ENUMERATED { ITUT (0), IOS_IEC(1),
 * national(2), specific(3); }
 *
 * @author mnowa
 *
 *@see Q.850
 */
public enum IsupCauseIndicatorCodingStandard {
    ITUT(0), IOS_IEC(1), national(2), specific(3);

    private int code;

    private IsupCauseIndicatorCodingStandard(int code) {
        this.code = code;
    }

    public static IsupCauseIndicatorCodingStandard getInstance(int code) {
        switch (code) {
            case 0:
                return ITUT;
            case 1:
                return IOS_IEC;
            case 2:
                return national;
            case 3:
                return specific;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
