package org.restcomm.protocols.ss7.inap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class INAPParsingComponentException extends Exception {

    private INAPParsingComponentExceptionReason reason;

    public INAPParsingComponentException() {
        // TODO Auto-generated constructor stub
    }

    public INAPParsingComponentException(String message, INAPParsingComponentExceptionReason reason) {
        super(message);

        this.reason = reason;
    }

    public INAPParsingComponentException(Throwable cause, INAPParsingComponentExceptionReason reason) {
        super(cause);

        this.reason = reason;
    }

    public INAPParsingComponentException(String message, Throwable cause, INAPParsingComponentExceptionReason reason) {
        super(message, cause);

        this.reason = reason;
    }

    public INAPParsingComponentExceptionReason getReason() {
        return this.reason;
    }

}
