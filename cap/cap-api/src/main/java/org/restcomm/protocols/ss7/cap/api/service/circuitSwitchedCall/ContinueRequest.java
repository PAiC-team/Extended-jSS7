
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

/**
 *
<code>
continue OPERATION ::= {
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-continue
}
-- Direction: gsmSCF -> gsmSSF, Timer: Tcue
-- This operation is used to request the gsmSSF to proceed with call processing at the
-- DP at which it previously suspended call processing to await gsmSCF instructions
-- (i.e. proceed to the next point in call in the BCSM). The gsmSSF continues call
-- processing without substituting new data from gsmSCF.
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ContinueRequest extends CircuitSwitchedCallMessage {

}
