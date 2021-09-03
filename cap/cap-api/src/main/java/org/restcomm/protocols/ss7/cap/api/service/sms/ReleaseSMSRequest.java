
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.RPCause;

/**
 *
 releaseSMS OPERATION ::= { ARGUMENT ReleaseSMSArg RETURN RESULT FALSE ALWAYS RESPONDS FALSE CODE opcode-releaseSMS} --
 * Direction: gsmSCF -> gsmSSF or gprsSSF, Timer: Trelsms -- This operation is used to prevent an attempt to submit or deliver a
 * short message.
 *
 * ReleaseSMSArg ::= RPCause
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReleaseSMSRequest extends SmsMessage {

    RPCause getRPCause();

}