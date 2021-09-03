
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;

/**
 *
 disconnectForwardConnectionWithArgument {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT DisconnectForwardConnectionWithArgumentArg {bound}
   RETURN RESULT FALSE]
   ERRORS {missingParameter | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue |
           unexpectedParameter | unknownCSID} CODE opcode-dFCWithArgument}
-- Direction gsmSCF -> gsmSSF, Timer Tdfcwa
-- This operation is used to disconnect a forward temporary connection or a connection to a
-- resource. Refer to clause 11 for a description of the procedures associated with this operation.

DisconnectForwardConnectionWithArgumentArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
    callSegmentID  [1] CallSegmentID {bound} OPTIONAL,
    extensions     [2] Extensions {bound} OPTIONAL,
    ... }

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DisconnectForwardConnectionWithArgumentRequest extends CircuitSwitchedCallMessage {

    Integer getCallSegmentID();

    CAPExtensions getExtensions();

}