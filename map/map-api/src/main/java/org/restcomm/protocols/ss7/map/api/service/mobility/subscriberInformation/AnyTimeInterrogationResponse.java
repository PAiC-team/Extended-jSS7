package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
AnyTimeInterrogationRes ::= SEQUENCE {
  subscriberInfo       SubscriberInfo,
  extensionContainer   ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 * @author abhayani
 *
 */
public interface AnyTimeInterrogationResponse extends MobilityMessage {

    SubscriberInfo getSubscriberInfo();

    MAPExtensionContainer getExtensionContainer();

}
