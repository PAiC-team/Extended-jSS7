package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

/**
*
<code>
  oMidCallSpecificInfo [6] SEQUENCE {
    connectTime  [0] Integer4 OPTIONAL,
    oMidCallInfo [1] MidCallInfo {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface OMidCallSpecificInfo extends Serializable {

    Long getConnectTime();

    MidCallInfo getOMidCallInfo();

}
