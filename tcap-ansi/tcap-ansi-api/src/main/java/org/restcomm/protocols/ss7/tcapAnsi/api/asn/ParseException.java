
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.RejectProblem;

/**
 * Thrown to indicate problems at parse time.
 *
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class ParseException extends Exception {

    private PAbortCause pAbortCauseType;
    private RejectProblem problem;
    private Long invokeId;

    public ParseException(PAbortCause pAbortCauseType) {
        this.pAbortCauseType = pAbortCauseType;
    }

    public ParseException(RejectProblem problem) {
        this.problem = problem;
    }

    public ParseException(PAbortCause pAbortCauseType, String message) {
        super(message);
        this.pAbortCauseType = pAbortCauseType;
    }

    public ParseException(RejectProblem problem, String message) {
        super(message);
        this.problem = problem;
    }

    public ParseException(PAbortCause pAbortCauseType, Throwable cause) {
        super(cause);
        this.pAbortCauseType = pAbortCauseType;
    }

    public ParseException(RejectProblem problem, Throwable cause) {
        super(cause);
        this.problem = problem;
    }

    public ParseException(PAbortCause pAbortCauseType, String message, Throwable cause) {
        super(message, cause);
        this.pAbortCauseType = pAbortCauseType;
    }

    public ParseException(RejectProblem problem, String message, Throwable cause) {
        super(message, cause);
        this.problem = problem;
    }

    public PAbortCause getPAbortCauseType() {
        return pAbortCauseType;
    }

    public RejectProblem getProblem() {
        return problem;
    }

    public Long getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(Long val) {
        invokeId = val;
    }

}
