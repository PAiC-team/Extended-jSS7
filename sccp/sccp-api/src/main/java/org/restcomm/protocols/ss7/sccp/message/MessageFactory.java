package org.restcomm.protocols.ss7.sccp.message;

import java.io.Serializable;

import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 * Factory for creating messages.
 *
 * @author baranowb
 * @author kulikov
 * @author sergey vetyutnev
 *
 */
public interface MessageFactory extends Serializable {

    /**
     * Create a SCCP data transfer message (class 0)
     *
     * @param calledParty
     * @param callingParty
     * @param data
     * @param localSsn
     * @param returnMessageOnError
     * @param hopCounter This parameter is optional
     * @param importance This parameter is optional
     * @return
     */
    SccpDataMessage createDataMessageClass0(SccpAddress calledParty, SccpAddress callingParty, byte[] data,
            int localSsn, boolean returnMessageOnError, HopCounter hopCounter, Importance importance);

    /**
     * Create a SCCP data transfer message (class 1)
     *
     * @param calledParty
     * @param callingParty
     * @param data
     * @param sls
     * @param localSsn
     * @param returnMessageOnError
     * @param hopCounter This parameter is optional
     * @param importance This parameter is optional
     * @return
     */
    SccpDataMessage createDataMessageClass1(SccpAddress calledParty, SccpAddress callingParty, byte[] data, int sls,
            int localSsn, boolean returnMessageOnError, HopCounter hopCounter, Importance importance);

    // SccpNoticeMessage createNoticeMessage(ReturnCause returnCause, int outgoingSls, SccpAddress calledParty,
    // SccpAddress callingParty, byte[] data,
    // HopCounter hopCounter, Importance importance);

    /**
     * Create a SCCP connection request message (class 2)
     *
     * @param calledParty
     * @param callingParty This parameter is optional
     * @param data       This parameter is optional
     * @param importance This parameter is optional
     * @return
     */
    SccpConnCrMessage createConnectMessageClass2(int localSsn, SccpAddress calledParty, SccpAddress callingParty, byte[] data, Importance importance);

    /**
     * Create a SCCP connection request message (class 3)
     *
     * @param calledParty
     * @param callingParty This parameter is optional
     * @param credit
     * @param data       This parameter is optional
     * @param importance This parameter is optional
     * @return
     */
    SccpConnCrMessage createConnectMessageClass3(int localSsn, SccpAddress calledParty, SccpAddress callingParty, Credit credit, byte[] data, Importance importance);
}
