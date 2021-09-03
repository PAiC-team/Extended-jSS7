
package org.restcomm.protocols.ss7.map.api;

import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortProviderReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortSource;
import org.restcomm.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic;
import org.restcomm.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface MAPDialogListener {
    /**
     * Called when all components has been processed. It is equals the MAP-DELIMITER indication primitive
     */
    void onDialogDelimiter(MAPDialog mapDialog);

    /**
     * When T_BEGIN received. If MAP user rejects this dialog it should call MAPDialog.refuse()
     */
    void onDialogRequest(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
            MAPExtensionContainer extensionContainer);

    /**
     * When T_BEGIN received. If MAP user rejects this dialog it should call This event is the onDialogRequest() method for
     * Ericsson-style ASN.1 syntax MAPDialog.refuse()
     */
    void onDialogRequestEricsson(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
            AddressString ericssonMsisdn, AddressString ericssonVlrNo);

    /**
     * When T_CONTINUE or T_END received with dialogueResponse DialoguePDU (AARE-apdu) (dialog accepted) this is called before
     * ComponentPortion is called
     */
    void onDialogAccept(MAPDialog mapDialog, MAPExtensionContainer extensionContainer);

    /**
     * When T_U_ABORT received as the response to T_BEGIN
     *
     */
    void onDialogReject(MAPDialog mapDialog, MAPRefuseReason refuseReason,
            ApplicationContextName alternativeApplicationContext, MAPExtensionContainer extensionContainer);

    // void onDialogReject(MAPDialog mapDialog, MAPRefuseReason refuseReason, MAPProviderError providerError,
    // ApplicationContextName alternativeApplicationContext, MAPExtensionContainer extensionContainer);

    /**
     * When T_ABORT received NOT as the response to T_BEGIN
     *
     */
    void onDialogUserAbort(MAPDialog mapDialog, MAPUserAbortChoice userReason, MAPExtensionContainer extensionContainer);

    /**
     * When T_ABORT received NOT as the response to T_BEGIN
     *
     */
    void onDialogProviderAbort(MAPDialog mapDialog, MAPAbortProviderReason abortProviderReason, MAPAbortSource abortSource,
            MAPExtensionContainer extensionContainer);

    /**
     * When T_CLOSE received If T_CLOSE is the response to T-BEGIN, onDialogRequest() if called first, then ComponentPortion is
     * called and finally onDialogClose
     */
    void onDialogClose(MAPDialog mapDialog);

    /**
     * This service is used to notify the MAP service-user about protocol problems related to a MAP dialogue not affecting the
     * state of the protocol machines
     */
    void onDialogNotice(MAPDialog mapDialog, MAPNoticeProblemDiagnostic mapNoticeProblemDiagnostic);

    /**
     * Called when the MADDialog has been released
     *
     * @param mapDialog
     */
    void onDialogRelease(MAPDialog mapDialog);

    /**
     * Called when the MADDialog is about to aborted because of TimeOut
     *
     * @param mapDialog
     */
    void onDialogTimeout(MAPDialog mapDialog);

}
