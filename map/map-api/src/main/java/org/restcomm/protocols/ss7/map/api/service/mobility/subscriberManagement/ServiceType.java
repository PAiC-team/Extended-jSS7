
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
ServiceType ::= SEQUENCE {
  serviceTypeIdentity   LCSServiceTypeID,
  gmlc-Restriction      [0] GMLC-Restriction OPTIONAL,
  notificationToMSUser  [1] NotificationToMSUser OPTIONAL,
  -- If notificationToMSUser is not received, the default value according to
  -- 3GPP TS 23.271 shall be assumed.
  extensionContainer    [2] ExtensionContainer OPTIONAL,
  ...
}

LCSServiceTypeID ::= INTEGER (0..127)
-- the integer values 0-63 are reserved for Standard LCS service types
-- the integer values 64-127 are reserved for Non Standard LCS service types
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ServiceType extends Serializable {

    int getServiceTypeIdentity();

    GMLCRestriction getGMLCRestriction();

    NotificationToMSUser getNotificationToMSUser();

    MAPExtensionContainer getExtensionContainer();

}
