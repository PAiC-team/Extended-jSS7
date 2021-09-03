
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * releaseResources OPERATION::= { --Timer m ARGUMENT ReleaseResourcesArg RESULT ReleaseResourcesRes -- optional ERRORS {
 * unexpectedDataValue | systemFailure } CODE local:20 }
 *
 * ReleaseResourcesArg ::= SEQUENCE{ msrn ISDN-AddressString, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReleaseResourcesRequest extends CallHandlingMessage {

     ISDNAddressString getMSRN();

     MAPExtensionContainer getExtensionContainer();

}
