package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.charging.AddOnChargingInformation;
import org.restcomm.protocols.ss7.inap.api.charging.ChargingTariffInformation;

/**
*
<code>
    tariff [1] CHOICE {
      crgt  [0] ChargingTariffInformation,
      aocrg [1] AddOnChargingInformation
    } OPTIONAL,
    ...
  }
</code>
*
* @author sergey vetyutnev
*
*/
public interface Tariff extends Serializable {

    ChargingTariffInformation getChargingTariffInformation();

    AddOnChargingInformation getAddOnChargingInformation();

}
