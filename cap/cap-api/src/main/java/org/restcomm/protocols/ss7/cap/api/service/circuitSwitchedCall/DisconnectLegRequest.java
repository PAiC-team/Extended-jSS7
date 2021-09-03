
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
 disconnectLeg {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT DisconnectLegArg {bound}
   RETURN RESULT TRUE
   ERRORS {
     missingParameter | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownLegID
   }
   CODE opcode-disconnectLeg}
-- Direction: gsmSCF -> gsmSSF, Timer Tdl
-- This operation is used by the gsmSCF to release a specific leg associated with the call and
-- retain any other legs not specified in the DisconnectLeg. Refer to clause 11 for a description
-- of the procedures associated with this operation.

DisconnectLegArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  legToBeReleased   [0] LegID,
  releaseCause      [1] Cause {bound} OPTIONAL,
  extensions        [2] Extensions {bound} OPTIONAL,
  ...
}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DisconnectLegRequest extends CircuitSwitchedCallMessage {

    LegID getLegToBeReleased();

    CauseCap getReleaseCause();

    CAPExtensions getExtensions();

}