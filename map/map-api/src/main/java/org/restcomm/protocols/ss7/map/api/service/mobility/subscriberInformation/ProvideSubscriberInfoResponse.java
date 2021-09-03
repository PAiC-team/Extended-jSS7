
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 * <p>
 * The HLR may use MAP PSI to obtain subscriber data from VLR; the subscriber data that may be obtained with MAP PSI includes
 * location information and subscriber state. In CAMEL phase 3 and CAMEL phase 4, additional information may be obtained from
 * the VLR
 * </p>
 * <p>
 * Within the context of CAMEL, the HLR may use MAP PSI for the following procedures: (1) MT call handling (2) ATI
 * </p>
 * <p>
 * The HLR may also use MAP PSI for optimal routing (OR); refer to GSM TS 03.79 [39] for a description of OR
 * </p>
 *
<code>
ProvideSubscriberInfoRes ::= SEQUENCE {
  subscriberInfo       SubscriberInfo,
  extensionContainer   ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProvideSubscriberInfoResponse extends MobilityMessage {

    SubscriberInfo getSubscriberInfo();

    MAPExtensionContainer getExtensionContainer();

}
