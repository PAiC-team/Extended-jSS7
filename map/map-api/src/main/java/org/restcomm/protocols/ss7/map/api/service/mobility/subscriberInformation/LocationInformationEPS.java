
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
LocationInformationEPS ::= SEQUENCE {
  e-utranCellGlobalIdentity     [0] E-UTRAN-CGI OPTIONAL,
  trackingAreaIdentity          [1] TA-Id OPTIONAL,
  extensionContainer            [2] ExtensionContainer OPTIONAL,
  geographicalInformation       [3] GeographicalInformation OPTIONAL,
  geodeticInformation           [4] GeodeticInformation OPTIONAL,
  currentLocationRetrieved      [5] NULL OPTIONAL,
  ageOfLocationInformation      [6] AgeOfLocationInformation OPTIONAL,
  ...,
  mme-Name                      [7] DiameterIdentity OPTIONAL
}
-- currentLocationRetrieved shall be present if the location information
-- was retrieved after successful paging.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LocationInformationEPS extends Serializable {

    EUtranCgi getEUtranCellGlobalIdentity();

    TAId getTrackingAreaIdentity();

    MAPExtensionContainer getExtensionContainer();

    GeographicalInformation getGeographicalInformation();

    GeodeticInformation getGeodeticInformation();

    boolean getCurrentLocationRetrieved();

    Integer getAgeOfLocationInformation();

    DiameterIdentity getMmeName();

}
