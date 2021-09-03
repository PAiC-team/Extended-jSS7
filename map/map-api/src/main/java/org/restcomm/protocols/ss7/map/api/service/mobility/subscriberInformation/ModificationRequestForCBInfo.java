
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.Password;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
<code>
ModificationRequestFor-CB-Info ::= SEQUENCE {
  ss-Code             [0] SS-Code,
  basicService        [1] Ext-BasicServiceCode OPTIONAL,
  ss-Status           [2] Ext-SS-Status OPTIONAL,
  password            [3] Password OPTIONAL,
  wrongPasswordAttemptsCounter   [4] WrongPasswordAttemptsCounter OPTIONAL,
  modifyNotificationToCSE        [5] ModificationInstruction OPTIONAL,
  extensionContainer             [6] ExtensionContainer OPTIONAL,
  ...
}

WrongPasswordAttemptsCounter ::= INTEGER (0..4)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForCBInfo extends Serializable {

    SSCode getSsCode();

    ExtBasicServiceCode getBasicService();

    ExtSSStatus getSsStatus();

    Password getPassword();

    Integer getWrongPasswordAttemptsCounter();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
