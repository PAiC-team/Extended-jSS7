
package org.restcomm.protocols.ss7.cap.api.dialog;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CAPNoticeProblemDiagnostic {
    /**
     * TC-NOTICE is received for a sent outgoing message has not been delivered to a peer because of network issue. In Dialog
     * initiating state (TC-BEGIN has been sent) this leads Dialog releasing
     */
    MessageCannotBeDeliveredToThePeer(0),

    /**
     * Some abnormal dialog processing occurs when receiving a message from a peer. (no or bad APC received). A Dialog will be
     * released
     */
    AbnormalDialogAction(7);

    private int code;

    private CAPNoticeProblemDiagnostic(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}