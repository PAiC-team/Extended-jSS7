
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;

/**
 *
<code>
resetTimer {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT ResetTimerArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | parameterOutOfRange | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownCSID}
  CODE opcode-resetTimer
}
-- Direction: gsmSCF -> gsmSSF, Timer: Trt
-- This operation is used to request the gsmSSF to refresh an application timer in the gsmSSF.

ResetTimerArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  timerID        [0] TimerID DEFAULT tssf,
  timervalue     [1] TimerValue,
  extensions     [2] Extensions {bound} OPTIONAL,
  callSegmentID  [3] CallSegmentID {bound} OPTIONAL,
  ...
}

TimerID ::= ENUMERATED { tssf (0) }
-- Indicates the timer to be reset. TimerValue ::= Integer4
-- Indicates the timer value (in seconds).

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..bound.&numOfCSs)
numOfCSs ::= 127
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface ResetTimerRequest extends CircuitSwitchedCallMessage {

    TimerID getTimerID();

    int getTimerValue();

    CAPExtensions getExtensions();

    Integer getCallSegmentID();

}