package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
MAP V3:

anyTimeInterrogation OPERATION ::= {
  --Timer m
  ARGUMENT AnyTimeInterrogationArg
  RESULT AnyTimeInterrogationRes ERRORS { systemFailure | ati-NotAllowed | dataMissing | unexpectedDataValue | unknownSubscriber }
  CODE local:71
}


AnyTimeInterrogationArg ::= SEQUENCE {
  subscriberIdentity  [0] SubscriberIdentity,
  requestedInfo       [1] RequestedInfo,
  gsmSCF-Address      [3] ISDN-AddressString,
  extensionContainer  [2] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 * @author abhayani
 *
 */
public interface AnyTimeInterrogationRequest extends MobilityMessage {

    SubscriberIdentity getSubscriberIdentity();

    RequestedInfo getRequestedInfo();

    ISDNAddressString getGsmSCFAddress();

    MAPExtensionContainer getExtensionContainer();
}
