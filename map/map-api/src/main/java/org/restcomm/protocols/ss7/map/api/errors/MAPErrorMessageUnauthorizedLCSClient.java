package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * unauthorizedLCSClient ERROR ::= { PARAMETER UnauthorizedLCSClient-Param -- optional CODE local:53 }
 *
 * UnauthorizedLCSClient-Param ::= SEQUENCE { unauthorizedLCSClient-Diagnostic [0] UnauthorizedLCSClient-Diagnostic OPTIONAL,
 * extensionContainer [1] ExtensionContainer OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageUnauthorizedLCSClient extends MAPErrorMessage {

    UnauthorizedLCSClientDiagnostic getUnauthorizedLCSClientDiagnostic();

    MAPExtensionContainer getExtensionContainer();

    void setUnauthorizedLCSClientDiagnostic(UnauthorizedLCSClientDiagnostic unauthorizedLCSClientDiagnostic);

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

}
