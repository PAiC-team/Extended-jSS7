
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;

/**
 *
<code>
Ext-CwFeature ::= SEQUENCE {
  basicService     [1] Ext-BasicServiceCode,
  ss-Status        [2] Ext-SS-Status,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtCwFeature extends Serializable {

    ExtBasicServiceCode getBasicService();

    ExtSSStatus getSsStatus();

}
