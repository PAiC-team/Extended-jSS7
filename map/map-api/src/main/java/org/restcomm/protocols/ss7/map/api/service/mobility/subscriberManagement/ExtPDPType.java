
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
Ext-PDP-Type ::= OCTET STRING (SIZE (2))
-- Octets are coded, similarly to PDP-Type, according to TS 3GPP TS 29.060 [105].
-- Only the value IPv4v6 is allowed for this parameter.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtPDPType extends Serializable {

    byte[] getData();

}
