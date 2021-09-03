
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
TEID ::= OCTET STRING (SIZE (4))
-- This type carries the value part of the Tunnel Endpoint Identifier which is used to
-- distinguish between different tunnels between the same pair of entities which communicate
-- using the GPRS Tunnelling Protocol The encoding is defined in 3GPP TS 29.060.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TEID extends Serializable {

    byte[] getData();

}
