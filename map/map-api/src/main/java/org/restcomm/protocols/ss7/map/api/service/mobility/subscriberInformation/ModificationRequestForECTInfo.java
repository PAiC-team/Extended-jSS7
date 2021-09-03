
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;

/**
 *
<code>
ModificationRequestFor-ECT-Info ::= SEQUENCE {
  ss-Status                [0] Ext-SS-Status OPTIONAL,
  modifyNotificationToCSE  [1] ModificationInstruction OPTIONAL,
  extensionContainer       [2] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForECTInfo extends Serializable {

    ExtSSStatus getSsStatus();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
