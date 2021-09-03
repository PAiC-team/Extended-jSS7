
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 entityReleasedGPRS {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT EntityReleasedGPRSArg {bound} RETURN RESULT TRUE
 * ERRORS {missingParameter | taskRefused | unknownPDPID} CODE opcode-entityReleasedGPRS} -- Direction: gprsSSF -> gsmSCF,
 * Timer: Terg -- This operation is used when the GPRS Session is detached or a PDP Context is diconnected and -- the associated
 * event is not armed for reporting. -- The usage of this operation is independent of the functional entity that initiates the
 * Detach -- or PDP Context Disconnection and is independent of the cause of the Detach or PDP Context -- Disconnect.
 *
 * EntityReleasedGPRSArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { gPRSCause [0] GPRSCause {bound}, pDPID [1] PDPID OPTIONAL,
 * ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EntityReleasedGPRSRequest extends GprsMessage {

    GPRSCause getGPRSCause();

    PDPID getPDPID();

}