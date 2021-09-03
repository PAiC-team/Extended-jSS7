
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.Time;

/**
 *
<code>
CSG-SubscriptionData ::= SEQUENCE {
  csg-Id                CSG-Id,
  expirationDate        Time OPTIONAL,
  extensionContainer    ExtensionContainer OPTIONAL,
  ...,
  lipa-AllowedAPNList   [0] LIPA-AllowedAPNList OPTIONAL
}

LIPA-AllowedAPNList ::= SEQUENCE SIZE (1..50) OF APN

</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CSGSubscriptionData extends Serializable {

    CSGId getCsgId();

    Time getExpirationDate();

    MAPExtensionContainer getExtensionContainer();

    ArrayList<APN> getLipaAllowedAPNList();

}
