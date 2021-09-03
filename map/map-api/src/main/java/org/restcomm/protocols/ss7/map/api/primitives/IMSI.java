
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
<code>
IMSI ::= TBCD-STRING (SIZE (3..8))
-- digits of MCC, MNC, MSIN are concatenated in this order.

TBCD-STRING ::= OCTET STRING
-- This type (Telephony Binary Coded Decimal String) is used to
-- represent several digits from 0 through 9, *, #, a, b, c, two
-- digits per octet, each digit encoded 0000 to 1001 (0 to 9),
-- 1010 (*), 1011 (#), 1100 (a), 1101 (b) or 1110 (c); 1111 used
-- as filler when there is an odd number of digits.

-- bits 8765 of octet n encoding digit 2n -- bits 4321 of octet n encoding digit 2(n-1) +1
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface IMSI extends Serializable {

    /**
     * Returns all digits of IMSI (MCC+MNC+MSIN)
     *
     * @return
     */
    String getData();

}
