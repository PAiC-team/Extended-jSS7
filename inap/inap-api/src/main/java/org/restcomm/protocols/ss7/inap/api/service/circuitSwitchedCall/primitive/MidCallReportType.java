package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
 *
 <code>
ENUMERATED { inMonitoringState (0), inAnyState (1) }
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MidCallReportType {

    inMonitoringState(0), inAnyState(1);

    private int code;

    private MidCallReportType(int code) {
        this.code = code;
    }

    public static MidCallReportType getInstance(int code) {
        switch (code) {
            case 0:
                return MidCallReportType.inMonitoringState;
            case 1:
                return MidCallReportType.inAnyState;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
