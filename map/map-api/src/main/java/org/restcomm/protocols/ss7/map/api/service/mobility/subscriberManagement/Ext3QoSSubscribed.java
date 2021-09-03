
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
Ext3-QoS-Subscribed ::= OCTET STRING (SIZE (1..2))
-- Octets 1-2 are coded according to 3GPP TS 24.008 [35] Quality of Service Octets 17-18.

Octet 17: Maximum bit rate for uplink (extended)
Octet 18: Guaranteed bit rate for uplink (extended)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Ext3QoSSubscribed extends Serializable {

    byte[] getData();

    ExtQoSSubscribed_BitRateExtended getMaximumBitRateForUplinkExtended();

    ExtQoSSubscribed_BitRateExtended getGuaranteedBitRateForUplinkExtended();

}
