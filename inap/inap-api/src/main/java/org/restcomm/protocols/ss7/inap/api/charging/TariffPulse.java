package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
TariffPulse ::= SEQUENCE {
  currentTariffPulse [00] TariffPulseFormat OPTIONAL ,
  tariffSwitchPulse  [01] TariffSwitchPulse OPTIONAL }
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffPulse extends Serializable {

    TariffPulseFormat getCurrentTariffPulse();

    TariffSwitchPulse getTariffSwitchPulse();

}
