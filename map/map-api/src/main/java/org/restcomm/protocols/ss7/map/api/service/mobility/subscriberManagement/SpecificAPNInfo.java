
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SpecificAPNInfo ::= SEQUENCE { apn [0] APN, pdn-gw-Identity [1] PDN-GW-Identity, extensionContainer [2] ExtensionContainer
 * OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SpecificAPNInfo extends Serializable {

    APN getAPN();

    PDNGWIdentity getPdnGwIdentity();

    MAPExtensionContainer getExtensionContainer();

}
