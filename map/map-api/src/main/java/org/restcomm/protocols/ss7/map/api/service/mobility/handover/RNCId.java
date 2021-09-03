
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 RNCId ::= OCTET STRING (SIZE (7)) -- The internal structure is defined as follows: -- octet 1 bits 4321 Mobile Country Code
 * 1st digit -- bits 8765 Mobile Country Code 2nd digit -- octet 2 bits 4321 Mobile Country Code 3rd digit -- bits 8765 Mobile
 * Network Code 3rd digit -- or filler (1111) for 2 digit MNCs -- octet 3 bits 4321 Mobile Network Code 1st digit -- bits 8765
 * Mobile Network Code 2nd digit -- octets 4 and 5 Location Area Code according to 3GPP TS 24.008 -- octets 6 and 7 RNC Id value
 * according to 3GPP TS 25.413
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RNCId extends Serializable {

    byte[] getData();

    int getMcc();

    int getMnc();

    int getLac();

    int getRncId();

}
