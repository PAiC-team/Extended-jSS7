
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.Burst;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.LegOrCallSegment;

/**
 *
 playTone {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT PlayToneArg {bound} RETURN RESULT FALSE ERRORS {missingParameter
 * | parameterOutOfRange | systemFailure | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter |
 * unknownLegID | unknownCSID} CODE opcode-playTone} -- Direction: gsmSCF -> gsmSSF, Timer: Tpt -- This operation is used to
 * play tones to either a leg or a call segment using -- the MSC's tone generator.
 *
 * PlayToneArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { legOrCallSegment [0] LegOrCallSegment {bound}, bursts [1] Burst,
 * extensions [2] Extensions {bound} OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PlayToneRequest extends CircuitSwitchedCallMessage {

    LegOrCallSegment getLegOrCallSegment();

    Burst getBursts();

    CAPExtensions getExtensions();

}