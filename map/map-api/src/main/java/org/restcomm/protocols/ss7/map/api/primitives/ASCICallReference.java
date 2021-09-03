
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * ASCI-CallReference ::= TBCD-STRING (SIZE (1..8)) -- digits of VGCS/VBS-area,Group-ID are concatenated in this order if there
 * is a -- VGCS/VBS-area.
 *
 * TBCD-STRING ::= OCTET STRING -- This type (Telephony Binary Coded Decimal String) is used to -- represent several digits from
 * 0 through 9, *, #, a, b, c, two -- digits per octet, each digit encoded 0000 to 1001 (0 to 9), -- 1010 (*), 1011 (#), 1100
 * (a), 1101 (b) or 1110 (c); 1111 used -- as filler when there is an odd number of digits.
 *
 * -- bits 8765 of octet n encoding digit 2n -- bits 4321 of octet n encoding digit 2(n-1) +1
 *
 * @author sergey vetyutnev
 *
 */
public interface ASCICallReference extends Serializable {

    String getData();

}
