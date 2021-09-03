
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;

/**
 *
<code>
tServiceChangeSpecificInfo [1] SEQUENCE {
  ext-basicServiceCode [0] Ext-BasicServiceCode OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TServiceChangeSpecificInfo extends Serializable {

    ExtBasicServiceCode getExtBasicServiceCode();

}
