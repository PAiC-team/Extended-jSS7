
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;

/**
 *
<code>
MAP V1:

NoteSubscriberPresent ::= OPERATION
--Timer s

ARGUMENT imsi IMSI

</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NoteSubscriberPresentRequest extends SmsMessage {

    IMSI getIMSI();

}
