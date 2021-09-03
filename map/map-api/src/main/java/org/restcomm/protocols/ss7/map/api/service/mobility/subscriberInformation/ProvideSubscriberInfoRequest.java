
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
MAP V3:

provideSubscriberInfo OPERATION ::= {
  --Timer m
  ARGUMENT ProvideSubscriberInfoArg
  RESULT ProvideSubscriberInfoRes
  ERRORS { dataMissing | unexpectedDataValue}
  CODE local:70
}

ProvideSubscriberInfoArg ::= SEQUENCE {
  imsi                [0] IMSI,
  lmsi                [1] LMSI OPTIONAL,
  requestedInfo       [2] RequestedInfo,
  extensionContainer  [3] ExtensionContainer OPTIONAL,
  ...,
  callPriority        [4] EMLPP-Priority OPTIONAL
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProvideSubscriberInfoRequest extends MobilityMessage {

    IMSI getImsi();

    LMSI getLmsi();

    RequestedInfo getRequestedInfo();

    MAPExtensionContainer getExtensionContainer();

    EMLPPPriority getCallPriority();

}
