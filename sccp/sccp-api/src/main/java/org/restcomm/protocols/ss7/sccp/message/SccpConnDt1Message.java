
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.SegmentingReassembling;

/**
 *
 * This interface represents a SCCP data form 1 message for connection-oriented protocol class 2.
 *
 */
public interface SccpConnDt1Message extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    SegmentingReassembling getSegmentingReassembling();
    void setSegmentingReassembling(SegmentingReassembling value);

    byte[] getUserData();
    void setUserData(byte[] data);
}
