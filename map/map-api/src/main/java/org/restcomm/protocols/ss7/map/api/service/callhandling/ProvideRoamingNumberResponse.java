
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3: ProvideRoamingNumberRes ::= SEQUENCE { roamingNumber ISDN-AddressString, extensionContainer ExtensionContainer
 * OPTIONAL, ..., releaseResourcesSupported NULL OPTIONAL, vmsc-Address ISDN-AddressString OPTIONAL }
 *
 * MAP V2: RESULT roamingNumberISDN-AddressString
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProvideRoamingNumberResponse extends CallHandlingMessage {

     ISDNAddressString getRoamingNumber();

     MAPExtensionContainer getExtensionContainer();

     boolean getReleaseResourcesSupported();

     ISDNAddressString getVmscAddress();

     long getMapProtocolVersion();

}
