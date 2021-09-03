package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
CallingPartyBusinessGroupID {PARAMETERS-BOUND : bound} ::= OCTET STRING(SIZE(
bound.&minCallingPartyBusinessGroupIDLength .. bound.&maxCallingPartyBusinessGroupIDLength))
-- Indicates the business group of the calling party. The value of this octet string is network
-- operator specific.

minCallingPartyBusinessGroupIDLength=1
maxCallingPartyBusinessGroupIDLength=5
</code>
*
* @author sergey vetyutnev
*
*/
public interface CallingPartyBusinessGroupID extends Serializable {

    byte[] getData();

}
