package org.restcomm.protocols.ss7.sccp;

public enum SccpConnectionState {

    NEW,
    CONNECTION_INITIATED, // CR sent
    DISCONNECT_INITIATED, // RLSD sent
    CR_RECEIVED,
    ESTABLISHED,
    ESTABLISHED_SEND_WINDOW_EXHAUSTED,
    CLOSED,
    RSR_SENT,
    RSR_PROPAGATED_VIA_COUPLED,
    RSR_RECEIVED_WILL_PROPAGATE,
    RSR_RECEIVED

}
