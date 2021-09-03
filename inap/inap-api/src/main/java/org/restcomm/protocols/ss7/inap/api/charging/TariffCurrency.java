package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
TariffCurrency ::= SEQUENCE {
  currentTariffCurrency [00] TariffCurrencyFormat OPTIONAL ,
  tariffSwitchCurrency  [01] TariffSwitchCurrency OPTIONAL
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffCurrency extends Serializable {

    TariffCurrencyFormat getTariffCurrencyFormat();

    TariffSwitchCurrency getTariffSwitchCurrency();

}
