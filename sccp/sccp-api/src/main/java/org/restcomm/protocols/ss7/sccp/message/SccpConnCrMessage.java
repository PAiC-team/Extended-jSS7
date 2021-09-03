
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * This interface represents a SCCP connection request message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnCrMessage extends SccpConnMessage, SccpAddressedMessage {

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);

    SccpAddress getCalledPartyAddress();
    void setCalledPartyAddress(SccpAddress address);

    SccpAddress getCallingPartyAddress();
    void setCallingPartyAddress(SccpAddress address);

    ProtocolClass getProtocolClass();
    void setProtocolClass(ProtocolClass value);

    Credit getCredit();
    void setCredit(Credit value);

    byte[] getUserData();
    void setUserData(byte[] data);

    HopCounter getHopCounter();
    void setHopCounter(HopCounter counter);
    boolean reduceHopCounter();

    Importance getImportance();
    void setImportance(Importance importance);

    boolean getSccpCreatesSls();
}
