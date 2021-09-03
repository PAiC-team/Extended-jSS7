
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
<code>
Ext-ForwardingInfoFor-CSE ::= SEQUENCE {
  ss-Code                [0] SS-Code,
  forwardingFeatureList  [1] Ext-ForwFeatureList,
  notificationToCSE      [2] NULL OPTIONAL,
  extensionContainer     [3] ExtensionContainer OPTIONAL,
  ...
}

Ext-ForwFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-ForwFeature
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtForwardingInfoForCSE extends Serializable {

    SSCode getSsCode();

    ArrayList<ExtForwFeature> getForwardingFeatureList();

    boolean getNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
