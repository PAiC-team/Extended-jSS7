package org.restcomm.protocols.ss7.m3ua.impl.fsm;

/**
 *
 * @author amit bhayani
 * @author kulikov
 *
 */
public class UnknownTransitionException extends Exception {

    /**
     * Creates a new instance of <code>UnknownTransitionException</code> without detail message.
     */
    public UnknownTransitionException() {
    }

    /**
     * Constructs an instance of <code>UnknownTransitionException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownTransitionException(String msg) {
        super(msg);
    }
}
