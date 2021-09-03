
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientExternalID;

/**
 *
<code>
ExternalClient ::= SEQUENCE {
  clientIdentity         LCSClientExternalID,
  gmlc-Restriction       [0] GMLC-Restriction OPTIONAL,
  notificationToMSUser   [1] NotificationToMSUser OPTIONAL,
  -- If notificationToMSUser is not received, the default value according to
  -- 3GPP TS 23.271 shall be assumed.
  extensionContainer     [2] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExternalClient extends Serializable {

    LCSClientExternalID getClientIdentity();

    GMLCRestriction getGMLCRestriction();

    NotificationToMSUser getNotificationToMSUser();

    MAPExtensionContainer getExtensionContainer();

}
