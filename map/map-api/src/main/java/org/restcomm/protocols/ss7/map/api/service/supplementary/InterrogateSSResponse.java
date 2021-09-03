
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
InterrogateSS-Res ::= CHOICE {
  ss-Status               [0] SS-Status,
  basicServiceGroupList   [2] BasicServiceGroupList,
  forwardingFeatureList   [3] ForwardingFeatureList,
  genericServiceInfo      [4] GenericServiceInfo
}

BasicServiceGroupList ::= SEQUENCE SIZE (1..13) OF BasicServiceCode

ForwardingFeatureList ::= SEQUENCE SIZE (1..13) OF ForwardingFeature
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InterrogateSSResponse extends SupplementaryMessage {

    SSStatus getSsStatus();

    ArrayList<BasicServiceCode> getBasicServiceGroupList();

    ArrayList<ForwardingFeature> getForwardingFeatureList();

    GenericServiceInfo getGenericServiceInfo();

}
