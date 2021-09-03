
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 CUG-Feature ::= SEQUENCE { basicService Ext-BasicServiceCode OPTIONAL, preferentialCUG-Indicator CUG-Index OPTIONAL,
 * interCUG-Restrictions InterCUG-Restrictions, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 * CUG-Index ::= INTEGER (0..32767) -- The internal structure is defined in ETS 300 138.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CUGFeature extends Serializable {

    ExtBasicServiceCode getBasicService();

    Integer getPreferentialCugIndicator();

    InterCUGRestrictions getInterCugRestrictions();

    MAPExtensionContainer getExtensionContainer();

}
