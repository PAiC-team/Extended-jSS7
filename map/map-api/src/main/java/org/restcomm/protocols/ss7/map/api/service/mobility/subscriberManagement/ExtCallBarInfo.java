
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
 Ext-CallBarInfo ::= SEQUENCE { ss-Code SS-Code, callBarringFeatureList Ext-CallBarFeatureList, extensionContainer
 * ExtensionContainer OPTIONAL, ...}
 *
 * Ext-CallBarFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-CallBarringFeature
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtCallBarInfo extends Serializable {

    SSCode getSsCode();

    ArrayList<ExtCallBarringFeature> getCallBarringFeatureList();

    MAPExtensionContainer getExtensionContainer();

}
