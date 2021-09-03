
package org.restcomm.ss7.management.console;

import java.util.List;

/**
 * @author amit bhayani
 *
 */
public interface CommandHistory {
    /**
     * Returns the history as a list of strings.
     *
     * @return history as a list of strings.
     */
    List<String> asList();

    /**
     * Returns a boolean indicating whether the history is enabled or not.
     *
     * @return true in case the history is enabled, false otherwise.
     */
    boolean isUseHistory();

    /**
     * Enables or disables history.
     *
     * @param useHistory true enables history, false disables it.
     */
    void setUseHistory(boolean useHistory);

    /**
     * Clears history.
     */
    void clear();

    /**
     * Sets the maximum length of the history log.
     *
     * @param maxSize maximum length of the history log
     */
    void setMaxSize(int maxSize);

    /**
     * The maximum length of the history log.
     *
     * @return maximum length of the history log
     */
    int getMaxSize();
}
