package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.INAPExtensions;

/**
*
<code>
AddOnChargingInformation ::= SEQUENCE {
  chargingControlIndicators [00] ChargingControlIndicators ,
  addOncharge [01] CHOICE {
    addOnChargeCurrency [00] CurrencyFactorScale ,
    addOnChargePulse    [01] PulseUnits
  },
  extensions                [02] SEQUENCE SIZE(1..numOfExtensions) OF ExtensionField OPTIONAL,
  originationIdentification [03] ChargingReferenceIdentification,
  destinationIdentification [04] ChargingReferenceIdentification OPTIONAL,
  currency                  [05] Currency
}
-- This message is used to add an amount of charge for the call and does not alter the current tariff.
-- The destinationIdentification is not available in an initial AddOnChargingInformation, in all subsequent ones it is included, see
-- "Handling of Identifiers".
-- In the message the
-- add-on charge has either the pulse or currency format.
</code>
*
* @author sergey vetyutnev
*
*/
public interface AddOnChargingInformation extends Serializable {

    ChargingControlIndicators getChargingControlIndicators();

    AddOncharge getAddOncharge();

    INAPExtensions getExtensions();

    ChargingReferenceIdentification getOriginationIdentification();

    ChargingReferenceIdentification getDestinationIdentification();

    Currency getCurrency();

}
