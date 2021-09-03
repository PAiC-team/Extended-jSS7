
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
 *
<code>
LoggingDuration ::= ENUMERATED {
  d600sec (0),
  d1200sec (1),
  d2400sec (2),
  d3600sec (3),
  d5400sec (4),
  d7200sec (5)
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum LoggingDuration {

    d600sec(0), d1200sec(1), d2400sec(2), d3600sec(3), d5400sec(4), d7200sec(5);

    private int code;

    private LoggingDuration(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static LoggingDuration getInstance(int code) {
        switch (code) {
            case 0:
                return LoggingDuration.d600sec;
            case 1:
                return LoggingDuration.d1200sec;
            case 2:
                return LoggingDuration.d2400sec;
            case 3:
                return LoggingDuration.d3600sec;
            case 4:
                return LoggingDuration.d5400sec;
            case 5:
                return LoggingDuration.d7200sec;
            default:
                return null;
        }
    }
}
