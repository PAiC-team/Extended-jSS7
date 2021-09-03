
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
 *
<code>
JobType ::= ENUMERATED {
  immediate-MDT-only (0),
  logged-MDT-only (1),
  trace-only (2),
  immediate-MDT-and-trace (3)
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum JobType {

    immediateMdtOnly(0), loggedMdtOnly(1), traceOnly(2), immediateMdtAndTrace(3);

    private int code;

    private JobType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static JobType getInstance(int code) {
        switch (code) {
            case 0:
                return JobType.immediateMdtOnly;
            case 1:
                return JobType.loggedMdtOnly;
            case 2:
                return JobType.traceOnly;
            case 3:
                return JobType.immediateMdtAndTrace;
            default:
                return null;
        }
    }
}
