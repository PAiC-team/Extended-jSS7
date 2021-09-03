
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;

/**
 *
 * This interface represents a SCCP release complete message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnRlcMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);
}
