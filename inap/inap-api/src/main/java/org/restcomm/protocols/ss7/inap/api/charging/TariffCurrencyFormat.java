package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
TariffCurrencyFormat ::= SEQUENCE {
  communicationChargeSequenceCurrency [00] SEQUENCE SIZE(minCommunicationTariffNum..maxCommunicationTariffNum)
    OF CommunicationChargeCurrency OPTIONAL,
  tariffControlIndicators             [01] BIT STRING { non-cyclicTariff (0) } (SIZE(minTariffIndicatorsLen..maxTariffIndicatorsLen)) ,
  callAttemptChargeCurrency           [02] CurrencyFactorScale OPTIONAL ,
  callSetupChargeCurrency             [03] CurrencyFactorScale OPTIONAL
}

minCommunicationTariffNum INTEGER ::= 1
maxCommunicationTariffNum INTEGER ::= 4

-- The communication charge sequence currency is a direct charge in currency per time unit. Only one fixed time unit is used.
-- This time unit has
-- to be agreed between all cooperating networks, e.g. one second. Being fixed, the time unit is not transferred over
-- ISUP/INAP.
-- The call attempt charge is a direct charge, to be charged only for unsuccessful calls.
-- The call set-up charge is a direct charge, to be charged once at start of charging.
--
-- The coding of the non-cyclicTariff is as follows:
-- 0 - Cyclic tariff ( at expiration of the tariff duration of the last communication tariff of the communication charge sequence,
-- the communication charge sequence is re-applied.
-- 1 - Non-cyclic tariff ( at expiration of the tariff duration of the last communication tariff of the communication charge
-- sequence, do not re-apply the communication charge sequence)
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffCurrencyFormat extends Serializable {

    ArrayList<CommunicationChargeCurrency> getCommunicationChargeSequenceCurrency();

    TariffControlIndicators getTariffControlIndicators();

    CurrencyFactorScale getCallAttemptChargeCurrency();

    CurrencyFactorScale getCallSetupChargeCurrency();

}
