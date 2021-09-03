package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CalledPartyNumberInap;

/**
*
<code>
  collectedInfoSpecificInfo [0] SEQUENCE {
    calledPartynumber [0] CalledPartyNumber {bound},
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface CollectedInfoSpecificInfo extends Serializable {

    CalledPartyNumberInap getCalledPartyNumber();

}
