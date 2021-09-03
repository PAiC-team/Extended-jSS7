
package org.restcomm.protocols.ss7.oam.common.statistics.api;

/**
 * Created by mniemiec on 08.05.17.
 */
public enum CounterOutputFormat {
    CSV (0),
    VERBOSE (1),
    CSV_AND_VERBOSE (2);

    private int code;

    CounterOutputFormat(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CounterOutputFormat getInstance(int code) {
        switch (code) {
            case 0:
                return CounterOutputFormat.CSV;
            case 1:
                return CounterOutputFormat.VERBOSE;
            case 2:
                return CounterOutputFormat.CSV_AND_VERBOSE;
            default:
                return null;
        }
    }
}
