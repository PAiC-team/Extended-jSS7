package org.restcomm.protocols.ss7.map.api.errors;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * ExtensibleCallBarredParam ::= SEQUENCE { callBarringCause CallBarringCause OPTIONAL, extensionContainer ExtensionContainer
 * OPTIONAL, ... , unauthorisedMessageOriginator [1] NULL OPTIONAL }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtensibleCallBarredParam extends Serializable {

    CallBarringCause getCallBarringCause();

    MAPExtensionContainer getExtensionContainer();

    Boolean getUnauthorisedMessageOriginator();

}
