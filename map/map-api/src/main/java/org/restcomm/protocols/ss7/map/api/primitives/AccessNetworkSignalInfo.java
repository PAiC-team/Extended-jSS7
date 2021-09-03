
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
 AccessNetworkSignalInfo ::= SEQUENCE { accessNetworkProtocolId AccessNetworkProtocolId, signalInfo LongSignalInfo, --
 * Information about the internal structure is given in clause 7.6.9.1
 *
 * extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 * LongSignalInfo ::= OCTET STRING (SIZE (1..maxLongSignalInfoLength))
 *
 * maxLongSignalInfoLength INTEGER ::= 2560 -- This Named Value represents the maximum number of octets which is available -- to
 * carry a single instance of the LongSignalInfo data type using -- White Book SCCP with the maximum number of segments. -- It
 * takes account of the octets used by the lower layers of the protocol, and -- other information elements which may be included
 * in the same component.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AccessNetworkSignalInfo extends Serializable {

    AccessNetworkProtocolId getAccessNetworkProtocolId();

    byte[] getSignalInfo();

    MAPExtensionContainer getExtensionContainer();

}
