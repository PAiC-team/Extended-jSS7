
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;

/**
 *
 resetTimerGPRS OPERATION ::= { ARGUMENT ResetTimerGPRSArg RETURN RESULT FALSE ERRORS {missingParameter | parameterOutOfRange
 * | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownPDPID} CODE
 * opcode-resetTimerGPRS} -- Direction: gsmSCF > gprsSSF, Timer: Trtg -- This operation is used to request the gprsSSF to
 * refresh an application timer in the gprsSSF.
 *
 * ResetTimerGPRSArg ::= SEQUENCE { timerID [0] TimerID DEFAULT tssf, timervalue [1] TimerValue, ... }
 *
 * TimerValue ::= Integer4 -- Indicates the timer value (in seconds).
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ResetTimerGPRSRequest extends GprsMessage {

    TimerID getTimerID();

    int getTimerValue();

}