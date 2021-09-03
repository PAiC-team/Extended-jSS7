
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 RemoteUserFreeRes ::= SEQUENCE{ ruf-Outcome [0] RUF-Outcome, extensionContainer [1] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RemoteUserFreeResponse extends CallHandlingMessage {

     RUFOutcome getRufOutcome();

     MAPExtensionContainer getExtensionContainer();

}
