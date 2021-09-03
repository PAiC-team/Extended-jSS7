
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCause;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * This interface represents a SCCP connection refused message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnCrefMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    SccpAddress getCalledPartyAddress();
    void setCalledPartyAddress(SccpAddress address);

    byte[] getUserData();
    void setUserData(byte[] data);

    RefusalCause getRefusalCause();
    void setRefusalCause(RefusalCause value);

    Importance getImportance();
    void setImportance(Importance importance);
}
