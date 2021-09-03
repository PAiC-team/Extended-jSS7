package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * positionMethodFailure ERROR ::= { PARAMETER PositionMethodFailure-Param -- optional CODE local:54 }
 *
 *
 * PositionMethodFailure-Param ::= SEQUENCE { positionMethodFailure-Diagnostic [0] PositionMethodFailure-Diagnostic OPTIONAL,
 * extensionContainer [1] ExtensionContainer OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessagePositionMethodFailure extends MAPErrorMessage {

    PositionMethodFailureDiagnostic getPositionMethodFailureDiagnostic();

    MAPExtensionContainer getExtensionContainer();

    void setPositionMethodFailureDiagnostic(PositionMethodFailureDiagnostic positionMethodFailureDiagnostic);

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

}
