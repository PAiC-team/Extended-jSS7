
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
FreeFormatData ::= OCTET STRING (SIZE (1..160))

-- The endOfReplyDigit, cancelDigit, and startDigit parameters have been
-- designated as OCTET STRING, and are to be encoded as BCD, one digit per octet
-- only, contained in the four least significant bits of each OCTET. The following encoding shall
-- be applied for the non-decimal characters:
-- 1011 (*), 1100 (#).
</code>
*
*
*
* @author Lasith Waruna Perera
*
*/
public interface FreeFormatData extends Serializable {
    byte[] getData();
}
