package org.restcomm.protocols.ss7.sccp;

import org.restcomm.protocols.ss7.sccp.message.SccpConnCrMessage;
import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCause;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCause;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

import java.io.IOException;

public interface SccpConnection {
    /**
     * Get Signalling Link Selection (SLS) code for connection
     *
     * @return
     */
    int getSls();

    /**
     * Get subsystem number (SSN) for connection
     *
     * @return
     */
    int getLocalSsn();

    /**
     * Get source local reference for connection
     *
     * @return
     */
    LocalReference getLocalReference();

    /**
     * Get destination (remote) local reference for connection
     *
     * @return
     */
    LocalReference getRemoteReference();

    /**
     * Returns whether connection is available for sending data (i. e. connection isn't closed or performing reset, etc)
     *
     * @return
     */
    boolean isAvailable();

    /**
     * Send data via connection
     *
     * @param data
     * @return
     */
    void send(byte[] data) throws Exception;

    /**
     * Get connection state
     *
     * @return
     */
    SccpConnectionState getState();

    /**
     * Get send credit (send window size) for connection
     *
     * @return
     */
    Credit getSendCredit();

    /**
     * Get receive credit (receive window size) for connection
     *
     * @return
     */
    Credit getReceiveCredit();

    /**
     * Initiate establishing of connection by sending SCCP connection request message
     *
     * @param sccpConnCrMessage
     * @return
     */
    void establish(SccpConnCrMessage sccpConnCrMessage) throws IOException;

    /**
     * Reset connection
     *
     * @param resetCauseReason
     * @return
     */
    void reset(ResetCause resetCauseReason) throws Exception;

    /**
     * Refuse to accept new connection
     *
     * @param refusalCauseReason
     * @param data This parameter is optional
     * @return
     */
    void refuse(RefusalCause refusalCauseReason, byte[] data) throws Exception;

    /**
     * Disconnect established connection
     *
     * @param releaseCauseReason
     * @param data This parameter is optional
     * @return
     */
    void disconnect(ReleaseCause releaseCauseReason, byte[] data) throws Exception;

    /**
     * Accept new connection
     *
     * @param sccpRespondingAddress
     * @param credit This parameter is optional
     * @return
     */
    void confirm(SccpAddress sccpRespondingAddress, Credit credit, byte[] data) throws Exception;

    /**
     * Accept new connection
     *
     * @return
     */
    SccpListener getListener();
}
