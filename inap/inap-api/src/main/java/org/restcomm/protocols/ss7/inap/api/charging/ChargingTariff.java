package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
  chargingTariff            [01] CHOICE {
    tariffCurrency  [00] TariffCurrency,
    tariffPulse     [01] TariffPulse
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface ChargingTariff extends Serializable {

    TariffCurrency getTariffCurrency();

    TariffPulse getTariffPulse();

}
