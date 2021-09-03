
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 NoteMsPresentForGprsRes ::= SEQUENCE { extensionContainer [0] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NoteMsPresentForGprsResponse extends PdpContextActivationMessage {

    MAPExtensionContainer getExtensionContainer();

}
