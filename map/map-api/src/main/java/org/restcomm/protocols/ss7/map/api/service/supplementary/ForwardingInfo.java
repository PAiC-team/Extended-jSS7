
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
ForwardingInfo ::= SEQUENCE {
  ss-Code                 SS-Code OPTIONAL,
  forwardingFeatureList   ForwardingFeatureList,
  ...
}

ForwardingFeatureList ::= SEQUENCE SIZE (1..13) OF ForwardingFeature
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ForwardingInfo extends Serializable {

    SSCode getSsCode();

    ArrayList<ForwardingFeature> getForwardingFeatureList();

}
