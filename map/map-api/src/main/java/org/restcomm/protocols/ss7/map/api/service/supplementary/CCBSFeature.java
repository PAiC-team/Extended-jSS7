
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
CCBS-Feature ::= SEQUENCE {
  ccbs-Index                [0] CCBS-Index OPTIONAL,
  b-subscriberNumber        [1] ISDN-AddressString OPTIONAL,
  b-subscriberSubaddress    [2] ISDN-SubaddressString OPTIONAL,
  basicServiceGroup         [3] BasicServiceCode OPTIONAL,
  ...
}

CCBS-Index ::= INTEGER (1..5)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CCBSFeature extends Serializable {

    Integer getCcbsIndex();

    ISDNAddressString getBSubscriberNumber();

    ISDNAddressString getBSubscriberSubaddress();

    BasicServiceCode getBasicServiceCode();

}
