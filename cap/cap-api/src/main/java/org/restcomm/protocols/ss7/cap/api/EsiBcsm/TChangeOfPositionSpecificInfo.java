
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;

/**
 *
<code>
tChangeOfPositionSpecificInfo [51] SEQUENCE {
  locationInformation  [50] LocationInformation OPTIONAL,
  ...,
  metDPCriteriaList    [51] MetDPCriteriaList {bound} OPTIONAL
}

</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TChangeOfPositionSpecificInfo extends Serializable {

    LocationInformation getLocationInformation();

    ArrayList<MetDPCriterion> getMetDPCriteriaList();

}