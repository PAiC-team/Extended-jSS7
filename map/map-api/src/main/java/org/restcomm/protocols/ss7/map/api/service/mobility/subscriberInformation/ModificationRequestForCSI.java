
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
ModificationRequestFor-CSI ::= SEQUENCE {
  requestedCamel-SubscriptionInfo    [0] RequestedCAMEL-SubscriptionInfo,
  modifyNotificationToCSE            [1] ModificationInstruction OPTIONAL,
  modifyCSI-State                    [2] ModificationInstruction OPTIONAL,
  extensionContainer                 [3] ExtensionContainer OPTIONAL,
  ...,
  additionalRequestedCAMEL-SubscriptionInfo  [4] AdditionalRequestedCAMEL-SubscriptionInfo OPTIONAL
}
-- requestedCamel-SubscriptionInfo shall be discarded if
-- additionalRequestedCAMEL-SubscriptionInfo is received
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForCSI extends Serializable {

    RequestedCAMELSubscriptionInfo getRequestedCamelSubscriptionInfo();

    ModificationInstruction getModifyNotificationToCSE();

    ModificationInstruction getModifyCSIState();

    MAPExtensionContainer getExtensionContainer();

    AdditionalRequestedCAMELSubscriptionInfo getAdditionalRequestedCamelSubscriptionInfo();

}
