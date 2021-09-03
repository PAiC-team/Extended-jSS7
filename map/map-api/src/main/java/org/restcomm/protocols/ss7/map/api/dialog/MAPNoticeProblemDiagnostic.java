
package org.restcomm.protocols.ss7.map.api.dialog;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum MAPNoticeProblemDiagnostic {
    // we do not use following reasons now because we deliver local and remote Reject primitives
    // to a MAP-USER in components array
    // AbnormalEventDetectedByThePeer(0), ResponseRejectedByThePeer(1),
    // AbnormalEventReceivedFromThePeer(2),

    /**
     * TC-NOTICE is received for outgoing message has not been delivered to a peer because of network issue. When Dialog
     * initiating state (TC-BEGIN has been sent) onDialogReject() will be delivered instead of onDialogNotice()
     */
    MessageCannotBeDeliveredToThePeer(0);

    private int code;

    private MAPNoticeProblemDiagnostic(int code) {
        this.code = code;
    }
}
