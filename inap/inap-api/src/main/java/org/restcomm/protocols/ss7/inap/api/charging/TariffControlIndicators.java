package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
tariffControlIndicators {
  non-cyclicTariff (0)
} (SIZE(minTariffIndicatorsLen..maxTariffIndicatorsLen)) ,

minTariffIndicatorsLen INTEGER ::= 1
maxTariffIndicatorsLen INTEGER ::= 8
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffControlIndicators extends Serializable {

    boolean getNonCyclicTariff();

}
