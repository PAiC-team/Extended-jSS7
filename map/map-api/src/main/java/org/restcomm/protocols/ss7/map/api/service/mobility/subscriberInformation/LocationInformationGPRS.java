
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;

/**
<code>
LocationInformationGPRS ::= SEQUENCE {
  cellGlobalIdOrServiceAreaIdOrLAI    [0] CellGlobalIdOrServiceAreaIdOrLAI OPTIONAL,
  routeingAreaIdentity                [1] RAIdentity OPTIONAL,
  geographicalInformation             [2] GeographicalInformation OPTIONAL,
  sgsn-Number                         [3] ISDN-AddressString OPTIONAL,
  selectedLSAIdentity                 [4] LSAIdentity OPTIONAL,
  extensionContainer                  [5] ExtensionContainer OPTIONAL,
  ...,
  sai-Present                         [6] NULL OPTIONAL,
  geodeticInformation                 [7] GeodeticInformation OPTIONAL,
  currentLocationRetrieved            [8] NULL OPTIONAL,
  ageOfLocationInformation            [9] AgeOfLocationInformation OPTIONAL
}
-- sai-Present indicates that the cellGlobalIdOrServiceAreaIdOrLAI parameter contains
-- a Service Area Identity.
-- currentLocationRetrieved shall be present if the location information
-- was retrieved after successful paging.
</code>
 *
 *
 *
 * @author amit bhayani
 *
 */
public interface LocationInformationGPRS extends Serializable {

    CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI();

    RAIdentity getRouteingAreaIdentity();

    GeographicalInformation getGeographicalInformation();

    ISDNAddressString getSGSNNumber();

    LSAIdentity getLSAIdentity();

    MAPExtensionContainer getExtensionContainer();

    boolean isSaiPresent();

    GeodeticInformation getGeodeticInformation();

    boolean isCurrentLocationRetrieved();

    Integer getAgeOfLocationInformation();

}
