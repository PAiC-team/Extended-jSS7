
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
<code>
MAP V3: SendAuthenticationInfoRes ::= [3] SEQUENCE {
  authenticationSetList AuthenticationSetList OPTIONAL,
  extensionContainer ExtensionContainer OPTIONAL,
  ...,
  eps-AuthenticationSetList [2] EPS-AuthenticationSetList OPTIONAL
}

MAP V2: SendAuthenticationInfoRes ::= AuthenticationSetList
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SendAuthenticationInfoResponse extends MobilityMessage {

    AuthenticationSetList getAuthenticationSetList();

    MAPExtensionContainer getExtensionContainer();

    EpsAuthenticationSetList getEpsAuthenticationSetList();

    long getMapProtocolVersion();

}
