package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * LCSClientExternalID ::= SEQUENCE { externalAddress [0] ISDN-AddressString OPTIONAL, extensionContainer [1] ExtensionContainer
 * OPTIONAL, ... }
 *
 * @author amit bhayani
 *
 */
public interface LCSClientExternalID extends Serializable {

    ISDNAddressString getExternalAddress();

    MAPExtensionContainer getExtensionContainer();
}
