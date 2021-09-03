
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
 NAEA-PreferredCI ::= SEQUENCE { naea-PreferredCIC [0] NAEA-CIC, extensionContainer [1] ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NAEAPreferredCI extends Serializable {

    NAEACIC getNaeaPreferredCIC();

    MAPExtensionContainer getExtensionContainer();

}
