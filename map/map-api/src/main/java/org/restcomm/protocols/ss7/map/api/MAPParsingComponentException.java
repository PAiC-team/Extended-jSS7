package org.restcomm.protocols.ss7.map.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPParsingComponentException extends Exception {

    private MAPParsingComponentExceptionReason reason;

    public MAPParsingComponentException() {
        // TODO Auto-generated constructor stub
    }

    public MAPParsingComponentException(String message, MAPParsingComponentExceptionReason reason) {
        super(message);

        this.reason = reason;
    }

    public MAPParsingComponentException(Throwable cause, MAPParsingComponentExceptionReason reason) {
        super(cause);

        this.reason = reason;
    }

    public MAPParsingComponentException(String message, Throwable cause, MAPParsingComponentExceptionReason reason) {
        super(message, cause);

        this.reason = reason;
    }

    public MAPParsingComponentExceptionReason getReason() {
        return this.reason;
    }
}
