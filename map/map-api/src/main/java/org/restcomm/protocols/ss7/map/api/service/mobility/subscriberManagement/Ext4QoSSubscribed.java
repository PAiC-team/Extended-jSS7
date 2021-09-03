
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
Ext4-QoS-Subscribed ::= OCTET STRING (SIZE (1))
-- Octet 1:
-- Evolved Allocation/Retention Priority. This octet encodes the Priority Level (PL),
-- the Preemption Capability (PCI) and Preemption Vulnerability (PVI) values, as
-- described in 3GPP TS 29.060 [105].
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Ext4QoSSubscribed extends Serializable {

    int getData();

}
