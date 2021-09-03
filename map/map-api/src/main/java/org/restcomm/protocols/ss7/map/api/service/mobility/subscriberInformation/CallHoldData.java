
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;

/**
 *
<code>
CallHoldData ::= SEQUENCE {
  ss-Status          [1] Ext-SS-Status,
  notificationToCSE  [2] NULL OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallHoldData extends Serializable {

    ExtSSStatus getSsStatus();

    boolean getNotificationToCSE();

}
