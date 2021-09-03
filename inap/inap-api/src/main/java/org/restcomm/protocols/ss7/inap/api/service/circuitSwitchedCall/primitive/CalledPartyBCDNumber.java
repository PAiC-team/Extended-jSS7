package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
*
<code>
CalledPartyBCDNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(
bound.&minCalledPartyBCDNumberLength ..
bound.&maxCalledPartyBCDNumberLength))
-- Indicates the Called Party Number, including service selection information. Refer to GSM 04.08
-- for encoding. This data type carries only the "type of number", "numbering plan
-- identification" and "number digit" fields defined in GSM 04.08;
-- it does not carry the "called party
-- BCD number IEI" or "length of called party BCD number contents".
</code>

*
* minCalledPartyBCDNumberLength ::= 1 maxCalledPartyBCDNumberLength ::= 41
*
*
* @author sergey vetyutnev
*
*/
public interface CalledPartyBCDNumber extends AddressString {

    byte[] getData();

    AddressNature getAddressNature();

    NumberingPlan getNumberingPlan();

    String getAddress();

    boolean isExtension();

}
