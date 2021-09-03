
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 connectGPRS {PARAMETERS-BOUND: bound} OPERATION::= { ARGUMENT ConnectGPRSArg {bound} RETURN RESULT FALSE ERRORS
 * {missingParameter | parameterOutOfRange | unknownPDPID | systemFailure | taskRefused | unexpectedComponentSequence |
 * unexpectedDataValue | unexpectedParameter} CODE opcode-connectGPRS} -- Direction: gsmSCF -> gprsSSF, Timer: Tcong -- This
 * operation is used to modify the Access Point Name used when establishing a PDP Context.
 *
 * ConnectGPRSArg {PARAMETERS-BOUND: bound}::= SEQUENCE { accessPointName [0] AccessPointName {bound}, pdpID [1] PDPID OPTIONAL,
 * ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ConnectGPRSRequest extends GprsMessage {

    AccessPointName getAccessPointName();

    PDPID getPDPID();

}