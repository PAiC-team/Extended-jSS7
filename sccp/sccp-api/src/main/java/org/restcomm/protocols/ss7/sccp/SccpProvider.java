
package org.restcomm.protocols.ss7.sccp;

import java.io.IOException;
import java.io.Serializable;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.sccp.message.MessageFactory;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;

/**
 *
 * @author Oleg Kulikov
 * @author baranowb
 */
public interface SccpProvider extends Serializable {

    /**
     * Gets the access to message factory.
     *
     * @return message factory.
     */
    MessageFactory getMessageFactory();

    /**
     * Gets the access to parameter factory.
     *
     * @return parameter factory
     */
    ParameterFactory getParameterFactory();

    /**
     * Registers listener for some SSN. This is an equivalent of N-STATE request with User status==UIS (user in service)
     *
     * @param sccpListener
     */
    void registerSccpListener(int ssn, SccpListener sccpListener);

    /**
     * Removes listener for some SSN. This is an equivalent of N-STATE request with User status==UOS (user out of service)
     *
     */
    void deregisterSccpListener(int ssn);

    void registerManagementEventListener(SccpManagementEventListener sccpManagementEventListener);

    void deregisterManagementEventListener(SccpManagementEventListener sccpManagementEventListener);

    /**
     * Sends message.
     *
     * @param message Message to be sent
     * @throws IOException
     */
    void send(SccpDataMessage message) throws IOException;

    /**
     * Sends a unitdata service UDTS, XUDTS, LUDTS message (with error inside).
     *
     * @param sccpNoticeMessage Message to be sent
     * @throws IOException
     */
    void send(SccpNoticeMessage sccpNoticeMessage) throws IOException;

    /**
     * Return the maximum length (in bytes) of the sccp message data
     *
     * @param calledPartyAddress
     * @param callingPartyAddress
     * @param msgNetworkId
     * @return
     */
    int getMaxUserDataLength(SccpAddress calledPartyAddress, SccpAddress callingPartyAddress, int msgNetworkId);

    /**
     * Request of N-COORD when the originating user is requesting permission to go out-of-service
     *
     * @param ssn
     */
    void coordRequest(int ssn);

    /**
     * The collection of netwokIds that are marked as prohibited or congested.
     *
     * @return The collection of pairs: networkId value - NetworkIdState (prohibited / congested state)
     */
    FastMap<Integer, NetworkIdState> getNetworkIdStateList();

    /**
     * @return ExecutorCongestionMonitor list that are responsible for measuring of congestion of the thread Executor that
     *         processes incoming messages at mtp3 levels
     */
    ExecutorCongestionMonitor[] getExecutorCongestionMonitorList();

    SccpConnection newConnection(int localSsn, ProtocolClass protocolClass) throws MaxConnectionCountReached;

    FastMap<LocalReference, SccpConnection> getConnections();

    /**
     * @return SCCP stack
     */
    SccpStack getSccpStack();
    /**
     * Update Signaling Point congestion status
     * @return
     */

    void updateSPCongestion(Integer ssn, Integer congestionLevel);


}
