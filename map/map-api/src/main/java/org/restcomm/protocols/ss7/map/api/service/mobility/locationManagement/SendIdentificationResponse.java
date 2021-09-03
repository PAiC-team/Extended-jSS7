
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CurrentSecurityContext;

/**
<code>
MAP V3: SendIdentificationRes ::= [3] SEQUENCE {
  imsi                    IMSI OPTIONAL,
  -- IMSI shall be present in the first (or only) service response of a dialogue.
  -- If multiple service requests are present in a dialogue then IMSI
  -- shall not be present in any service response other than the first one.
  authenticationSetList AuthenticationSetList OPTIONAL,
  currentSecurityContext  [2] CurrentSecurityContext OPTIONAL,
  extensionContainer      [3] ExtensionContainer OPTIONAL,
  ...
}

MAP V2: SendIdentificationRes ::= SEQUENCE {
  imsi                    IMSI,
  authenticationSetList   AuthenticationSetList OPTIONAL,
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SendIdentificationResponse extends MobilityMessage {

    IMSI getImsi();

    AuthenticationSetList getAuthenticationSetList();

    CurrentSecurityContext getCurrentSecurityContext();

    MAPExtensionContainer getExtensionContainer();

}
