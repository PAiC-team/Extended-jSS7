
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
ChargingCharacteristics ::= OCTET STRING (SIZE (2))
-- Octets are coded according to 3GPP TS 32.215.

hargingCharacteristics ::= OCTET STRING (SIZE(2))
--
-- Bit 0-3: Profile Index
-- Bit 4-15: For Behavior

Profile index bits (byte 0, bits 3-0 - P3-P0):
the P3 (N) flag in the Charging Characteristics indicates normal charging,
the P2 (P) flag indicates prepaid charging,
the P1 (F) flag indicates flat rate charging and
the P0 (H) flag indicates charging by hot billing.
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChargingCharacteristics extends Serializable {

    byte[] getData();

    boolean isNormalCharging();

    boolean isPrepaidCharging();

    boolean isFlatRateChargingCharging();

    boolean isChargingByHotBillingCharging();

}
