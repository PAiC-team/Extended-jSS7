
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

/**
 * @author baranowb
 * @author amit bhayani
 *

ReturnError{ERROR: Errors} ::= SEQUENCE {
    componentID     [PRIVATE 15] IMPLICIT OCTET STRING SIZE(1)
        (CONSTRAINED BY {--must be that of an outstanding operation --}
            ! RejectProblem : returnError-unrecognisedCorrelationId)
        (CONSTRAINED BY {--which returns an error --}
            ! RejectProblem : returnError-unexpectedReturnError),
    errorCode ERROR.&errorCode
        ({Errors}
            ! RejectProblem : returnError-unrecognisedError)
        (CONSTRAINED BY {--must be in the &Errors field of the associated operation --}
            ! RejectProblem : returnError-unexpectedError),
    parameter ERROR.&ParameterType
        ({Errors}{@errorcode}
            ! RejectProblem : returnError-incorrectParameter) OPTIONAL
}
(CONSTRAINED BY {--must conform to the above definition --}
    ! RejectProblem : general-incorrectComponentPortion)
(CONSTRAINED BY {--must have consistent encoding --}
    ! RejectProblem : general-badlyStructuredCompPortion)
(CONSTRAINED BY {--must conform to ANSI T1.114.3 encoding rules --}
    ! RejectProblem : general-incorrectComponentCoding)

 */
public interface ReturnError extends Component {
    int _TAG_RETURN_ERROR = 11;

    void setErrorCode(ErrorCode ec);

    ErrorCode getErrorCode();

    void setParameter(Parameter p);

    Parameter getParameter();

}
