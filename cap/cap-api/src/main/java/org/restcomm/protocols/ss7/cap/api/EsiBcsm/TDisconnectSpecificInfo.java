
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
<code>
tDisconnectSpecificInfo [12] SEQUENCE {
  releaseCause [0] Cause {bound} OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TDisconnectSpecificInfo extends Serializable {

    CauseCap getReleaseCause();

}