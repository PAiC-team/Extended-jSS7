
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
GPRSChargingID ::= OCTET STRING (SIZE (4))
-- The Charging ID is a unique four octet value generated by the GGSN when
-- a PDP Context is activated. A Charging ID is generated for each activated context.
-- The encoding is defined in 3GPP TS 29.060.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSChargingID extends Serializable {

    byte[] getData();

}
