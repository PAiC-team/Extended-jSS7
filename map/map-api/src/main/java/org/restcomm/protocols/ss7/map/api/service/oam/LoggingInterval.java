
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
 *
<code>
LoggingInterval ::= ENUMERATED {
  d1dot28 (0),
  d2dot56 (1),
  d5dot12 (2),
  d10dot24 (3),
  d20dot48 (4),
  d30dot72 (5),
  d40dot96 (6),
  d61dot44 (7)
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum LoggingInterval {

    d1dot28(0), d2dot56(1), d5dot12(2), d10dot24(3), d20dot48(4), d30dot72(5), d40dot96(6), d61dot44(7);

    private int code;

    private LoggingInterval(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static LoggingInterval getInstance(int code) {
        switch (code) {
            case 0:
                return LoggingInterval.d1dot28;
            case 1:
                return LoggingInterval.d2dot56;
            case 2:
                return LoggingInterval.d5dot12;
            case 3:
                return LoggingInterval.d10dot24;
            case 4:
                return LoggingInterval.d20dot48;
            case 5:
                return LoggingInterval.d30dot72;
            case 6:
                return LoggingInterval.d40dot96;
            case 7:
                return LoggingInterval.d61dot44;
            default:
                return null;
        }
    }
}
