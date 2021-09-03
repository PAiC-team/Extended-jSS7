package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * CallBarredParam ::= CHOICE { callBarringCause CallBarringCause, -- call BarringCause must not be used in version 3 and higher
 * extensibleCallBarredParam ExtensibleCallBarredParam -- extensibleCallBarredParam must not be used in version <3 }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageCallBarred extends MAPErrorMessage {

    CallBarringCause getCallBarringCause();

    MAPExtensionContainer getExtensionContainer();

    Boolean getUnauthorisedMessageOriginator();

    long getMapProtocolVersion();

    void setCallBarringCause(CallBarringCause callBarringCause);

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setUnauthorisedMessageOriginator(Boolean unauthorisedMessageOriginator);

    void setMapProtocolVersion(long mapProtocolVersion);

}
