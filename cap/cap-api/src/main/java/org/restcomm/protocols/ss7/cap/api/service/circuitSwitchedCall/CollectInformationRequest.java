
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

/**
 *
<code>
collectInformation OPERATION::= {
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-collectInformation
}
-- Direction: gsmSCF-> gsmSSF, Timer: Tci
-- This operation is used to request the gsmSSF to perform the call
-- processing actions to prompt a calling party for additional digits.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CollectInformationRequest extends CircuitSwitchedCallMessage {

}
