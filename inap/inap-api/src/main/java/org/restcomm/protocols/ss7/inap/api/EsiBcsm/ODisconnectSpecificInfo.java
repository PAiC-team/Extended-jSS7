package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CauseInap;

/**
*
<code>
  oDisconnectSpecificInfo [7] SEQUENCE {
    releaseCause [0] Cause {bound} OPTIONAL,
    connectTime [1] Integer4 OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface ODisconnectSpecificInfo extends Serializable {

    CauseInap getReleaseCause();

    Long getConnectTime();

}
