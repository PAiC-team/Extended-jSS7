package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.INAPExtensions;

/**
*
<code>
ChargingTariffInformation ::= SEQUENCE {
  chargingControlIndicators [00] ChargingControlIndicators,
  chargingTariff            [01] CHOICE {
    tariffCurrency  [00] TariffCurrency,
    tariffPulse     [01] TariffPulse
  },
  extensions                [02] SEQUENCE SIZE(1..numOfExtensions) OF ExtensionField OPTIONAL,
  originationIdentification [03] ChargingReferenceIdentification,
  destinationIdentification [04] ChargingReferenceIdentification OPTIONAL,
  currency                  [05] Currency
}
--This message is used
-- to transfer explicit tariff data to the originating subscriber exchange and the charge  registration exchange during call
-- set-up and also in the active phase of a call.
-- The destinationIdentification is not available in an initial ChargingTariffInformation, in all subsequent ones it is included, see
-- "Handling of Identifiers".
</code>
*
* @author sergey vetyutnev
*
*/
public interface ChargingTariffInformation extends Serializable {

    ChargingControlIndicators getChargingControlIndicators();

    ChargingTariff getChargingTariff();

    INAPExtensions getExtensions();

    ChargingReferenceIdentification getOriginationIdentification();

    ChargingReferenceIdentification getDestinationIdentification();

    Currency getCurrency();

}
