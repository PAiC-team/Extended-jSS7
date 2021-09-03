
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
<code>
RoutingInfo ::= CHOICE {
  roamingNumber   ISDN-AddressString,
  forwardingData  ForwardingData
}
</code>
 *
 * @author cristian veliscu
 *
 */
public interface RoutingInfo extends Serializable {

     ISDNAddressString getRoamingNumber();

     ForwardingData getForwardingData();

}