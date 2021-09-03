
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CliRestrictionOption;

/**
 *
<code>
ModificationRequestFor-CLIR-Info ::= SEQUENCE {
  ss-Status                [0] Ext-SS-Status OPTIONAL,
  cliRestrictionOption     [1] CliRestrictionOption OPTIONAL,
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
public interface ModificationRequestForCLIRInfo extends Serializable {

    ExtSSStatus getSsStatus();

    CliRestrictionOption getCliRestrictionOption();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
