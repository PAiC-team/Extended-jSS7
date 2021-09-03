
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SS-InvocationNotificationRes ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSInvocationNotificationResponse extends SupplementaryMessage {

    MAPExtensionContainer getExtensionContainer();

}