
package org.restcomm.protocols.ss7.cap.api.service.sms;

/**
*
continueSMS OPERATION ::= {
RETURN RESULT FALSE
ALWAYS RESPONDS FALSE
CODE opcode-continueSMS}
-- Direction: gsmSCF -> smsSSF, Timer: Tcuesms
-- This operation is used to request the smsSSF to proceed with
-- Short Message processing at the DP at which it previously suspended
-- Short Message processing to await gsmSCF instructions (i.e. proceed
-- to the next Point in Association in the SMS FSM). The smsSSF
-- continues SMS processing without substituting new data from the gsmSCF.

*
*
*
* @author sergey vetyutnev
*
*/
public interface ContinueSMSRequest extends SmsMessage {

}
