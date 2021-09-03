
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SelectedUMTS-Algorithms ::= SEQUENCE { integrityProtectionAlgorithm [0] ChosenIntegrityProtectionAlgorithm OPTIONAL,
 * encryptionAlgorithm [1] ChosenEncryptionAlgorithm OPTIONAL, extensionContainer [2] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SelectedUMTSAlgorithms extends Serializable {

    ChosenIntegrityProtectionAlgorithm getIntegrityProtectionAlgorithm();

    ChosenEncryptionAlgorithm getEncryptionAlgorithm();

    MAPExtensionContainer getExtensionContainer();

}
