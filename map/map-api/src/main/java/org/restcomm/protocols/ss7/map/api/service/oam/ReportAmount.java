
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
 *
<code>
ReportAmount ::= ENUMERATED {
  d1 (0),
  d2 (1),
  d4 (2),
  d8 (3),
  d16 (4),
  d32 (5),
  d64 (6),
  infinity (7)
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ReportAmount {

    d1(0), d2(1), d4(2), d8(3), d16(4), d32(5), d64(6), infinity(7);

    private int code;

    private ReportAmount(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ReportAmount getInstance(int code) {
        switch (code) {
            case 0:
                return ReportAmount.d1;
            case 1:
                return ReportAmount.d2;
            case 2:
                return ReportAmount.d4;
            case 3:
                return ReportAmount.d8;
            case 4:
                return ReportAmount.d16;
            case 5:
                return ReportAmount.d32;
            case 6:
                return ReportAmount.d64;
            case 7:
                return ReportAmount.infinity;
            default:
                return null;
        }
    }
}
