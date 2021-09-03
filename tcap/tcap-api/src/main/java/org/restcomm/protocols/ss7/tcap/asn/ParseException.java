
package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * Thrown to indicate problems at parse time.
 *
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class ParseException extends Exception {

    private GeneralProblemType generalProblemType;
    private PAbortCauseType providerAbortCauseType;
    private Long invokeId;

    public ParseException(PAbortCauseType providerAbortCauseType, GeneralProblemType generalProblemType) {
        this.generalProblemType = generalProblemType;
        this.providerAbortCauseType = providerAbortCauseType;
    }

    public ParseException(PAbortCauseType providerAbortCauseType, GeneralProblemType generalProblemType, String message) {
        super(message);
        this.generalProblemType = generalProblemType;
        this.providerAbortCauseType = providerAbortCauseType;
    }

    public ParseException(PAbortCauseType providerAbortCauseType, GeneralProblemType generalProblemType, Throwable cause) {
        super(cause);
        this.generalProblemType = generalProblemType;
        this.providerAbortCauseType = providerAbortCauseType;
    }

    public ParseException(PAbortCauseType providerAbortCauseType, GeneralProblemType generalProblemType, String message, Throwable cause) {
        super(message, cause);
        this.generalProblemType = generalProblemType;
        this.providerAbortCauseType = providerAbortCauseType;
    }

    public GeneralProblemType getProblem() {
        return generalProblemType;
    }

    public PAbortCauseType getPAbortCauseType() {
        return providerAbortCauseType;
    }

    public Long getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(Long val) {
        invokeId = val;
    }

}
