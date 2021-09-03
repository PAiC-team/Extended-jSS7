package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CalledPartyNumberInap;

/**
*
<code>
  analysedInfoSpecificInfo [1] SEQUENCE {
    calledPartynumber [0] CalledPartyNumber {bound},
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface AnalysedInfoSpecificInfo extends Serializable {

    CalledPartyNumberInap getCalledPartyNumber();

}
