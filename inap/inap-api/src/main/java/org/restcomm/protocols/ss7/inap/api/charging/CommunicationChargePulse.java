package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
CommunicationChargePulse ::= SEQUENCE {
  pulseUnits             [00] PulseUnits ,
  chargeUnitTimeInterval [01] ChargeUnitTimeInterval ,
  tariffDuration         [02] TariffDuration
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface CommunicationChargePulse extends Serializable {

    PulseUnits getPulseUnits();

    ChargeUnitTimeInterval getChargeUnitTimeInterval();

    TariffDuration getTariffDuration();

}
