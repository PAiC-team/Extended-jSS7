
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 FreeFormatData ::= OCTET STRING (SIZE (1..160))
 *
 *
 * -- The endOfReplyDigit, cancelDigit, and startDigit parameters have been -- designated as OCTET STRING, and are to be encoded
 * as BCD, one digit per octet -- only, contained in the four least significant bits of each OCTET. The following encoding shall
 * -- be applied for the non-decimal characters: -- 1011 (*), 1100 (#).
 *
 *
 *
 * @author Lasith Waruna Perera
 *
 */
public interface FreeFormatDataGprs extends Serializable {
    byte[] getData();
}
