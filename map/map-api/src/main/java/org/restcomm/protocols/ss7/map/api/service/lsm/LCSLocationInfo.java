
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedLCSCapabilitySets;

/**
 *
<code>
LCSLocationInfo ::= SEQUENCE {
  networkNode-Number       ISDN-AddressString,
  -- NetworkNode-number can be msc-number, sgsn-number or a dummy value of "0"
  lmsi                     [0] LMSI OPTIONAL,
  extensionContainer       [1] ExtensionContainer OPTIONAL,
  ... ,
  gprsNodeIndicator        [2] NULL OPTIONAL,
  -- gprsNodeIndicator is set only if the SGSN number is sent as the Network Node Number
  additional-Number        [3] Additional-Number OPTIONAL,
  supportedLCS-CapabilitySets    [4] SupportedLCS-CapabilitySets OPTIONAL,
  additional-LCS-CapabilitySets  [5] SupportedLCS-CapabilitySets OPTIONAL,
  mme-Name                       [6] DiameterIdentity OPTIONAL,
  aaa-Server-Name                [8] DiameterIdentity OPTIONAL
}
</code>
 *
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface LCSLocationInfo extends Serializable {

    ISDNAddressString getNetworkNodeNumber();

    LMSI getLMSI();

    MAPExtensionContainer getExtensionContainer();

    boolean getGprsNodeIndicator();

    AdditionalNumber getAdditionalNumber();

    SupportedLCSCapabilitySets getSupportedLCSCapabilitySets();

    SupportedLCSCapabilitySets getAdditionalLCSCapabilitySets();

    DiameterIdentity getMmeName();

    DiameterIdentity getAaaServerName();

}
