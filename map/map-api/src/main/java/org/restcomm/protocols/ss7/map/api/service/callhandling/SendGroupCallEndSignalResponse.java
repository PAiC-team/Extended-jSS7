
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SendGroupCallEndSignalRes ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendGroupCallEndSignalResponse extends CallHandlingMessage {

     MAPExtensionContainer getExtensionContainer();

}
