
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;

/**
 *
<code>
callingAddressAndService [30] SEQUENCE {
  callingAddressValue  [0] Digits {bound},
  serviceKey           [1] ServiceKey,
  ...
}

ServiceKey::= Integer4
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallingAddressAndService extends Serializable {

    Digits getCallingAddressValue();

    int getServiceKey();

}