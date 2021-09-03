
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;

/**
 *
<code>
oServiceChangeSpecificInfo [0] SEQUENCE {
  ext-basicServiceCode [0] Ext-BasicServiceCode OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OServiceChangeSpecificInfo extends Serializable {

    ExtBasicServiceCode getExtBasicServiceCode();

}
