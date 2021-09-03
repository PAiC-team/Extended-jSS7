
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwFeature;

/**
 *
<code>
CallForwardingData ::= SEQUENCE {
  forwardingFeatureList  Ext-ForwFeatureList,
  notificationToCSE      NULL OPTIONAL,
  extensionContainer     [0] ExtensionContainer OPTIONAL,
  ...
}

Ext-ForwFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-ForwFeature
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallForwardingData extends Serializable {

    ArrayList<ExtForwFeature> getForwardingFeatureList();

    boolean getNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
