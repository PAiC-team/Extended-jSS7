
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.SequencingSegmenting;

/**
 *
 * This interface represents a SCCP data form 2 message for connection-oriented protocol class 3.
 *
 */
public interface SccpConnDt2Message extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    SequencingSegmenting getSequencingSegmenting();
    void setSequencingSegmenting(SequencingSegmenting value);

    byte[] getUserData();
    void setUserData(byte[] data);
}
