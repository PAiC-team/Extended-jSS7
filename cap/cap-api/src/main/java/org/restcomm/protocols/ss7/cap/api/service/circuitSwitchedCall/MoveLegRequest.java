
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
moveLeg {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT MoveLegArg {bound}
   RETURN RESULT TRUE
   ERRORS {
     missingParameter | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownLegID
   }
   CODE opcode-moveLeg
}
-- Direction: gsmSCF -> gsmSSF, Timer: Tml
-- This operation is used by the gsmSCF to move a leg from one call segment to another call segment
-- within the same call segment association.

MoveLegArg {PARAMETERS-BOUND : bound} ::= SEQUENCE{
  legIDToMove  [0] LegID,
  extensions   [2] Extensions {bound} OPTIONAL,
  ...
}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MoveLegRequest extends CircuitSwitchedCallMessage {

    LegID getLegIDToMove();

    CAPExtensions getExtensions();

}
