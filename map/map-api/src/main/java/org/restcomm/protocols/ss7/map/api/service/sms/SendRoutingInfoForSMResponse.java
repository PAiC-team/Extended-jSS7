
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
RoutingInfoForSM-Res ::= SEQUENCE {
  imsi                    IMSI,
  locationInfoWithLMSI    [0] LocationInfoWithLMSI,
  extensionContainer      [4] ExtensionContainer OPTIONAL,
  ...,
  ip-sm-gwGuidance        [5] IP-SM-GW-Guidance OPTIONAL
}

MAP V2: RoutingInfoForSM-Res::= SEQUENCE {
  imsi                    IMSI,
  locationInfoWithLMSI    [0] LocationInfoWithLMSI,
  mwd-Set                 [2] BOOLEAN OPTIONAL,
  -- mwd-Set must be absent in version greater 1 ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SendRoutingInfoForSMResponse extends SmsMessage {

    IMSI getIMSI();

    LocationInfoWithLMSI getLocationInfoWithLMSI();

    MAPExtensionContainer getExtensionContainer();

    IpSmGwGuidance getIpSmGwGuidance();

    // for MAP V1 only
    Boolean getMwdSet();

}
