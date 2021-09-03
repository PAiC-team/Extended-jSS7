
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;

/**
 *
<code>
ModificationRequestFor-ODB-data ::= SEQUENCE {
  odb-data                  [0] ODB-Data OPTIONAL,
  modifyNotificationToCSE   [1] ModificationInstruction OPTIONAL,
  extensionContainer        [2] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForODBdata extends Serializable {

    ODBData getOdbData();

    ModificationInstruction getModifyNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
