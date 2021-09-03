
package org.restcomm.protocols.ss7.oam.common.alarm;

/**
 * Severity of an alarm
 *
 * @author sergey vetyutnev
 *
 */
public enum AlarmSeverity {
    warning(1), minor(5), major(6), critical(7);

    private int code;

    private AlarmSeverity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
