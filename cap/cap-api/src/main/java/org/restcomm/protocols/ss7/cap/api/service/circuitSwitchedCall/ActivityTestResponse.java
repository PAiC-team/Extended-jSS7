
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

/**
 *
<code>
activityTest OPERATION ::= {
  RETURN RESULT TRUE
  CODE opcode-activityTest
}
-- Direction: gsmSCF -> gsmSSF, Timer: T at
-- This operation is used to check for the continued existence of a relationship
-- between the gsmSCF and gsmSSF, assist gsmSSF or gsmSRF. If the relationship is
-- still in existence, then the gsmSSF will respond. If no reply is received,
-- then the gsmSCF will assume that the gsmSSF, assist gsmSSF or gsmSRF has failed
-- in some way.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ActivityTestResponse extends CircuitSwitchedCallMessage {

}
