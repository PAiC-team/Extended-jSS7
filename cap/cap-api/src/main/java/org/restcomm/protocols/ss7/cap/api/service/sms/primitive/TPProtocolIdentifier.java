
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
 *
 TPProtocolIdentifier ::= OCTET STRING (SIZE (1)) -- indicates the protocol used above the SM-Transfer Layer as specified in
 * 3GPP TS 23.040 [6].
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TPProtocolIdentifier extends Serializable {

    int getData();

}