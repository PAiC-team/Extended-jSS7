
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;

/**
 *
<code>
fCIBCCCAMELsequence1 [0] SEQUENCE {
  freeFormatData       [0] OCTET STRING (SIZE( bound.&minFCIBillingChargingDataLength .. bound.&maxFCIBillingChargingDataLength)),
  partyToCharge        [1] SendingSideID DEFAULT sendingSideID: leg1,
  appendFreeFormatData [2] AppendFreeFormatData DEFAULT overwrite,
  ...
}

minFCIBillingChargingDataLength ::= 1
maxFCIBillingChargingDataLength ::= 160
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FCIBCCCAMELsequence1 extends Serializable {

    FreeFormatData getFreeFormatData();

    SendingSideID getPartyToCharge();

    AppendFreeFormatData getAppendFreeFormatData();

}
