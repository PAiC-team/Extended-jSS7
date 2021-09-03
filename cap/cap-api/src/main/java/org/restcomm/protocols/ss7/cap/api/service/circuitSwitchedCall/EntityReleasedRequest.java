
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.BCSMFailure;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CallSegmentFailure;

/**
 *
 entityReleased {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT EntityReleasedArg {bound} RETURN RESULT FALSE ALWAYS
 * RESPONDS FALSE CODE opcode-entityReleased} -- Direction: gsmSSF -> gsmSCF, Timer: Ter -- This operation is used by the gsmSSF
 * to inform the gsmSCF of an error or exception
 *
 * EntityReleasedArg {PARAMETERS-BOUND : bound} ::= CHOICE { callSegmentFailure [0] CallSegmentFailure {bound}, bCSM-Failure [1]
 * BCSM-Failure {bound} }
 *
 * @author sergey vetyutnev
 *
 */
public interface EntityReleasedRequest extends CircuitSwitchedCallMessage {

    CallSegmentFailure getCallSegmentFailure();

    BCSMFailure getBcsmFailure();

}