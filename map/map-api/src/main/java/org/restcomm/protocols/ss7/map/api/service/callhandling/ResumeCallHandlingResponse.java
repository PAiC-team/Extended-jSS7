
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 ResumeCallHandlingRes ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ResumeCallHandlingResponse extends CallHandlingMessage {

    MAPExtensionContainer getExtensionContainer();

}
