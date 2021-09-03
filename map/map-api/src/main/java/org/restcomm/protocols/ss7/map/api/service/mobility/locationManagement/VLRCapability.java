
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 *
 <code>
VLR-Capability ::= SEQUENCE{
  supportedCamelPhases          [0] SupportedCamelPhases OPTIONAL,
  extensionContainer            ExtensionContainer OPTIONAL,
  ... ,
  solsaSupportIndicator         [2] NULL OPTIONAL,
  istSupportIndicator           [1] IST-SupportIndicator OPTIONAL,
  superChargerSupportedInServingNetworkEntity [3] SuperChargerInfo OPTIONAL,
  longFTN-Supported             [4] NULL OPTIONAL,
  supportedLCS-CapabilitySets   [5] SupportedLCS-CapabilitySets OPTIONAL,
  offeredCamel4CSIs             [6] OfferedCamel4CSIs OPTIONAL,
  supportedRAT-TypesIndicator   [7] SupportedRAT-Types OPTIONAL,
  longGroupID-Supported         [8] NULL OPTIONAL,
  mtRoamingForwardingSupported  [9] NULL OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VLRCapability extends Serializable {

    SupportedCamelPhases getSupportedCamelPhases();

    MAPExtensionContainer getExtensionContainer();

    boolean getSolsaSupportIndicator();

    ISTSupportIndicator getIstSupportIndicator();

    SuperChargerInfo getSuperChargerSupportedInServingNetworkEntity();

    boolean getLongFtnSupported();

    SupportedLCSCapabilitySets getSupportedLCSCapabilitySets();

    OfferedCamel4CSIs getOfferedCamel4CSIs();

    SupportedRATTypes getSupportedRATTypesIndicator();

    boolean getLongGroupIDSupported();

    boolean getMtRoamingForwardingSupported();

}
