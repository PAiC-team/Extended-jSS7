
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CliRestrictionOption;

/**
 *
</code>
ClirData ::= SEQUENCE {
  ss-Status              [1] Ext-SS-Status,
  cliRestrictionOption   [2] CliRestrictionOption OPTIONAL,
  notificationToCSE      [3] NULL OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ClirData extends Serializable {

    ExtSSStatus getSsStatus();

    CliRestrictionOption getCliRestrictionOption();

    boolean getNotificationToCSE();

}
