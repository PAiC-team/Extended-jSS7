
package org.restcomm.protocols.ss7.cap.api.primitives;

/**
 *
 TimerID ::= ENUMERATED { tssf (0) } -- Indicates the timer to be reset.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum TimerID {

    tssf(0);

    private int code;

    private TimerID(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TimerID getInstance(int code) {
        switch (code) {
            case 0:
                return TimerID.tssf;
            default:
                return null;
        }
    }
}