
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.SequencingSegmenting;

/**
 *
 * This interface represents a SCCP inactivity test message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnItMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);

    ProtocolClass getProtocolClass();
    void setProtocolClass(ProtocolClass value);

    SequencingSegmenting getSequencingSegmenting();
    void setSequencingSegmenting(SequencingSegmenting value);

    Credit getCredit();
    void setCredit(Credit value);
}
