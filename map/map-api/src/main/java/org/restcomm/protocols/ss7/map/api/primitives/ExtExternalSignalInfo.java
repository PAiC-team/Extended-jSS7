
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * <pre>
 *  Ext-ExternalSignalInfo ::= SEQUENCE {
 *  protocolId Ext-ProtocolId,
 *  signalInfo SignalInfo,
 *  extensionContainer ExtensionContainer OPTIONAL,
 * ...}
 *
 * SignalInfo ::= OCTET STRING (SIZE (1..200))
 * </pre>
 *
 *
 * @author cristian veliscu
 *
 */
public interface ExtExternalSignalInfo extends Serializable {

    ExtProtocolId getExtProtocolId();

    SignalInfo getSignalInfo();

    MAPExtensionContainer getExtensionContainer();
}