
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
GPRS-CamelTDPData ::= SEQUENCE {
  gprs-TriggerDetectionPoint [0] GPRS-TriggerDetectionPoint,
  serviceKey                 [1] ServiceKey,
  gsmSCF-Address             [2] ISDN-AddressString,
  defaultSessionHandling     [3] DefaultGPRS-Handling,
  extensionContainer         [4] ExtensionContainer OPTIONAL,
  ...
}

ServiceKey ::= INTEGER (0..2147483647)
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSCamelTDPData extends Serializable {

    GPRSTriggerDetectionPoint getGPRSTriggerDetectionPoint();

    long getServiceKey();

    ISDNAddressString getGsmSCFAddress();

    DefaultGPRSHandling getDefaultSessionHandling();

    MAPExtensionContainer getExtensionContainer();

}
