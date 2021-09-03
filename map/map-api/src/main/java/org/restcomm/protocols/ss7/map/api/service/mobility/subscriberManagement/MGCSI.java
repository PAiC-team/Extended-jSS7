
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MG-CSI ::= SEQUENCE {
  mobilityTriggers    MobilityTriggers,
  serviceKey          ServiceKey,
  gsmSCF-Address      [0] ISDN-AddressString,
  extensionContainer  [1] ExtensionContainer OPTIONAL,
  notificationToCSE   [2] NULL OPTIONAL,
  csi-Active          [3] NULL OPTIONAL,
  ...
}
-- notificationToCSE and csi-Active shall not be present when MG-CSI is sent to SGSN.
-- They may only be included in ATSI/ATM ack/NSDC message.

ServiceKey ::= INTEGER (0..2147483647)

MobilityTriggers ::= SEQUENCE SIZE (1..10) OF MM-Code
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MGCSI extends Serializable {

    ArrayList<MMCode> getMobilityTriggers();

    long getServiceKey();

    ISDNAddressString getGsmSCFAddress();

    MAPExtensionContainer getExtensionContainer();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
