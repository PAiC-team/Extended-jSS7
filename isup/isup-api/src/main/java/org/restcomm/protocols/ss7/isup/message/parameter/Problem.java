package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;


/**
 * @author baranowb
 *
 */
public interface Problem extends Serializable{

    // this is a hell of a combo
    ProblemType getType();

    void setType(ProblemType t);

    // now depending on type, one of below values must not be null

    // mandatory, one for each type
    void setGeneralProblemType(GeneralProblemType t);

    GeneralProblemType getGeneralProblemType();

    void setInvokeProblemType(InvokeProblemType t);

    InvokeProblemType getInvokeProblemType();

    void setReturnErrorProblemType(ReturnErrorProblemType t);

    ReturnErrorProblemType getReturnErrorProblemType();

    void setReturnResultProblemType(ReturnResultProblemType t);

    ReturnResultProblemType getReturnResultProblemType();

}
