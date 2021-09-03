
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import java.io.Serializable;

import org.mobicents.protocols.asn.BitSetStrictLength;

/**
 *
 UESBI-IuB ::= BIT STRING (SIZE(1..128)) -- See 3GPP TS 25.413
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UESBIIuB extends Serializable {

    BitSetStrictLength getData();

}
