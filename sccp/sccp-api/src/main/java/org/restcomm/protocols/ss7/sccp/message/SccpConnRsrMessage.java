
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCause;

/**
 *
 * This interface represents a SCCP reset request message for connection-oriented protocol class 3.
 *
 */
public interface SccpConnRsrMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    LocalReference getSourceLocalReferenceNumber();
    void setSourceLocalReferenceNumber(LocalReference number);

    ResetCause getResetCause();
    void setResetCause(ResetCause value);
}
