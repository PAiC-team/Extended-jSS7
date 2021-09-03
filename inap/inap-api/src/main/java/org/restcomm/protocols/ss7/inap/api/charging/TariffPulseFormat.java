package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
TariffPulseFormat ::= SEQUENCE {
  communicationChargeSequencePulse [00] SEQUENCE SIZE(minCommunicationTariffNum.. maxCommunicationTariffNum)
    OF CommunicationChargePulse OPTIONAL ,
  tariffControlIndicators          [01] BIT STRING { non-cyclicTariff (0) } (SIZE(minTariffIndicatorsLen..maxTariffIndicatorsLen)) ,
  callAttemptChargePulse           [02] PulseUnits OPTIONAL ,
  callSetupChargePulse             [03] PulseUnits OPTIONAL
}
-- The communication charges are meter-pulse units, which are to be applied per charge unit time interval.
-- The call attempt pulse units are to be charged only for unsuccessful calls.
-- The call set-up pulse units are to be charged once at start of charging.
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffPulseFormat extends Serializable {

    ArrayList<CommunicationChargePulse> getCommunicationChargeSequencePulse();

    TariffControlIndicators getTariffControlIndicators();

    PulseUnits getCallAttemptChargePulse();

    PulseUnits getCallSetupChargePulse();

}
