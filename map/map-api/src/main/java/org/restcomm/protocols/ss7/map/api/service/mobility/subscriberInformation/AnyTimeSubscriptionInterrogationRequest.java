
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
MAP V3:

anyTimeSubscriptionInterrogation OPERATION ::= {
  --Timer m
  ARGUMENT AnyTimeSubscriptionInterrogationArg
  RESULT AnyTimeSubscriptionInterrogationRes
  ERRORS { atsi-NotAllowed | dataMissing | unexpectedDataValue | unknownSubscriber | bearerServiceNotProvisioned |
           teleserviceNotProvisioned | callBarred | illegalSS-Operation | ss-NotAvailable | informationNotAvailable}
  CODE local:62
}

AnyTimeSubscriptionInterrogationArg ::= SEQUENCE {
  subscriberIdentity          [0] SubscriberIdentity,
  requestedSubscriptionInfo   [1] RequestedSubscriptionInfo,
  gsmSCF-Address              [2] ISDN-AddressString,
  extensionContainer          [3] ExtensionContainer OPTIONAL,
  longFTN-Supported           [4] NULL OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AnyTimeSubscriptionInterrogationRequest extends MobilityMessage {

    SubscriberIdentity getSubscriberIdentity();

    RequestedSubscriptionInfo getRequestedSubscriptionInfo();

    ISDNAddressString getGsmScfAddress();

    MAPExtensionContainer getExtensionContainer();

    boolean getLongFTNSupported();

}
