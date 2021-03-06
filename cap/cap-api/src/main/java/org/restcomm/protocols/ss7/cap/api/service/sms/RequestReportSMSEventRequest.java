
package org.restcomm.protocols.ss7.cap.api.service.sms;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSEvent;

/**
 *
 requestReportSMSEvent {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT RequestReportSMSEventArg {bound}
   RETURN RESULT FALSE
   ERRORS {missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence |
     unexpectedDataValue | unexpectedParameter}
   CODE opcode-requestReportSMSEvent}
   -- Direction: gsmSCF -> gsmSSF or gprsSSF, Timer: Trrbsms
   -- This operation is used to request the gsmSSF or gprsSSF to monitor for a
   -- Short Message related event (FSM events such as submission, delivery or failure)
   -- and to send a notification to the gsmSCF when the event is detected.

RequestReportSMSEventArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  sMSEvents  [0] SEQUENCE SIZE (1..10) OF SMSEvent,
  extensions [10] Extensions {bound} OPTIONAL, ...
}
-- Indicates the Short Message related events(s) for notification.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestReportSMSEventRequest extends SmsMessage {

    ArrayList<SMSEvent> getSMSEvents();

    CAPExtensions getExtensions();

}