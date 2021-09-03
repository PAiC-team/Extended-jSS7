
package org.restcomm.protocols.ss7.cap.api;

import org.restcomm.protocols.ss7.cap.api.dialog.CAPGeneralAbortReason;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPNoticeProblemDiagnostic;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPUserAbortReason;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPDialogListener {
    /**
     * Called after all components has been processed.
     */
     void onDialogDelimiter(CAPDialog capDialog);

    /**
     * When TC-BEGIN received. If CAP user rejects this dialog it should call CAPDialog.abort()
     */
     void onDialogRequest(CAPDialog capDialog, CAPGprsReferenceNumber capGprsReferenceNumber);

    /**
     * When TC-CONTINUE or TC-END received with dialogueResponse DialoguePDU (AARE-apdu) (dialog accepted) this is called before
     * ComponentPortion is called
     */
     void onDialogAccept(CAPDialog capDialog, CAPGprsReferenceNumber capGprsReferenceNumber);

    /**
     * When TC-ABORT received with user abort userReason is defined only if generalReason=UserSpecific
     */
     void onDialogUserAbort(CAPDialog capDialog, CAPGeneralAbortReason generalReason, CAPUserAbortReason userReason);

    /**
     * When TC-ABORT received with provider abort
     *
     */
     void onDialogProviderAbort(CAPDialog capDialog, PAbortCauseType abortCause);

    /**
     * When TC-END received
     */
     void onDialogClose(CAPDialog capDialog);

    /**
     * Called when the CADDialog has been released
     *
     * @param capDialog
     */
     void onDialogRelease(CAPDialog capDialog);

    /**
     * Called when the CADDialog is about to aborted because of TimeOut
     *
     * @param capDialog
     */
     void onDialogTimeout(CAPDialog capDialog);

    /**
     * Called to notice of abnormal cases
     *
     */
     void onDialogNotice(CAPDialog capDialog, CAPNoticeProblemDiagnostic noticeProblemDiagnostic);

}
