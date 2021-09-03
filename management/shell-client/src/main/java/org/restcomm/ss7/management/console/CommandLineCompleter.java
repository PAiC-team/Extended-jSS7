
package org.restcomm.ss7.management.console;

import java.util.List;

/**
 * @author amit bhayani
 *
 */
public interface CommandLineCompleter {
    int complete(CommandContext ctx, String buffer, int cursor, List<String> candidates);
}
