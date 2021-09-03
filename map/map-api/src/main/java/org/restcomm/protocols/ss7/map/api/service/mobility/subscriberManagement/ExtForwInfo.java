
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
 Ext-ForwInfo ::= SEQUENCE { ss-Code SS-Code, forwardingFeatureList Ext-ForwFeatureList, extensionContainer [0]
 * ExtensionContainer OPTIONAL, ...}
 *
 * Ext-ForwFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-ForwFeature
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtForwInfo extends Serializable {

    SSCode getSsCode();

    ArrayList<ExtForwFeature> getForwardingFeatureList();

    MAPExtensionContainer getExtensionContainer();

}
