
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 ReleaseResourcesRes ::= SEQUENCE{ extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReleaseResourcesResponse extends CallHandlingMessage {

    MAPExtensionContainer getExtensionContainer();

}
