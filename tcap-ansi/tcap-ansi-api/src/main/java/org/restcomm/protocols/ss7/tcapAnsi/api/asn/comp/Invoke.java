
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.tc.component.InvokeClass;

/**
 * @author baranowb
 * @author amit bhayani
 *
Invoke{InvokeId: InvokeIdSet, OPERATION: Operations} ::= SEQUENCE {
    componentIDs        [PRIVATE 15] IMPLICIT OCTET STRING SIZE(0..2)
    --The invoke ID precedes the correlation id. There may be no
    --identifier, only an invoke ID, or both invoke and correlation
    --IDs.
        (CONSTRAINED BY {--must be unambiguous --}
            ! RejectProblem : invoke-duplicateInvocation),
        (CONSTRAINED BY {--correlation ID must identify an outstanding operation --}
            ! RejectProblem : invoke-unrecognisedCorrelationId) OPTIONAL,
    operationCode OPERATION.&operationCode
        ((Operations)
            ! RejectProblem : invoke-unrecognisedOperation),
    parameter OPERATION.&ParameterType
        ((Operations)(@Opcode)
            ! RejectProblem : invoke-mistypedArgument) OPTIONAL
}
(CONSTRAINED BY {--must conform to the above definition --}
    ! RejectProblem : general-incorrectComponentPortion)
(CONSTRAINED BY {--must have consistent encoding --}
    ! RejectProblem : general-badlyStructuredCompPortion)
(CONSTRAINED BY {--must conform to ANSI T1.114.3 encoding rules --}
    ! RejectProblem : general-incorrectComponentCoding)

 *
 */
public interface Invoke extends Component {

    int _TAG_INVOKE_NOT_LAST = 13;
    int _TAG_INVOKE_LAST = 9;

    // local, relevant only for send
    InvokeClass getInvokeClass();

    boolean isNotLast();

    void setNotLast(boolean val);

    void setInvokeId(Long i);

    Long getInvokeId();

    Invoke getCorrelationInvoke();

    void setCorrelationInvoke(Invoke val);

    void setOperationCode(OperationCode i);

    OperationCode getOperationCode();

    void setParameter(Parameter p);

    Parameter getParameter();

    /**
     * @return the current invokeTimeout value
     */
    long getTimeout();

    /**
     * Setting the Invoke timeout in milliseconds Must be invoked before sendComponent() invoking
     *
     * @param invokeTimeout
     */
    void setTimeout(long invokeTimeout);
}
