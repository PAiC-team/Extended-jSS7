
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
<code>
splitLeg {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT SplitLegArg {bound}
  RETURN RESULT TRUE
  ERRORS {missingParameter | unexpectedComponentSequence | unexpectedParameter | unexpectedDataValue | systemFailure | taskRefused | unknownLegID}
  CODE opcode-splitLeg
}
-- Direction: gsmSCF -> gsmSSF, Timer Tsl
-- This operation is used by the gsmSCF to separate a leg from its source call segment and
-- place it in a new call segment within the same call segment association.

SplitLegArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  legToBeSplit    [0] LegID,
  newCallSegment  [1] CallSegmentID {bound} OPTIONAL,
  extensions      [2] Extensions {bound} OPTIONAL,
  ...
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SplitLegRequest extends CircuitSwitchedCallMessage {

    LegID getLegToBeSplit();

    Integer getNewCallSegment();

    CAPExtensions getExtensions();

}