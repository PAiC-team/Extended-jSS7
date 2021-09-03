package org.restcomm.protocols.ss7.map;

/**
 * @author amit bhayani
 *
 */
public enum MAPComponentState {
    // No activity associated with the ID.
    Idle,

    // An operation has been transmitted to the remote end, but no result has been received. The timer associated with
    // the operation invocation (with the value of "Timeout") is started when the transition from "Idle" to "Operation Sent"
    // occurs
    OperationPending,

    // The result has been received; TCAP is waiting for its possible rejection by the TC-user.
    WaitforReject,

    // Reject of the result has been requested by the TC-user, but no request for transmission has been issued.
    Rejectpending;
}
