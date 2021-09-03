
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
 NAEA-CIC ::= OCTET STRING (SIZE (3)) -- The internal structure is defined by the Carrier Identification -- parameter in ANSI
 * T1.113.3. Carrier codes between 000 and 999 may -- be encoded as 3 digits using 000 to 999 or as 4 digits using -- 0000 to
 * 0999. Carrier codes between 1000 and 9999 are encoded -- using 4 digits. -- using 4 digits.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NAEACIC extends Serializable {

    byte[] getData();

    String getCarrierCode();

    NetworkIdentificationPlanValue getNetworkIdentificationPlanValue();

    NetworkIdentificationTypeValue getNetworkIdentificationTypeValue();

}
