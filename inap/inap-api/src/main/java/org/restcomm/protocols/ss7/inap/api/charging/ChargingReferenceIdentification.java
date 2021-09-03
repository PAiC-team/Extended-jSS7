package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
ChargingReferenceIdentification ::= SEQUENCE {
  networkIdentification [00] NetworkIdentification,
  referenceID           [01] ReferenceID
}

NetworkIdentification ::= OBJECT IDENTIFIER

ReferenceID ::= INTEGER (0..4294967295)
-- maximum value 232 - 1
</code>
*
* @author sergey vetyutnev
*
*/
public interface ChargingReferenceIdentification extends Serializable {

    long[] getNetworkIdentification();

    long getReferenceID();

}
