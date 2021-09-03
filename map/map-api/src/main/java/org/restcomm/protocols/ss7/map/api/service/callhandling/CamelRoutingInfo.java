
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
<code>
CamelRoutingInfo ::= SEQUENCE {
  forwardingData              ForwardingData OPTIONAL,
  gmscCamelSubscriptionInfo   [0] GmscCamelSubscriptionInfo,
  extensionContainer          [1] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 * @author cristian veliscu
 *
 */
public interface CamelRoutingInfo {

    ForwardingData getForwardingData();

    GmscCamelSubscriptionInfo getGmscCamelSubscriptionInfo();

    MAPExtensionContainer getExtensionContainer();

}
