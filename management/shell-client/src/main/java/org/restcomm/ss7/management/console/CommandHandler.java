
package org.restcomm.ss7.management.console;

import java.util.List;

/**
 * @author amit bhayani
 *
 */
public interface CommandHandler {

    boolean handles(String command);

    List<CommandLineCompleter> getCommandLineCompleterList();

    boolean isAvailable(CommandContext ctx);

    void handle(CommandContext ctx, String command);

}
