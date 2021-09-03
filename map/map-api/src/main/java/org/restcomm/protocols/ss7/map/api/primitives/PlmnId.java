
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
 PLMN-Id ::= OCTET STRING (SIZE (3)) -- The internal structure is defined as follows: -- octet 1 bits 4321 Mobile Country Code
 * 1st digit -- bits 8765 Mobile Country Code 2nd digit -- octet 2 bits 4321 Mobile Country Code 3rd digit -- bits 8765 Mobile
 * Network Code 3rd digit -- or filler (1111) for 2 digit MNCs -- octet 3 bits 4321 Mobile Network Code 1st digit -- bits 8765
 * Mobile Network Code 2nd digit
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PlmnId extends Serializable {

    byte[] getData();

    int getMcc();

    int getMnc();

}
