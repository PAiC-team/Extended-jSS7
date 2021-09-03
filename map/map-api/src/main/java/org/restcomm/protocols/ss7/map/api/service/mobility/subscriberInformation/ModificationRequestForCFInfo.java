
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNSubaddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
<code>
ModificationRequestFor-CF-Info ::= SEQUENCE {
  ss-Code                  [0] SS-Code,
  basicService             [1] Ext-BasicServiceCode OPTIONAL,
  ss-Status                [2] Ext-SS-Status OPTIONAL,
  forwardedToNumber        [3] AddressString OPTIONAL,
  forwardedToSubaddress    [4] ISDN-SubaddressString OPTIONAL,
  noReplyConditionTime     [5] Ext-NoRepCondTime OPTIONAL,
  modifyNotificationToCSE  [6] ModificationInstruction OPTIONAL,
  extensionContainer       [7] ExtensionContainer OPTIONAL,
  ...
}

Ext-NoRepCondTime ::= INTEGER (1..100)
-- Only values 5-30 are used.
-- Values in the ranges 1-4 and 31-100 are reserved for future use
-- If received:
-- values 1-4 shall be mapped on to value 5
-- values 31-100 shall be mapped on to value 30
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForCFInfo extends Serializable {

    SSCode getSsCode();

    ExtBasicServiceCode getBasicService();

    ExtSSStatus getSsStatus();

    AddressString getForwardedToNumber();

    ISDNSubaddressString getForwardedToSubaddress();

    Integer getNoReplyConditionTime();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
