
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 releaseGPRS {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT ReleaseGPRSArg {bound} RETURN RESULT FALSE ERRORS
 * {missingParameter | taskRefused | unknownPDPID} CODE opcode-releaseGPRS} -- Direction: gsmSCF -> gprsSSF, Timer: Trg -- This
 * operation is used to tear down an existing GPRS session or PDP Context at any phase.
 *
 * ReleaseGPRSArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { gprsCause [0] GPRSCause {bound}, pDPID [1] PDPID OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReleaseGPRSRequest extends GprsMessage {

    GPRSCause getGPRSCause();

    PDPID getPDPID();

}