
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * CCBS-Indicators ::= SEQUENCE { ccbs-Possible [0] NULL OPTIONAL, keepCCBS-CallIndicator [1] NULL OPTIONAL, extensionContainer
 * [2] ExtensionContainer OPTIONAL, ...}
 *
 * @author cristian veliscu
 *
 */
public interface CCBSIndicators {

    boolean getCCBSPossible();

    boolean getKeepCCBSCallIndicator();

    MAPExtensionContainer getMAPExtensionContainer();
}