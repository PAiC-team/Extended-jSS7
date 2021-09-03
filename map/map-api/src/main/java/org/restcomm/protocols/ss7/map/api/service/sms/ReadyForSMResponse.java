
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MAP V3:

ReadyForSM-Res ::= SEQUENCE {
  extensionContainer ExtensionContainer OPTIONAL,
  ...
}
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReadyForSMResponse extends SmsMessage {

    MAPExtensionContainer getExtensionContainer();

}
