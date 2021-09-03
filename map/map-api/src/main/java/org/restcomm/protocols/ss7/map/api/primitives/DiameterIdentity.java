
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
<code>
DiameterIdentity ::= OCTET STRING (SIZE(9..55))
-- content of DiameterIdentity is defined in IETF RFC 3588 [139]
IETF RFC 3588: The DiameterIdentity format is derived from the OctetString AVP Base Format.

DiameterIdentity = FQDN

DiameterIdentity value is used to uniquely identify a Diameter node for purposes of duplicate connection and routing loop detection.

The contents of the string MUST be the FQDN of the Diameter node. If multiple Diameter nodes run on the same host, each
Diameter node MUST be assigned a unique DiameterIdentity. If a Diameter node can be identified by several FQDNs, a single
FQDN should be picked at startup, and used as the only DiameterIdentity for that node, whatever the connection it is sent on.

FQDN=fully qualified domain name
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DiameterIdentity extends Serializable {

    byte[] getData();

    // TODO: add implementing of internal structure (?)

}
