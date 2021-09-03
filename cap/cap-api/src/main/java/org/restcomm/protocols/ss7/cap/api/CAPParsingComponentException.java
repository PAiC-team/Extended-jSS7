
package org.restcomm.protocols.ss7.cap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPParsingComponentException extends Exception {

    private CAPParsingComponentExceptionReason reason;

    public CAPParsingComponentException() {
        // TODO Auto-generated constructor stub
    }

    public CAPParsingComponentException(String message, CAPParsingComponentExceptionReason reason) {
        super(message);

        this.reason = reason;
    }

    public CAPParsingComponentException(Throwable cause, CAPParsingComponentExceptionReason reason) {
        super(cause);

        this.reason = reason;
    }

    public CAPParsingComponentException(String message, Throwable cause, CAPParsingComponentExceptionReason reason) {
        super(message, cause);

        this.reason = reason;
    }

    public CAPParsingComponentExceptionReason getReason() {
        return this.reason;
    }
}
