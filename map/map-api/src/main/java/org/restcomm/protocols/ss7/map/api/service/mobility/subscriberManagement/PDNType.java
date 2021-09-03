
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 PDN-Type ::= OCTET STRING (SIZE (1)) -- Octet is coded according to TS 3GPP TS 29.274 [140]
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDNType extends Serializable {

    int getData();

    PDNTypeValue getPDNTypeValue();

}
