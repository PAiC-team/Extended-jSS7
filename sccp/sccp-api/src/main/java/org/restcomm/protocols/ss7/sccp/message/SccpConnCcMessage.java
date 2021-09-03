
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * This interface represents a SCCP connection confirm message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnCcMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);

    SccpAddress getCalledPartyAddress();
    void setCalledPartyAddress(SccpAddress address);

    ProtocolClass getProtocolClass();
    void setProtocolClass(ProtocolClass value);

    Credit getCredit();
    void setCredit(Credit value);

    byte[] getUserData();
    void setUserData(byte[] data);

    Importance getImportance();
    void setImportance(Importance importance);
}
