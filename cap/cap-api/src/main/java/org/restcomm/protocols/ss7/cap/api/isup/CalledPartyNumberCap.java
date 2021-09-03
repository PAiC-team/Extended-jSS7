
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CalledPartyNumber;

/**
 *
<code>
ISUP CalledPartyNumber wrapper

CalledPartyNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCalledPartyNumberLength .. bound.&maxCalledPartyNumberLength))
-- Indicates the Called Party Number. Refer to ETS EN 300 356-1 [23] for encoding.

-- A CalledPartyNumber may contain national-specific values of the Nature Of Address
-- indicator. The filling-in of the national-specific Nature Of Address indicator
-- values shall be done in accordance with the national ISUP of the gsmSSF country, e.g.
-- ANSI T1.113-1995 [92].
-- In terms of ETS EN 300 356-1 [23], the Destination Address Field is not present if the
-- destination address length is set to zero. This is the case e.g. when the ANSI
-- ISUP Nature Of Address indicator indicates no number present, operator requested
-- (1110100) or no number present, cut-through call to carrier (1110101).
-- See also see 3GPP TS 23.078 [7].

minCalledPartyNumberLength ::= 2
maxCalledPartyNumberLength ::= 18
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CalledPartyNumberCap extends Serializable {

    byte[] getData();

    CalledPartyNumber getCalledPartyNumber() throws CAPException;

}