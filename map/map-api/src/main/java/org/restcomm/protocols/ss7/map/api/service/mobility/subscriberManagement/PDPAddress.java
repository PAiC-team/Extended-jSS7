
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
PDP-Address ::= OCTET STRING (SIZE (1..16))
-- Octets are coded according to TS 3GPP TS 29.060 [105]

-- The possible size values are:
-- 1-7 octets X.25 address type
-- 4 octets IPv4 address type
-- 16 octets Ipv6 address type
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDPAddress extends Serializable {

    byte[] getData();

}