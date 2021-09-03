
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 APN-OI-Replacement ::= OCTET STRING (SIZE (9..100)) -- Octets are coded as APN Operator Identifier according to TS 3GPP TS
 * 23.003 [17]
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface APNOIReplacement extends Serializable {

    byte[] getData();

}
