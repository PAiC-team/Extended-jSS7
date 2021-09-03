package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
ChargingControlIndicators ::= BIT STRING {
  subscriberCharge (0),
  immediateChangeOfActuallyAppliedTariff (1),
  delayUntilStart (2)
} (SIZE(minChargingControlIndicatorsLen..maxChargingControlIndicatorsLen))

minChargingControlIndicatorsLen INTEGER ::= 1
maxChargingControlIndicatorsLen INTEGER ::= 8

-- Coding of "subscriberCharge":
-- 0 - advice-of-charge: charging information only to be used by the advice of charge service.
-- 1 - subscriber-charge: charging information to be used by the subscriber charging program.
-- Coding of "immediateChangeOfActuallyAppliedTariff":
-- 0 - immediate tariff change without restart
-- 1 - immediate tariff change with restart
-- It is only used to change the actually applied tariff.
-- Coding of 'delayUntilStart':
-- 0 - start tariffing, if it is not already started, without waiting for the 'start' signal
-- 1 - delay start of tariffing up to the receipt of the 'start' signal
</code>
*
* @author sergey vetyutnev
*
*/
public interface ChargingControlIndicators extends Serializable {

    boolean getSubscriberCharge();

    Boolean getImmediateChangeOfActuallyAppliedTariff();

    Boolean getDelayUntilStart();

}
