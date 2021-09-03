
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.OverrideCategory;

/**
 *
<code>
ClipData ::= SEQUENCE {
  ss-Status          [1] Ext-SS-Status,
  overrideCategory   [2] OverrideCategory,
  notificationToCSE  [3] NULL OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ClipData extends Serializable {

    ExtSSStatus getSsStatus();

    OverrideCategory getOverrideCategory();

    boolean getNotificationToCSE();

}
