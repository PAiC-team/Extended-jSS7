
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.FTNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNSubaddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingOptions;

/**
 * ForwardingData ::= SEQUENCE { forwardedToNumber [5] ISDN-AddressString OPTIONAL, forwardedToSubaddress [4]
 * ISDN-SubaddressString OPTIONAL, forwardingOptions [6] ForwardingOptions OPTIONAL, extensionContainer [7] ExtensionContainer
 * OPTIONAL, ..., longForwardedToNumber [8] FTN-AddressString OPTIONAL}
 *
 * @author cristian veliscu
 *
 */
public interface ForwardingData extends Serializable {

     ISDNAddressString getForwardedToNumber();

     ISDNSubaddressString getForwardedToSubaddress(); // TODO: ISDNSubaddressString

     ForwardingOptions getForwardingOptions();

     MAPExtensionContainer getExtensionContainer();

     FTNAddressString getLongForwardedToNumber();
}