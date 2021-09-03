
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 Ext-CallBarringFeature ::= SEQUENCE { basicService Ext-BasicServiceCode OPTIONAL, ss-Status [4] Ext-SS-Status,
 * extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtCallBarringFeature extends Serializable {

    ExtBasicServiceCode getBasicService();

    ExtSSStatus getSsStatus();

    MAPExtensionContainer getExtensionContainer();

}
