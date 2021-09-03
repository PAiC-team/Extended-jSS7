package org.restcomm.protocols.ss7.isup;

import java.io.IOException;
import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.message.ISUPMessage;

/**
 * @author baranowb
 * @author kulikov
 */
public interface ISUPProvider extends Serializable {

    /**
     * Returns localy configured network indicator.
     *
     * @return
     */
    int getNi();

    /**
     * Returns local PC (OPC for outgoing messages)
     *
     * @return
     */
    int getLocalSpc();

    /**
     * Sends message.
     *
     * @param msg
     * @throws ParameterException
     * @throws IOException
     */
    void sendMessage(ISUPMessage msg, int dpc) throws ParameterException, IOException;

    /**
     * Adds default listener.
     *
     * @param listener
     */
    void addListener(ISUPListener listener);

    /**
     * Removes listener.
     *
     * @param listener
     */
    void removeListener(ISUPListener listener);

    /**
     * Get factory for ISUP parameters.
     *
     * @return
     */
    ISUPParameterFactory getParameterFactory();

    /**
     * Get factory for ISUP messages.
     *
     * @return
     */
    ISUPMessageFactory getMessageFactory();

    /**
     * cancel timer. It is required for instance in case of T17 to allow it be explicitly canceled
     *
     * @param cic - circuit identification code
     * @param dpc - destination point code
     * @param timerId - integer id of timer. See {@link ISUPTimeoutEvent} static values.
     * @return <ul>
     *         <li><b>true</b> - if timer was removed</li>
     *         <li><b>false</b> - otherwise</li>
     *         </ul>
     */
    boolean cancelTimer(int cic, int dcp, int timerId);

    /**
     * cancel all timers. Useable when circuit usage is ended
     *
     * @param cic - circuit identification code
     * @param dpc - destination point code
     */
    void cancelAllTimers(int cic, int dpc);
}
