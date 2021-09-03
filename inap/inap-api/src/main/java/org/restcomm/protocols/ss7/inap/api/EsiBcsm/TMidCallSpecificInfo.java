package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

/**
*
<code>
  tMidCallSpecificInfo [11] SEQUENCE {
    connectTime [0] Integer4 OPTIONAL,
    tMidCallInfo [1] MidCallInfo {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface TMidCallSpecificInfo extends Serializable {

    Long getConnectTime();

    MidCallInfo getTMidCallInfo();

}
