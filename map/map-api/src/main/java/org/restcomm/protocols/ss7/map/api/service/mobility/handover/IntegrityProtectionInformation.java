
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 IntegrityProtectionInformation ::= OCTET STRING (SIZE (18..maxNumOfIntegrityInfo)) -- Octets contain a complete
 * IntegrityProtectionInformation data type -- as defined in 3GPP TS 25.413, encoded according to the encoding scheme --
 * mandated by 3GPP TS 25.413 -- Padding bits are included, if needed, in the least significant bits of the -- last octet of the
 * octet string.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IntegrityProtectionInformation extends Serializable {

    byte[] getData();

}
