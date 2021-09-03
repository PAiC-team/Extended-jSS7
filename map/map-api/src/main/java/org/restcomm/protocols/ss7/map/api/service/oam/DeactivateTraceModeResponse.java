
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3: DeactivateTraceModeRes ::= SEQUENCE { extensionContainer [0] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DeactivateTraceModeResponse extends OamMessage {

    MAPExtensionContainer getExtensionContainer();

}
