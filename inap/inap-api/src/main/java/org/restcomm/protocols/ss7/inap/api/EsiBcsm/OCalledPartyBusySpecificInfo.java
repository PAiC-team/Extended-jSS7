package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CauseInap;

/**
*
<code>
  oCalledPartyBusySpecificInfo [3] SEQUENCE {
    busyCause [0] Cause {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface OCalledPartyBusySpecificInfo extends Serializable {

    CauseInap getBusyCause();

}
