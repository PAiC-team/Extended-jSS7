
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.UUData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 *
<code>
CAP V4:
InitialDPArgExtension {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  gmscAddress                   [0] ISDN-AddressString OPTIONAL,
  forwardingDestinationNumber   [1] CalledPartyNumber {bound} OPTIONAL,
  ms-Classmark2                 [2] MS-Classmark2 OPTIONAL,
  iMEI                          [3] IMEI OPTIONAL,
  supportedCamelPhases          [4] SupportedCamelPhases OPTIONAL,
  offeredCamel4Functionalities  [5] OfferedCamel4Functionalities OPTIONAL,
  bearerCapability2             [6] BearerCapability {bound} OPTIONAL,
  ext-basicServiceCode2         [7] Ext-BasicServiceCode OPTIONAL,
  highLayerCompatibility2       [8] HighLayerCompatibility OPTIONAL,
  lowLayerCompatibility         [9] LowLayerCompatibility {bound} OPTIONAL,
  lowLayerCompatibility2        [10] LowLayerCompatibility {bound} OPTIONAL,
  ...,
  enhancedDialledServicesAllowed [11] NULL OPTIONAL,
  uu-Data                        [12] UU-Data OPTIONAL
  collectInformationAllowed      [13] NULL OPTIONAL,
  releaseCallArgExtensionAllowed [14] NULL OPTIONAL
}

CAP V2: InitialDPArgExtension ::= SEQUENCE {
  naCarrierInformation          [0] NACarrierInformation OPTIONAL,
  gmscAddress                   [1] ISDN-AddressString OPTIONAL,
  ...
}

-- If iPSSPCapabilities is not present then this denotes that a colocated gsmSRF is not
-- supported by the gsmSSF. If present, then the gsmSSF supports a colocated gsmSRF capable
-- of playing announcements via elementaryMessageIDs and variableMessages, the playing of
-- tones and the collection of DTMF digits. Other supported capabilities are explicitly
-- detailed in the IPSSPCapabilities parameter itself.
-- Carrier is included at the discretion of the gsmSSF operator.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InitialDPArgExtension extends Serializable {

    ISDNAddressString getGmscAddress();

    CalledPartyNumberCap getForwardingDestinationNumber();

    MSClassmark2 getMSClassmark2();

    IMEI getIMEI();

    SupportedCamelPhases getSupportedCamelPhases();

    OfferedCamel4Functionalities getOfferedCamel4Functionalities();

    BearerCapability getBearerCapability2();

    ExtBasicServiceCode getExtBasicServiceCode2();

    HighLayerCompatibilityInap getHighLayerCompatibility2();

    LowLayerCompatibility getLowLayerCompatibility();

    LowLayerCompatibility getLowLayerCompatibility2();

    boolean getEnhancedDialledServicesAllowed();

    UUData getUUData();

    boolean getCollectInformationAllowed();

    boolean getReleaseCallArgExtensionAllowed();

}
