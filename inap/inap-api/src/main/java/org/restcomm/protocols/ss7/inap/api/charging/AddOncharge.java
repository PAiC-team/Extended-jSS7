package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
  addOncharge [01] CHOICE {
    addOnChargeCurrency [00] CurrencyFactorScale ,
    addOnChargePulse    [01] PulseUnits
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface AddOncharge extends Serializable {

    CurrencyFactorScale getAddOnChargeCurrency();

    PulseUnits getAddOnChargePulse();

}
