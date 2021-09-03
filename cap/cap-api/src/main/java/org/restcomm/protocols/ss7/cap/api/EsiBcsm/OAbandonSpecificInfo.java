
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

/**
 *
<code>
oAbandonSpecificInfo [21] SEQUENCE {
  routeNotPermitted [50] NULL OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OAbandonSpecificInfo extends Serializable {

    boolean getRouteNotPermitted();

}
