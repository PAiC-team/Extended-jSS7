package org.restcomm.protocols.ss7.inap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class INAPException extends Exception {

    public INAPException() {
    }

    public INAPException(String message) {
        super(message);
    }

    public INAPException(Throwable cause) {
        super(cause);
    }

    public INAPException(String message, Throwable cause) {
        super(message, cause);
    }

}
