
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
<code>
MSNetworkCapability ::= OCTET STRING (SIZE (1..8))
-- This parameter carries the value part of the MS Network Capability IE defined in
-- 3GPP TS 24.008 [35].
</code>
 *
 * @author amit bhayani
 *
 */
public interface MSNetworkCapability extends Serializable {

    byte[] getData();

}
