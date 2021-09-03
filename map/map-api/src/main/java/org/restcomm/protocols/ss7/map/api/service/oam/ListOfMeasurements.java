
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
ListOfMeasurements ::= OCTET STRING (SIZE (4))
-- Octets are coded as described in 3GPP TS 32.422.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ListOfMeasurements extends Serializable {

    byte[] getData();

}
