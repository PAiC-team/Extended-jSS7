
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
 *
 TPDataCodingScheme ::= OCTET STRING (SIZE (1)) -- TP Data Coding Scheme according to 3GPP TS 23.040 [6]
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TPDataCodingScheme extends Serializable {

    int getData();

}