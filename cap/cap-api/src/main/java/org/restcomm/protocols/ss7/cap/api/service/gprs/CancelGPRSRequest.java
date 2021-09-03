
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 cancelGPRS OPERATION ::= { ARGUMENT CancelGPRSArg RETURN RESULT FALSE ERRORS {missingParameter | taskRefused | unknownPDPID}
 * CODE opcode-cancelGPRS} -- Direction: gsmSCF -> gprsSSF, Timer: Tcag -- This generic operation cancels all previous requests,
 * -- i.e. all EDPs and reports can be cancelled by the gsmSCF.
 *
 * CancelGPRSArg ::= SEQUENCE { pDPID [0] PDPID OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CancelGPRSRequest extends GprsMessage {

    PDPID getPDPID();

}