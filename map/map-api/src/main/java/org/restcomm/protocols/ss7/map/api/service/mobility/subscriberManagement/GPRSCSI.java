
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
GPRS-CSI ::= SEQUENCE {
  gprs-CamelTDPDataList      [0] GPRS-CamelTDPDataList OPTIONAL,
  camelCapabilityHandling    [1] CamelCapabilityHandling OPTIONAL,
  extensionContainer         [2] ExtensionContainer OPTIONAL,
  notificationToCSE          [3] NULL OPTIONAL,
  csi-Active                 [4] NULL OPTIONAL,
  ...
}
-- notificationToCSE and csi-Active shall not be present when GPRS-CSI is sent to SGSN.
-- They may only be included in ATSI/ATM ack/NSDC message.
-- GPRS-CamelTDPData and camelCapabilityHandling shall be present in
-- the GPRS-CSI sequence.
-- If GPRS-CSI is segmented, gprs-CamelTDPDataList and camelCapabilityHandling shall be
-- present in the first segment

GPRS-CamelTDPDataList ::= SEQUENCE SIZE (1..10) OF GPRS-CamelTDPData
-- GPRS-CamelTDPDataList shall not contain more than one instance of
-- GPRS-CamelTDPData containing the same value for gprs-TriggerDetectionPoint.

CamelCapabilityHandling ::= INTEGER(1..16)
-- value 1 = CAMEL phase 1,
-- value 2 = CAMEL phase 2,
-- value 3 = CAMEL Phase 3,
-- value 4 = CAMEL phase 4:
-- reception of values greater than 4 shall be treated as CAMEL phase 4.
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSCSI extends Serializable {

    ArrayList<GPRSCamelTDPData> getGPRSCamelTDPDataList();

    Integer getCamelCapabilityHandling();

    MAPExtensionContainer getExtensionContainer();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
