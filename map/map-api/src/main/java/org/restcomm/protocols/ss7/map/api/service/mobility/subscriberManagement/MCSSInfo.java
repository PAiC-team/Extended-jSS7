
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
<code>
MC-SS-Info ::= SEQUENCE {
  ss-Code             [0] SS-Code,
  ss-Status           [1] Ext-SS-Status,
  nbrSB               [2] MaxMC-Bearers,
  nbrUser             [3] MC-Bearers,
  extensionContainer  [4] ExtensionContainer OPTIONAL,
  ...
}

MaxMC-Bearers ::= INTEGER (2..7)

MC-Bearers ::= INTEGER (1..7)
</code>
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MCSSInfo extends Serializable {

    SSCode getSSCode();

    ExtSSStatus getSSStatus();

    int getNbrSB();

    int getNbrUser();

    MAPExtensionContainer getExtensionContainer();

}
