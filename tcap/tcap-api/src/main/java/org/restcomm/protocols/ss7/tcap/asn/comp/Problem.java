
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface Problem extends Encodable {

    // this is a hell of a combo
    ProblemType getType();

    void setType(ProblemType problemType);

    // now depending on type, one of below values must not be null

    // mandatory, one for each type
    void setGeneralProblemType(GeneralProblemType generalProblemType);

    GeneralProblemType getGeneralProblemType();

    void setInvokeProblemType(InvokeProblemType invokeProblemType);

    InvokeProblemType getInvokeProblemType();

    void setReturnErrorProblemType(ReturnErrorProblemType returnErrorProblemType);

    ReturnErrorProblemType getReturnErrorProblemType();

    void setReturnResultProblemType(ReturnResultProblemType returnResultProblemType);

    ReturnResultProblemType getReturnResultProblemType();

}
