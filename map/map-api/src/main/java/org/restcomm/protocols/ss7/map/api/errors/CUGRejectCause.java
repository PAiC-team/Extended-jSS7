
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 CUG-RejectCause ::= ENUMERATED { incomingCallsBarredWithinCUG (0), subscriberNotMemberOfCUG (1),
 * requestedBasicServiceViolatesCUG-Constraints (5), calledPartySS-InteractionViolation (7)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CUGRejectCause {

    incomingCallsBarredWithinCUG(0), subscriberNotMemberOfCUG(1), requestedBasicServiceViolatesCUGConstraints(5),
    calledPartySSInteractionViolation(7);

    private int code;

    private CUGRejectCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CUGRejectCause getInstance(int code) {
        switch (code) {
            case 0:
                return CUGRejectCause.incomingCallsBarredWithinCUG;
            case 1:
                return CUGRejectCause.subscriberNotMemberOfCUG;
            case 5:
                return CUGRejectCause.requestedBasicServiceViolatesCUGConstraints;
            case 7:
                return CUGRejectCause.calledPartySSInteractionViolation;
            default:
                return null;
        }
    }
}
