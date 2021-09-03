
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 connectSMS {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT ConnectSMSArg {bound}
   RETURN RESULT FALSE
   ERRORS {missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue |
     unexpectedParameter}
   CODE opcode-connectSMS}
 -- Direction: gsmSCF -> gsmSSF or gprsSSF, Timer: Tconsms
 -- This operation is used to request the smsSSF to perform the SMS processing
 -- actions to route or forward a short message to a specified destination.

 ConnectSMSArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
   callingPartysNumber         [0] SMS-AddressString OPTIONAL,
   destinationSubscriberNumber [1] CalledPartyBCDNumber {bound} OPTIONAL,
   sMSCAddress                 [2] ISDN-AddressString OPTIONAL,
   extensions                  [10] Extensions {bound} OPTIONAL,
   ...
}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ConnectSMSRequest extends SmsMessage {

    SMSAddressString getCallingPartysNumber();

    CalledPartyBCDNumber getDestinationSubscriberNumber();

    ISDNAddressString getSMSCAddress();

    CAPExtensions getExtensions();

}