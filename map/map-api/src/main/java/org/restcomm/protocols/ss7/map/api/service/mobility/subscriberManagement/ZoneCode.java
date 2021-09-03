
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 ZoneCode ::= OCTET STRING (SIZE (2)) -- internal structure is defined in TS 3GPP TS 23.003 [17]
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ZoneCode extends Serializable {

    byte[] getData();

    int getValue();

}
