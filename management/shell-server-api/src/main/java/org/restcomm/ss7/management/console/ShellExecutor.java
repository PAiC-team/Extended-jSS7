
package org.restcomm.ss7.management.console;

/**
 * Listener interface for receiving options specific to linkset. The class that is interested in processing command implements
 * this interface.
 *
 * @author amit bhayani
 *
 */
public interface ShellExecutor {

    boolean handles(String command);

    String execute(String[] options);

}
