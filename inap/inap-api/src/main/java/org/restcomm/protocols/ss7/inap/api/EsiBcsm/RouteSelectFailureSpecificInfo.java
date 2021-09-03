package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CauseInap;

/**
*
<code>
  routeSelectFailureSpecificInfo [2] SEQUENCE {
    failureCause [0] Cause {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface RouteSelectFailureSpecificInfo extends Serializable {

    CauseInap getFailureCause();

}
