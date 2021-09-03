package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * The MAP ReturnError message: with a single parameter - ExtensionContainer
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageExtensionContainer extends MAPErrorMessage {

    Long getErrorCode();

    MAPExtensionContainer getExtensionContainer();

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

}
