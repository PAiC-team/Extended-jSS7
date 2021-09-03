
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
<code>
MOLR-Class ::= SEQUENCE {
  ss-Code            SS-Code,
  ss-Status          Ext-SS-Status,
  extensionContainer [0] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MOLRClass extends Serializable {

    SSCode getSsCode();

    ExtSSStatus getSsStatus();

    MAPExtensionContainer getExtensionContainer();

}
