
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
<code>
PositioningDataInformation ::= OCTET STRING (SIZE (2..10))
-- Refers to the Positioning Data defined in 3GPP TS 49.031.
-- This is composed of 2 or more octets with an internal structure according to
-- 3GPP TS 49.031.

maxPositioningDataInformation INTEGER ::= 10
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PositioningDataInformation extends Serializable {

    byte[] getData();

}
