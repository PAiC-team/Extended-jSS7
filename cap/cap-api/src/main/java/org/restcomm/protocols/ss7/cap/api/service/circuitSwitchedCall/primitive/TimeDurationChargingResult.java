
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.AChChargingAddress;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;

/**
 *
<code>
timeDurationChargingResult [0] SEQUENCE {
  partyToCharge       [0] ReceivingSideID,
  timeInformation     [1] TimeInformation,
  legActive           [2] BOOLEAN DEFAULT TRUE,
  callLegReleasedAtTcpExpiry   [3] NULL OPTIONAL,
  extensions          [4] Extensions {bound} OPTIONAL,
  aChChargingAddress  [5] AChChargingAddress {bound} DEFAULT legID:receivingSideID:leg1,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TimeDurationChargingResult extends Serializable {

    ReceivingSideID getPartyToCharge();

    TimeInformation getTimeInformation();

    boolean getLegActive();

    boolean getCallLegReleasedAtTcpExpiry();

    CAPExtensions getExtensions();

    AChChargingAddress getAChChargingAddress();

}