
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

/**
 *
<code>
SS-Info ::= CHOICE {
  forwardingInfo   [0] ForwardingInfo,
  callBarringInfo  [1] CallBarringInfo,
  ss-Data          [3] SS-Data
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSInfo extends Serializable {

    ForwardingInfo getForwardingInfo();

    CallBarringInfo getCallBarringInfo();

    SSData getSsData();

}