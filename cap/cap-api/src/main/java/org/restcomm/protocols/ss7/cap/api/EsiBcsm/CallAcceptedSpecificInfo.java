
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;

/**
 *
<code>
callAcceptedSpecificInfo [20] SEQUENCE {
  locationInformation [50] LocationInformation OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallAcceptedSpecificInfo extends Serializable {

    LocationInformation getLocationInformation();

}
