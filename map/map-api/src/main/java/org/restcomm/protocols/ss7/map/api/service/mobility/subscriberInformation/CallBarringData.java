
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtCallBarringFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.Password;

/**
 *
<code>
CallBarringData ::= SEQUENCE {
  callBarringFeatureList        Ext-CallBarFeatureList,
  password                      Password OPTIONAL,
  wrongPasswordAttemptsCounter  WrongPasswordAttemptsCounter OPTIONAL,
  notificationToCSE             NULL OPTIONAL,
  extensionContainer            ExtensionContainer OPTIONAL,
  ...
}

Ext-CallBarFeatureList ::= SEQUENCE SIZE (1..32) OF Ext-CallBarringFeature

WrongPasswordAttemptsCounter ::= INTEGER (0..4)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallBarringData extends Serializable {

    ArrayList<ExtCallBarringFeature> getCallBarringFeatureList();

    Password getPassword();

    Integer getWrongPasswordAttemptsCounter();

    boolean getNotificationToCSE();

    MAPExtensionContainer getExtensionContainer();

}
