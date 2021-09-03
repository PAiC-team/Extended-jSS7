
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;

/**
 *
<code>
MSISDN-BS ::= SEQUENCE {
  msisdn              ISDN-AddressString,
  basicServiceList    [0] BasicServiceList OPTIONAL,
  extensionContainer  [1] ExtensionContainer OPTIONAL,
  ...
}

BasicServiceList ::= SEQUENCE SIZE (1..70) OF Ext-BasicServiceCode
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MSISDNBS extends Serializable {

    ISDNAddressString getMsisdn();

    ArrayList<ExtBasicServiceCode> getBasicServiceList();

    MAPExtensionContainer getExtensionContainer();

}
