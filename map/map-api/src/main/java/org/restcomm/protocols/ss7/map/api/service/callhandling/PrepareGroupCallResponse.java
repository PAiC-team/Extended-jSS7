
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 PrepareGroupCallRes ::= SEQUENCE { groupCallNumber ISDN-AddressString, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PrepareGroupCallResponse extends CallHandlingMessage {

     ISDNAddressString getGroupCallNumber();

     MAPExtensionContainer getExtensionContainer();

}
