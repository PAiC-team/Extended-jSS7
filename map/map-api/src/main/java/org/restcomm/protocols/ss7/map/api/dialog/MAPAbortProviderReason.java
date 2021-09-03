
package org.restcomm.protocols.ss7.map.api.dialog;

/**
 * Provider reason: This parameter indicates the reason for aborting the MAP dialogue: - provider malfunction; - supporting
 * dialogue/transaction released; - resource limitation; - maintenance activity; - version incompatibility; - abnormal MAP
 * dialogue.
 *
 * @author sergey vetyutnev
 *
 */
public enum MAPAbortProviderReason {
    /**
     * This option is used when other PAbortCauseType options is received from a peer except UnrecognizedTxID or
     * ResourceLimitation
     */
    ProviderMalfunction(0),

    /**
     * This option is used when PAbortCauseType.UnrecognizedTxID is received from a peer (a Dialog has already released at a
     * peer side)
     */
    SupportingDialogueTransactionReleased(1),

    /**
     * This option is used when PAbortCauseType.ResourceLimitation is received from a peer
     */
    ResourceLimitation(2),

    /**
     * This option is not used now.
     */
    MaintenanceActivity(3),

    /**
     * If a potential version incompatibility is detected (when we should try to use MAP V1)
     * MAPDialogListener.onDialogRequest() is issued with MAPRefuseReason==PotentialVersionIncompatibility
     */
    VersionIncompatibility(4),

    /**
     * If a potential version incompatibility is detected at TCAP level: peer TCAP stack does not support TCAP ProtocolVersion==1
     * MAPDialogListener.onDialogRequest() is issued with MAPRefuseReason==PotentialVersionIncompatibility
     */
    VersionIncompatibilityTcap(8),

    /**
     * Used when local problems with a Dialog detected: - no or bad ACN received at the first TC-CONTINUE or TC-END - no or bad
     * user info is received at the TC-U-ABORT
     */
    AbnormalMAPDialogueLocal(5),

    /**
     * Used when receiving MAP-ProviderAbortInfo from a peer with MAP-ProviderAbortReason = abnormalDialogue
     */
    AbnormalMAPDialogueFromPeer(6),

    /**
     * Used when receiving MAP-ProviderAbortInfo from a peer with MAP-ProviderAbortReason = invalidPDU
     */
    InvalidPDU(7);

    private int code;

    private MAPAbortProviderReason(int code) {
        this.code = code;
    }
}
