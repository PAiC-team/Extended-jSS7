
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
CallWaitingData ::= SEQUENCE {
  cwFeatureList      [1] Ext-CwFeatureList,
  notificationToCSE  [2] NULL OPTIONAL,
  ...
}

Ext-CwFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-CwFeature
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallWaitingData extends Serializable {

    ArrayList<ExtCwFeature> getCwFeatureList();

    boolean getNotificationToCSE();

}
