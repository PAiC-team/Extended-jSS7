
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;

/**
 *
<code>
oChangeOfPositionSpecificInfo [50] SEQUENCE {
  locationInformation  [50] LocationInformation OPTIONAL,
  ...,
  metDPCriteriaList    [51] MetDPCriteriaList {bound} OPTIONAL
},

MetDPCriteriaList {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE(1..10) OF MetDPCriterion {bound}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OChangeOfPositionSpecificInfo extends Serializable {

    LocationInformation getLocationInformation();

    ArrayList<MetDPCriterion> getMetDPCriteriaList();

}
