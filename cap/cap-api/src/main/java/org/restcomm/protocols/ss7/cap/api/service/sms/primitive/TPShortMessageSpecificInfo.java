
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
 *
 TPShortMessageSpecificInfo ::= OCTET STRING (SIZE (1)) -- contains the 1st octect of the applicable TPDU or the SMS-COMMAND
 * TPDU as specified in -- 3GPP TS 23.040 [6].
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TPShortMessageSpecificInfo extends Serializable {

    int getData();

}