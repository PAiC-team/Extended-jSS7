
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
<code>
CellGlobalIdOrServiceAreaIdOrLAI ::= CHOICE {
  cellGlobalIdOrServiceAreaIdFixedLength  [0] CellGlobalIdOrServiceAreaIdFixedLength,
  laiFixedLength                          [1] LAIFixedLength
}
</code>
 *
 * @author amit bhayani
 *
 */
public interface CellGlobalIdOrServiceAreaIdOrLAI extends Serializable {

    /**
     * CellGlobalIdOrServiceAreaIdFixedLength ::= OCTET STRING (SIZE (7)) -- Refers to Cell Global Identification or Service Are
     * Identification -- defined in 3GPP TS 23.003. -- The internal structure is defined as follows: -- octet 1 bits 4321 Mobile
     * Country Code 1st digit -- bits 8765 Mobile Country Code 2nd digit -- octet 2 bits 4321 Mobile Country Code 3rd digit --
     * bits 8765 Mobile Network Code 3rd digit -- or filler (1111) for 2 digit MNCs -- octet 3 bits 4321 Mobile Network Code 1st
     * digit -- bits 8765 Mobile Network Code 2nd digit -- octets 4 and 5 Location Area Code according to 3GPP TS 24.008 --
     * octets 6 and 7 Cell Identity (CI) value or -- Service Area Code (SAC) value -- according to 3GPP TS 23.003
     *
     * @return
     */
    CellGlobalIdOrServiceAreaIdFixedLength getCellGlobalIdOrServiceAreaIdFixedLength();

    /**
     * LAIFixedLength ::= OCTET STRING (SIZE (5)) -- Refers to Location Area Identification defined in TS 3GPP TS 23.003 [17].
     * -- The internal structure is defined as follows: -- octet 1 bits 4321 Mobile Country Code 1st digit -- bits 8765 Mobile
     * Country Code 2nd digit -- octet 2 bits 4321 Mobile Country Code 3rd digit -- bits 8765 Mobile Network Code 3rd digit --
     * or filler (1111) for 2 digit MNCs -- octet 3 bits 4321 Mobile Network Code 1st digit -- bits 8765 Mobile Network Code 2nd
     * digit -- octets 4 and 5 Location Area Code according to TS 3GPP TS 24.008
     *
     * @return
     */
    LAIFixedLength getLAIFixedLength();

}
