
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 LSAData ::= SEQUENCE { lsaIdentity [0] LSAIdentity, lsaAttributes [1] LSAAttributes, lsaActiveModeIndicator [2] NULL
 * OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LSAData extends Serializable {

    LSAIdentity getLSAIdentity();

    LSAAttributes getLSAAttributes();

    boolean getLsaActiveModeIndicator();

    MAPExtensionContainer getExtensionContainer();

}
