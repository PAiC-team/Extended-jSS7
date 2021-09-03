
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCause;

/**
 *
 * This interface represents a SCCP released message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnRlsdMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);

    ReleaseCause getReleaseCause();
    void setReleaseCause(ReleaseCause value);

    byte[] getUserData();
    void setUserData(byte[] data);

    Importance getImportance();
    void setImportance(Importance importance);
}
