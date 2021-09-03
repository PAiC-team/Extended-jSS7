
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
SS-ForBS-Code ::= SEQUENCE {
  ss-Code             SS-Code,
  basicService        BasicServiceCode OPTIONAL,
  ...,
  longFTN-Supported   [4] NULL OPTIONAL
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSForBSCode extends Serializable {

    SSCode getSsCode();

    BasicServiceCode getBasicService(); // -> BasicServiceCode -> subscriber management !!!!!

    boolean getLongFtnSupported();

}
