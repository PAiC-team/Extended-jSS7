
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ReceiveSequenceNumber;

/**
 *
 * This interface represents a SCCP data acknowledgement message for connection-oriented protocol class 3.
 *
 */
public interface SccpConnAkMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    ReceiveSequenceNumber getReceiveSequenceNumber();
    void setReceiveSequenceNumber(ReceiveSequenceNumber value);

    Credit getCredit();
    void setCredit(Credit value);
}
