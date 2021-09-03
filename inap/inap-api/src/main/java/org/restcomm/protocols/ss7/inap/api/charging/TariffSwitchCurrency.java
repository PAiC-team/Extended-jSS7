package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
TariffSwitchCurrency ::= SEQUENCE {
  nextTariffCurrency    [00] TariffCurrencyFormat ,
  tariffSwitchoverTime  [01] TariffSwitchoverTime
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffSwitchCurrency extends Serializable {

    TariffCurrencyFormat getNextTariffCurrency();

    TariffSwitchoverTime getTariffSwitchoverTime();

}
