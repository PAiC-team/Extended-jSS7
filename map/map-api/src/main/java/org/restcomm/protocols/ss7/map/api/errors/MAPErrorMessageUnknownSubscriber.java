package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * UnknownSubscriberParam ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ..., unknownSubscriberDiagnostic
 * UnknownSubscriberDiagnostic OPTIONAL}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageUnknownSubscriber extends MAPErrorMessage {

    MAPExtensionContainer getExtensionContainer();

    UnknownSubscriberDiagnostic getUnknownSubscriberDiagnostic();

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setUnknownSubscriberDiagnostic(UnknownSubscriberDiagnostic unknownSubscriberDiagnostic);

}
