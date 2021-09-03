
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 ReportingState ::= ENUMERATED { stopMonitoring (0), startMonitoring (1), ...} -- exception handling: -- reception of values
 * 2-10 shall be mapped to 'stopMonitoring' -- reception of values > 10 shall be mapped to 'startMonitoring'
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ReportingState {

    stopMonitoring(0), startMonitoring(1);

    private int code;

    private ReportingState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ReportingState getInstance(int code) {
        if (code == 0 || code >= 2 && code <= 10)
            return ReportingState.stopMonitoring;
        else
            return ReportingState.startMonitoring;
    }
}
