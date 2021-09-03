
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 AllowedUMTS-Algorithms ::= SEQUENCE { integrityProtectionAlgorithms [0] PermittedIntegrityProtectionAlgorithms OPTIONAL,
 * encryptionAlgorithms [1] PermittedEncryptionAlgorithms OPTIONAL, extensionContainer [2] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AllowedUMTSAlgorithms extends Serializable {

    PermittedIntegrityProtectionAlgorithms getIntegrityProtectionAlgorithms();

    PermittedEncryptionAlgorithms getEncryptionAlgorithms();

    MAPExtensionContainer getExtensionContainer();

}
