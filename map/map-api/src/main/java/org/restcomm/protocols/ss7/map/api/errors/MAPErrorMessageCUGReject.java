
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 cug-Reject ERROR ::= { PARAMETER CUG-RejectParam -- optional CODE local:15 }
 *
 * CUG-RejectParam ::= SEQUENCE { cug-RejectCause CUG-RejectCause OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageCUGReject extends MAPErrorMessage {

    CUGRejectCause getCUGRejectCause();

    MAPExtensionContainer getExtensionContainer();

    void setCUGRejectCause(CUGRejectCause val);

    void setExtensionContainer(MAPExtensionContainer val);

}
