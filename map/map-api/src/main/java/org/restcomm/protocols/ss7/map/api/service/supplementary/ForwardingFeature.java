
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.FTNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
ForwardingFeature ::= SEQUENCE {
  basicService             BasicServiceCode OPTIONAL,
  ss-Status                [4] SS-Status OPTIONAL,
  forwardedToNumber        [5] ISDN-AddressString OPTIONAL,
  forwardedToSubaddress    [8] ISDN-SubaddressString OPTIONAL,
  forwardingOptions        [6] ForwardingOptions OPTIONAL,
  noReplyConditionTime     [7] NoReplyConditionTime OPTIONAL,
  ...,
  longForwardedToNumber    [9] FTN-AddressString OPTIONAL
}

NoReplyConditionTime ::= INTEGER (5..30)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ForwardingFeature extends Serializable {

    BasicServiceCode getBasicService();

    SSStatus getSsStatus();

    ISDNAddressString getForwardedToNumber();

    ISDNAddressString getForwardedToSubaddress();

    ForwardingOptions getForwardingOptions();

    Integer getNoReplyConditionTime();

    FTNAddressString getLongForwardedToNumber();

}
