
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
 *
<code>
ReportInterval ::= ENUMERATED {
  umts250ms (0),
  umts500ms (1),
  umts1000ms (2),
  umts2000ms (3),
  umts3000ms (4),
  umts4000ms (5),
  umts6000ms (6),
  umts8000ms (7),
  umts12000ms (8),
  umts16000ms (9),
  umts20000ms (10),
  umts24000ms (11),
  umts28000ms (12),
  umts32000ms (13),
  umts64000ms (14),
  lte120ms (15),
  lte240ms (16),
  lte480ms (17),
  lte640ms (18),
  lte1024ms (19),
  lte2048ms (20),
  lte5120ms (21),
  lte10240ms (22),
  lte1min (23),
  lte6min (24),
  lte12min (25),
  lte30min (26),
  lte60min (27)
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ReportInterval {

    umts250ms(0), umts500ms(1), umts1000ms(2), umts2000ms(3), umts3000ms(4), umts4000ms(5), umts6000ms(6),
    umts8000ms(7), umts12000ms(8), umts16000ms(9), umts20000ms(10), umts24000ms(11), umts28000ms(12),
    umts32000ms(13), umts64000ms(14), lte120ms(15), lte240ms(16), lte480ms(17), lte640ms(18),
    lte1024ms(19), lte2048ms(20), lte5120ms(21), lte10240ms(22), lte1min(23), lte6min(24),
    lte12min(25), lte30min(26), lte60min(27);

    private int code;

    private ReportInterval(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ReportInterval getInstance(int code) {
        switch (code) {
            case 0:
                return ReportInterval.umts250ms;
            case 1:
                return ReportInterval.umts500ms;
            case 2:
                return ReportInterval.umts1000ms;
            case 3:
                return ReportInterval.umts2000ms;
            case 4:
                return ReportInterval.umts3000ms;
            case 5:
                return ReportInterval.umts4000ms;
            case 6:
                return ReportInterval.umts6000ms;
            case 7:
                return ReportInterval.umts8000ms;
            case 8:
                return ReportInterval.umts12000ms;
            case 9:
                return ReportInterval.umts16000ms;
            case 10:
                return ReportInterval.umts20000ms;
            case 11:
                return ReportInterval.umts24000ms;
            case 12:
                return ReportInterval.umts28000ms;
            case 13:
                return ReportInterval.umts32000ms;
            case 14:
                return ReportInterval.umts64000ms;
            case 15:
                return ReportInterval.lte120ms;
            case 16:
                return ReportInterval.lte240ms;
            case 17:
                return ReportInterval.lte480ms;
            case 18:
                return ReportInterval.lte640ms;
            case 19:
                return ReportInterval.lte1024ms;
            case 20:
                return ReportInterval.lte2048ms;
            case 21:
                return ReportInterval.lte5120ms;
            case 22:
                return ReportInterval.lte10240ms;
            case 23:
                return ReportInterval.lte1min;
            case 24:
                return ReportInterval.lte6min;
            case 25:
                return ReportInterval.lte12min;
            case 26:
                return ReportInterval.lte30min;
            case 27:
                return ReportInterval.lte60min;
            default:
                return null;
        }
    }
}
