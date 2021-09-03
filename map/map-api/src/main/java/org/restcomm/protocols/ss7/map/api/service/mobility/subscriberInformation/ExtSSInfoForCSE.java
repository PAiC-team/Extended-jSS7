
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
Ext-SS-InfoFor-CSE ::= CHOICE {
  forwardingInfoFor-CSE   [0] Ext-ForwardingInfoFor-CSE,
  callBarringInfoFor-CSE  [1] Ext-CallBarringInfoFor-CSE
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtSSInfoForCSE extends Serializable {

    ExtForwardingInfoForCSE getForwardingInfoForCSE();

    ExtCallBarringInfoForCSE getCallBarringInfoForCSE();

}
