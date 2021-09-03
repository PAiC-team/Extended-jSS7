package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
ChargeUnitTimeInterval ::= OCTET STRING (SIZE(2))
-- The ChargeUnitTimeInterval is binary coded and has the value range from 0 to 35997. It begins with 200 milliseconds and
-- then in steps of 50 milliseconds.
-- the LSBit is the least significant bit of the first octet
-- the MSBit is the most significant bit of the last octet
-- the coding of the ChargeUnitTimeInterval is the following:
-- 0 : no periodic metering
-- 1 : 200 msec
-- 2 : 250 msec
-- ..
-- 35997 : 30 minutes
-- All other values are spare.
</code>
*
* @author sergey vetyutnev
*
*/
public interface ChargeUnitTimeInterval extends Serializable {

    int getData();

}
