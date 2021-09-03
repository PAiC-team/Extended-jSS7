
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;

/**
 *
<code>
collectedInfoSpecificInfo [2] SEQUENCE {
  calledPartyNumber [0] CalledPartyNumber OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CollectedInfoSpecificInfo extends Serializable {

    CalledPartyNumberCap getCalledPartyNumber();

}
