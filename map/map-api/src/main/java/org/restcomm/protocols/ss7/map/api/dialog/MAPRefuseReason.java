
package org.restcomm.protocols.ss7.map.api.dialog;

/**
 * This parameter is present only if the Result parameter indicates that the dialogue is refused. It takes one of the following
 * values: - Application-context-not-supported; - Invalid-destination-reference; - Invalid-originating-reference; -
 * No-reason-given; - Remote node not reachable; - Potential version incompatibility.
 *
 * @author sergey vetyutnev
 *
 */
public enum MAPRefuseReason {
    /**
     * Peer does not support a given ACN. We should try to use lower MAP version.
     */
    ApplicationContextNotSupported(0),

    /**
     * InvalidDestinationReference is detected by a peer
     */
    InvalidDestinationReference(1),

    /**
     * InvalidOriginatingReference is detected by a peer
     */
    InvalidOriginatingReference(2),

    /**
     * TCUserAbort received with not reason given
     */
    NoReasonGiven(3),

    /**
     * TC-NOTICE is received at the initiating stage originating MAPDialog because of TC-BEGIN message has not been delivered to
     * a peer
     */
    RemoteNodeNotReachable(4),

    /**
     * We received a response from a peer for a local originated TC-BEGIN message that tells us about a peer possible supports
     * only MAP V1 (PAbortCauseType==IncorrectTxPortion or DialogServiceProviderType.NoCommonDialogPortion or no userInfo in
     * TCUserAbort) We should try to use MAP V1 for this peer
     */
    PotentialVersionIncompatibility(5),

    /**
     * We received a response from a peer for a peer TCAP does not support TCAP V1
     */
    PotentialVersionIncompatibilityTcap(6);

    private int code;

    private MAPRefuseReason(int code) {
        this.code = code;
    }
}
