
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;

/**
 *
 resetTimerSMS {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT ResetTimerSMSArg {bound}
   RETURN RESULT FALSE
   ERRORS {missingParameter | parameterOutOfRange | taskRefused | unexpectedComponentSequence | unexpectedDataValue |
     unexpectedParameter}
   CODE opcode-resetTimerSMS
 }
 -- Direction: gsmSCF -> smsSSF, Timer: T rtsms
 -- This operation is used to request the smsSSF to refresh an application
 -- timer in the smsSSF.

ResetTimerSMSArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  timerID    [0] TimerID DEFAULT tssf,
  timervalue [1] TimerValue,
  extensions [2] Extensions {bound} OPTIONAL,
  ...
}

TimerValue ::= Integer4 -- Indicates the timer value (in seconds).

 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ResetTimerSMSRequest extends SmsMessage {

    TimerID getTimerID();

    int getTimerValue();

    CAPExtensions getExtensions();

}