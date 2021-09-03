
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.OverrideCategory;

/**
 *
<code>
ModificationRequestFor-CLIP-Info ::= SEQUENCE {
  ss-Status                [0] Ext-SS-Status OPTIONAL,
  overrideCategory         [1] OverrideCategory OPTIONAL,
  modifyNotificationToCSE  [2] ModificationInstruction OPTIONAL,
  extensionContainer       [3] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForCLIPInfo extends Serializable {

    ExtSSStatus getSsStatus();

    OverrideCategory getOverrideCategory();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
