
package org.restcomm.protocols.ss7.cap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPException extends Exception {

    public CAPException() {
    }

    public CAPException(String message) {
        super(message);
    }

    public CAPException(Throwable cause) {
        super(cause);
    }

    public CAPException(String message, Throwable cause) {
        super(message, cause);
    }

}
