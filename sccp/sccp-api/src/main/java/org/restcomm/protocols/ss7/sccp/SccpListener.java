
package org.restcomm.protocols.ss7.sccp;

import java.io.Serializable;

import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.ErrorCause;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCause;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCause;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author Oleg Kulikov
 * @author baranowb
 * @author serey vetyutnev
 */
public interface SccpListener extends Serializable {

    /**
     * Called when proper data is received, it is partially decoded. This method is called with message payload.
     *
     * @param sccpDataMessage Message received
     */
    void onMessage(SccpDataMessage sccpDataMessage);

    /**
     * Called when N-NOTICE indication (on receiving UDTS, XUDTS or LUDTS) N-NOTICE indication is the means by which the SCCP
     * returns to the originating user a SCCP-SDU which could not reach the final destination.
     *
     * @param message Message received
     */
    void onNotice(SccpNoticeMessage message);

    /**
     * Indication of N-COORD response when the originator (SCCP user) is informed that it may go out-of-service
     *
     * @param ssn
     * @param multiplicityIndicator
     */
    void onCoordResponse(int ssn, int multiplicityIndicator);

    /**
     * Indication of N-STATE (another local subsystem state change - UIS / UOS)
     *
     * @param dpc
     * @param ssn
     * @param inService
     * @param multiplicityIndicator
     */
    void onState(int dpc, int ssn, boolean inService, int multiplicityIndicator);

    /**
     * Indication of N-PCSTATE (pointcode state)
     *
     * @param dpc
     * @param status
     * @param restrictedImportanceLevel
     * @param remoteSccpStatus
     */
    void onPcState(int dpc, SignallingPointStatus status, Integer restrictedImportanceLevel, RemoteSccpStatus remoteSccpStatus);

    /**
     * Reporting of changing of availability / congestion state for a networkId
     *
     * @param networkId
     * @param networkIdState
     */
    void onNetworkIdState(int networkId, NetworkIdState networkIdState);


    // N-CONNECT
    // can call conn.confirm() or conn.disconnect(...) with refuse reason and data
    void onConnectIndication(SccpConnection sccpConnection, SccpAddress calledAddress, SccpAddress callingAddress,
                             ProtocolClass protocolClass, Credit credit, // QoS, credit is set only for class 3
                             byte[] data, Importance importance) throws Exception;
    // N-CONNECT
    void onConnectConfirm(SccpConnection conn, byte[] data);

    // N-DISCONNECT
    void onDisconnectIndication(SccpConnection sccpConnection, ReleaseCause releaseCauseReason, byte[] data);
    void onDisconnectIndication(SccpConnection sccpConnection, RefusalCause refusalCauseReason, byte[] data);
    void onDisconnectIndication(SccpConnection sccpConnection, ErrorCause errorCause);

    void onResetIndication(SccpConnection sccpConnection, ResetCause resetCauseReason);

    void onResetConfirm(SccpConnection sccpConnection);

    void onData(SccpConnection sccpConnection, byte[] data);

    void onDisconnectConfirm(SccpConnection sccpConnection);
}
