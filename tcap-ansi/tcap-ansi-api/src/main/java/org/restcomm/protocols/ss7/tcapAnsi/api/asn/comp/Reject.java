
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

/**
 * @author baranowb
 * @author sergey vetyutnev

Reject ::= SEQUENCE {
    componentID     [PRIVATE 15] IMPLICIT OCTET STRING SIZE(0..1),
    rejectProblem       [PRIVATE 21] IMPLICIT Problem,
    parameter CHOICE {
        paramSequence       [PRIVATE 16] IMPLICIT SEQUENCE { },
        paramSet        [PRIVATE 18] IMPLICIT SET { }
        --The choice between paramSequence and paramSet is implementation
        --dependent, however paramSequence is preferred.
    }
}
(CONSTRAINED BY {--must conform to the above definition --}
    ! RejectProblem : general-incorrectComponentPortion)
(CONSTRAINED BY {--must have consistent encoding --}
    ! RejectProblem : general-badlyStructuredCompPortion)
(CONSTRAINED BY {--must conform to ANSI T1.114.3 encoding rules --}
    ! RejectProblem : general-incorrectComponentCoding)

 *
 */
public interface Reject extends Component {

    int _TAG_REJECT = 12;
    int _TAG_REJECT_PROBLEM = 21;

    void setProblem(RejectProblem p);

    RejectProblem getProblem();

    /**
     * @return true: local originated Reject (rejecting a bad incoming primitive by a local side) false: remote originated
     *         Reject (rejecting a bad outgoing primitive by a peer)
     */
    boolean isLocalOriginated();

    void setLocalOriginated(boolean p);

}
