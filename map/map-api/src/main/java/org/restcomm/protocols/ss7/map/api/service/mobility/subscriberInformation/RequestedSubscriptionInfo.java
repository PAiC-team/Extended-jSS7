
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;

/**
 *
<code>
RequestedSubscriptionInfo ::= SEQUENCE {
  requestedSS-Info                 [1] SS-ForBS-Code OPTIONAL,
  odb                              [2] NULL OPTIONAL,
  requestedCAMEL-SubscriptionInfo  [3] RequestedCAMEL-SubscriptionInfo OPTIONAL,
  supportedVLR-CAMEL-Phases        [4] NULL OPTIONAL,
  supportedSGSN-CAMEL-Phases       [5] NULL OPTIONAL,
  extensionContainer               [6] ExtensionContainer OPTIONAL,
  ...,
  additionalRequestedCAMEL-SubscriptionInfo [7] AdditionalRequestedCAMEL-SubscriptionInfo OPTIONAL,
  msisdn-BS-List                            [8] NULL OPTIONAL,
  csg-SubscriptionDataRequested             [9] NULL OPTIONAL,
  cw-Info                                   [10] NULL OPTIONAL,
  clip-Info                                 [11] NULL OPTIONAL,
  clir-Info                                 [12] NULL OPTIONAL,
  hold-Info                                 [13] NULL OPTIONAL,
  ect-Info                                  [14] NULL OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestedSubscriptionInfo extends Serializable {

    SSForBSCode getRequestedSSInfo();

    boolean getOdb();

    RequestedCAMELSubscriptionInfo getRequestedCAMELSubscriptionInfo();

    boolean getSupportedVlrCamelPhases();

    boolean getSupportedSgsnCamelPhases();

    MAPExtensionContainer getExtensionContainer();

    AdditionalRequestedCAMELSubscriptionInfo getAdditionalRequestedCamelSubscriptionInfo();

    boolean getMsisdnBsList();

    boolean getCsgSubscriptionDataRequested();

    boolean getCwInfo();

    boolean getClipInfo();

    boolean getClirInfo();

    boolean getHoldInfo();

    boolean getEctInfo();

}
