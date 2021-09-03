
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
<code>
ExtendedRoutingInfo ::= CHOICE {
  routingInfo        RoutingInfo,
  camelRoutingInfo   [8] CamelRoutingInfo
}
</code>
 *
 * @author cristian veliscu
 *
 */
public interface ExtendedRoutingInfo extends Serializable {

    RoutingInfo getRoutingInfo();

    CamelRoutingInfo getCamelRoutingInfo();

}