
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
ModificationRequestFor-CSG ::= SEQUENCE {
  modifyNotificationToCSE     [0] ModificationInstruction OPTIONAL,
  extensionContainer          [1] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForCSG extends Serializable {

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
