
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
<code>
AChChargingAddress {PARAMETERS-BOUND : bound} ::= CHOICE {
  legID          [2] LegID,
  srfConnection  [50] CallSegmentID {bound}
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AChChargingAddress extends Serializable {

    LegID getLegID();

    int getSrfConnection();

}
