package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
TariffSwitchPulse ::= SEQUENCE {
  nextTariffPulse      [00] TariffPulseFormat ,
  tariffSwitchoverTime [01] TariffSwitchoverTime
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffSwitchPulse extends Serializable {

    TariffPulseFormat getNextTariffPulse();

    TariffSwitchoverTime getTariffSwitchoverTime();

}
