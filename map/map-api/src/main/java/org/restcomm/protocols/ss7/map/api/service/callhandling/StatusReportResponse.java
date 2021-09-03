
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 StatusReportRes ::= SEQUENCE { extensionContainer [0] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface StatusReportResponse extends CallHandlingMessage {

    MAPExtensionContainer getExtensionContainer();

}
