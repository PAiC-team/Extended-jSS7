
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
<code>
GlobalCellId ::= OCTET STRING (SIZE (5..7))
-- Refers to Cell Global Identification defined in TS 3GPP TS 23.003 [17].
-- The internal structure is defined as follows:
-- octet 1 bits 4321 Mobile Country Code 1st digit
--         bits 8765 Mobile Country Code 2nd digit
-- octet 2 bits 4321 Mobile Country Code 3rd digit
--         bits 8765 Mobile Network Code 3rd digit
-- or filler (1111) for 2 digit MNCs
-- octet 3 bits 4321 Mobile Network Code 1st digit
--         bits 8765 Mobile Network Code 2nd digit
-- octets 4 and 5 Location Area Code according to TS 3GPP TS 24.008 [35]
-- octets 6 and 7 Cell Identity (CI) according to TS 3GPP TS 24.008 [35]
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GlobalCellId extends Serializable {

    byte[] getData();

    int getMcc() throws MAPException;

    int getMnc() throws MAPException;

    int getLac() throws MAPException;

    int getCellId() throws MAPException;

}
