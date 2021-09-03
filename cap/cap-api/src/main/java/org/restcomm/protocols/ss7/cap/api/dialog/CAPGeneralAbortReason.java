
package org.restcomm.protocols.ss7.cap.api.dialog;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CAPGeneralAbortReason {
    /**
     * Application context name does not supported
     */
    ACNNotSupported(0),
    /**
     * Other part has been refused the Dialog with AARE apdu with abortReason=null or abortReason=no-reason-given
     */
    DialogRefused(1),
    /**
     * Other part has been refused the Dialog with AARE apdu with abortReason=null or abortReason=NoCommonDialogPortion
     */
    NoCommonDialogPortionReceived(2),
    /**
     * User abort, CAPUserAbortReason is present in the message
     */
    UserSpecific(3);

    private int code;

    private CAPGeneralAbortReason(int code) {
        this.code = code;
    }

    public static CAPGeneralAbortReason getInstance(int code) {
        switch (code) {
            case 0:
                return CAPGeneralAbortReason.ACNNotSupported;
            case 1:
                return CAPGeneralAbortReason.DialogRefused;
            case 2:
                return CAPGeneralAbortReason.NoCommonDialogPortionReceived;
            case 3:
                return CAPGeneralAbortReason.UserSpecific;
            default:
                return null;
        }
    }

    public int getCode() {
        return code;
    }
}
