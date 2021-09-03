
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;

/**
 *
<code>
calledAddressAndService [29] SEQUENCE {
  calledAddressValue  [0] Digits {bound},
  serviceKey          [1] ServiceKey,
  ...
},

ServiceKey::= Integer4
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CalledAddressAndService extends Serializable {

    Digits getCalledAddressValue();

    int getServiceKey();

}