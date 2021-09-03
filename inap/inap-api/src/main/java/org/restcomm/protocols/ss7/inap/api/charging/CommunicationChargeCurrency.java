package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
CommunicationChargeCurrency ::= SEQUENCE {
  currencyFactorScale [00] CurrencyFactorScale ,
  tariffDuration      [01] TariffDuration ,
  subTariffControl    [02] SubTariffControl
}

TariffDuration ::= INTEGER (0..36000)
-- TariffDuration identifies with 0 unlimited duration and else in seconds unit.
-- 0 = unlimited
-- 1 = 1 second
-- 2 = 2 seconds
-- ...
-- 36000 = 10 hours
--
-- The duration indicates for how long time the communication charge component is valid. Expiration of the tariff duration
-- timer leads to the activation of the next communication charge (if present).
-- In the case where there is no next communication charge in the communication charge sequence, the action to be performed
-- is indicated by the tariffControlIndicators.
</code>
*
* @author sergey vetyutnev
*
*/
public interface CommunicationChargeCurrency extends Serializable {

    CurrencyFactorScale getCurrencyFactorScale();

    int getTariffDuration();

    SubTariffControl getSubTariffControl();

}
