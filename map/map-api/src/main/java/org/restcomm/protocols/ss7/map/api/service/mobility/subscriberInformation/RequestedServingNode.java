
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
RequestedServingNode ::= BIT STRING {
  mmeAndSgsn (0)
} (SIZE (1..8))
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestedServingNode extends Serializable {

    boolean getMmeAndSgsn();

}
