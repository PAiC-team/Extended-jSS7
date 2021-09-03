
package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.ErrorCause;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;

/**
 *
 * This interface represents a SCCP protocol data unit error message for connection-oriented protocol classes 2 and 3.
 *
 */
public interface SccpConnErrMessage extends SccpConnMessage {

    LocalReference getDestinationLocalReferenceNumber();
    void setDestinationLocalReferenceNumber(LocalReference number);

    ErrorCause getErrorCause();
    void setErrorCause(ErrorCause value);
}
