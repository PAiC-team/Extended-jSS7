package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * This interface represents a SCCP message that addressed with Called/CallingPartyAddresses
 *
 * @author sergey vetyutnev
 *
 */
public interface SccpAddressedMessage extends SccpMessage {

    SccpAddress getCalledPartyAddress();

    SccpAddress getCallingPartyAddress();

    boolean getReturnMessageOnError();

    void clearReturnMessageOnError();

    boolean getSccpCreatesSls();

    HopCounter getHopCounter();

    void setCalledPartyAddress(SccpAddress v);

    void setCallingPartyAddress(SccpAddress v);

    void setHopCounter(HopCounter hopCounter);

    boolean reduceHopCounter();

}
