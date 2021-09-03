
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
<code>
routeSelectFailureSpecificInfo [2] SEQUENCE {
  failureCause [0] Cause {bound} OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RouteSelectFailureSpecificInfo extends Serializable {

    CauseCap getFailureCause();

}