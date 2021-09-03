
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
IST-CommandRes ::= SEQUENCE{
  extensionContainer ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IstCommandResponse extends CallHandlingMessage {

    MAPExtensionContainer getExtensionContainer();

}
