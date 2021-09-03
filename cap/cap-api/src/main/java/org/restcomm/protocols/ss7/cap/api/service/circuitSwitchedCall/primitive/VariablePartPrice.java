
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
price [4] OCTET STRING (SIZE(4))

-- Price is BCD encoded. The digit indicating hundreds of thousands occupies bits 0-3 of the
-- first octet, and the digit indicating tens of thousands occupies bits 4-7 of the first octet.
-- The digit indicating thousands occupies bits 0-3 of the second octet, whilst the digit
-- indicating hundreds occupies bits 4-7 of the second octet. The digit indicating tens occupies
-- bits 0-3 of the third octet, and the digit indicating 0 to 9 occupies bits 4-7 of the third
-- octet. The tenths digit occupies bits 0-3 of the fourth octet, and the hundredths digit
-- occupies bits 4-7 of the fourth octet.
-- -- For the encoding of digits in an octet, refer to the timeAndtimezone parameter

-- The Definition of range of constants follows numOfInfoItems INTEGER ::= 4
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VariablePartPrice extends Serializable {

    byte[] getData();

    double getPrice();

    int getPriceIntegerPart();

    int getPriceHundredthPart();

}