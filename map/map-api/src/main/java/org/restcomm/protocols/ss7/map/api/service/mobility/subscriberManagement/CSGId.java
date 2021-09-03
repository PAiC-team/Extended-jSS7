
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.mobicents.protocols.asn.BitSetStrictLength;

/**
 *
<code>
CSG-Id ::= BIT STRING (SIZE (27))
-- coded according to 3GPP TS 23.003 [17].
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CSGId extends Serializable {

    BitSetStrictLength getData();

    // TODO: add implementing of internal structure (?)

}
