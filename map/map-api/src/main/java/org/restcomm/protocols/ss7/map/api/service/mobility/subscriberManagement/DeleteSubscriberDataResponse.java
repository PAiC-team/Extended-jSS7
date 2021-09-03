
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
 MAP V3:
 DeleteSubscriberDataRes ::= SEQUENCE {
   regionalSubscriptionResponse [0] RegionalSubscriptionResponse OPTIONAL,
   extensionContainer           ExtensionContainer OPTIONAL,
   ...
}

MAP V2:
DeleteSubscriberDataRes ::= SEQUENCE {
  regionalSubscriptionResponse [0] RegionalSubscriptionResponse OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DeleteSubscriberDataResponse extends MobilityMessage {

    RegionalSubscriptionResponse getRegionalSubscriptionResponse();

    MAPExtensionContainer getExtensionContainer();

}
