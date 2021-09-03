
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
MAP V3: AuthenticationFailureReportRes ::= SEQUENCE {
  extensionContainer ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AuthenticationFailureReportResponse extends MobilityMessage {

    MAPExtensionContainer getExtensionContainer();

}
