
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
date [3] OCTET STRING (SIZE(4)),
-- YYYYMMDD, BCD coded

-- Date is BCD encoded. The year digit indicating millenium occupies bits 0-3 of the first octet,
-- and the year digit indicating century occupies bits 4-7 of the first octet. The year digit
-- indicating decade occupies bits 0-3 of the second octet, whilst the digit indicating the year
-- within the decade occupies bits 4-7 of the second octet.
-- The most significant month digit occupies bits 0-3 of the third octet, and the least
-- significant month digit occupies bits 4-7 of the third octet. The most significant day digit
-- occupies bits 0-3 of the fourth octet, and the least significant day digit occupies bits 4-7
-- of the fourth octet.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VariablePartDate extends Serializable {

    byte[] getData();

    int getYear();

    int getMonth();

    int getDay();

}