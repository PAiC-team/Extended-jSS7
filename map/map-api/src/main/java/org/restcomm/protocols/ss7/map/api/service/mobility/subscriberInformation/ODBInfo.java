
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;

/**
 *
<code>
ODB-Info ::= SEQUENCE {
  odb-Data            ODB-Data,
  notificationToCSE   NULL OPTIONAL,
  extensionContainer  ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ODBInfo extends Serializable {

    ODBData getOdbData();

    boolean getNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
