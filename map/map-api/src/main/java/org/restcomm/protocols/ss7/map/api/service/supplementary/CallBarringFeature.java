
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
CallBarringFeature ::= SEQUENCE {
  basicService   BasicServiceCode OPTIONAL,
  ss-Status      [4] SS-Status OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallBarringFeature extends Serializable {

    BasicServiceCode getBasicService();

    SSStatus getSsStatus();

}
