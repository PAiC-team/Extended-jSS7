
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 continueGPRS OPERATION ::= { ARGUMENT ContinueGPRSArg RETURN RESULT FALSE ERRORS {missingParameter | unknownPDPID | -
 * Direction: gsmSCF -> gprsSSF, Timer: Tcueg -- This operation is used to request the gprsSSF to proceed with processing at the
 * DP at -- which it previously suspended processing to await gsmSCF instructions (i.e., proceed to -- the next point in
 * processing in the Attach/Detach state model or PDP Context -- state model) substituting new data from the gsmSCF.
 *
 * ContinueGPRSArg ::= SEQUENCE { pDPID [0] PDPID OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ContinueGPRSRequest extends GprsMessage {

    PDPID getPDPID();

}