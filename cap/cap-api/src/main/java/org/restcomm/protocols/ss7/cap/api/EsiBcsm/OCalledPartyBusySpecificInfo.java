
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
<code>
oCalledPartyBusySpecificInfo [3] SEQUENCE {
  busyCause [0] Cause {bound} OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OCalledPartyBusySpecificInfo extends Serializable {

    CauseCap getBusyCause();

}
