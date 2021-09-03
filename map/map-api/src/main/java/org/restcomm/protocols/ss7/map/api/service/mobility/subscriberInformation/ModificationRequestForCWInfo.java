
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;

/**
 *
<code>
ModificationRequestFor-CW-Info ::= SEQUENCE {
  basicService             [0] Ext-BasicServiceCode OPTIONAL,
  ss-Status                [1] Ext-SS-Status OPTIONAL,
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
public interface ModificationRequestForCWInfo extends Serializable {

    ExtBasicServiceCode getBasicService();

    ExtSSStatus getSsStatus();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
