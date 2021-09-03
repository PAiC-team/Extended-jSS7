
package org.restcomm.ss7.management.console;

import java.io.File;
import java.util.Collection;

/**
 * @author amit bhayani
 *
 */
public interface Console {

    void stop();

    void addCompleter(CommandLineCompleter completer);

    boolean isUseHistory();

    void setUseHistory(boolean useHistory);

    CommandHistory getHistory();

    void setHistoryFile(File f);

    void clearScreen();

    void printColumns(Collection<String> list);

    void print(String line);

    void printNewLine();

    String readLine(String prompt);

    String readLine(String prompt, Character mask);

}
