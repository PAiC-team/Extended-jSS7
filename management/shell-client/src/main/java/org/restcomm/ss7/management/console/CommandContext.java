
package org.restcomm.ss7.management.console;

import java.util.Collection;

/**
 * @author amit bhayani
 *
 */
public interface CommandContext {

    boolean isControllerConnected();

    /**
     * Prints a string to the CLI's output.
     *
     * @param message the message to print
     */
    void printLine(String message);

    /**
     * Prints a collection of strings as columns to the CLI's output.
     *
     * @param col the collection of strings to print as columns.
     */
    void printColumns(Collection<String> col);

    /**
     * Clears the screen.
     */
    void clearScreen();

    /**
     * Terminates the command line session. Also closes the connection to the controller if it's still open.
     */
    void terminateSession();

    /**
     * Checks whether the session has been terminated.
     *
     * @return
     */
    boolean isTerminated();

    /**
     * Connects the controller client using the host and the port. If the host is null, the default controller host will be
     * used, which is localhost. If the port is less than zero, the default controller port will be used, which is 9999.
     *
     * @param host the host to connect with
     * @param port the port to connect on
     * @throws CommandLineException in case the attempt to connect failed
     */
    void connectController(String host, int port);

    /**
     * Closes the previously established connection with the controller client. If the connection hasn't been established, the
     * method silently returns.
     */
    void disconnectController();

    /**
     * Returns the default host the controller client will be connected to in case the host argument isn't specified.
     *
     * @return the default host the controller client will be connected to in case the host argument isn't specified.
     */
    String getDefaultControllerHost();

    /**
     * Returns the default port the controller client will be connected to in case the port argument isn't specified.
     *
     * @return the default port the controller client will be connected to in case the port argument isn't specified.
     */
    int getDefaultControllerPort();

    /**
     * Returns the host the controller client is connected to or null if the connection hasn't been established yet.
     *
     * @return the host the controller client is connected to or null if the connection hasn't been established yet.
     */
    String getControllerHost();

    /**
     * Returns the port the controller client is connected to.
     *
     * @return the port the controller client is connected to.
     */
    int getControllerPort();

    /**
     * Returns the history of all the commands and operations.
     *
     * @return the history of all the commands and operations.
     */
    CommandHistory getHistory();

    void sendMessage(String text);
}
