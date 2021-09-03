
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
<code>
RouteingNumber ::= TBCD-STRING (SIZE (1..5))
</code>
 *
 * @author amit bhayani
 *
 */
public interface RouteingNumber extends Serializable {

    String getRouteingNumber();

}
