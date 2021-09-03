
package org.restcomm.ss7.management.console;

/**
 * @author amit bhayani
 *
 */
public class CommandLineException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3970317811436379869L;

    public CommandLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandLineException(String message) {
        super(message);
    }

    public CommandLineException(Throwable cause) {
        super(cause);
    }
}
