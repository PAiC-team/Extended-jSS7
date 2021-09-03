
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;

/**
 *
<code>
oTermSeizedSpecificInfo [13] SEQUENCE {
  locationInformation [50] LocationInformation OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OTermSeizedSpecificInfo extends Serializable {

    LocationInformation getLocationInformation();

}
