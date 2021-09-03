
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventSpecificInformationSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;

/**
 *
 eventReportSMS {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT EventReportSMSArg {bound}
   RETURN RESULT FALSE
   ALWAYS RESPONDS FALSE
   CODE opcode-eventReportSMS
 }
 -- Direction: gsmSSF or gprsSSF -> gsmSCF, Timer: Terbsms
 -- This operation is used to notify the gsmSCF of a Short Message related event (FSM events
 -- such as submission, delivery or failure) previously requested by the gsmSCF in a
 -- RequestReportSMSEvent operation.

 EventReportSMSArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
   eventTypeSMS                [0] EventTypeSMS,
   eventSpecificInformationSMS [1] EventSpecificInformationSMS OPTIONAL,
   miscCallInfo                [2] MiscCallInfo DEFAULT {messageType request},
   extensions                  [10] Extensions {bound} OPTIONAL,
   ...
}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EventReportSMSRequest extends SmsMessage {

    EventTypeSMS getEventTypeSMS();

    EventSpecificInformationSMS getEventSpecificInformationSMS();

    MiscCallInfo getMiscCallInfo();

    CAPExtensions getExtensions();

}