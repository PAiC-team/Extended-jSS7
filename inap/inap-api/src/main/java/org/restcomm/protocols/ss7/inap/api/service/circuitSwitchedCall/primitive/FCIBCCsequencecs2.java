package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
  fCIBCCsequencecs2 [51] SEQUENCE {
    fCIBCC [0] OCTET STRING (SIZE (bound.&minFCIbccCS2Length .. bound.&maxFCIbccCS2Length)) OPTIONAL,
-- Its content is network operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this parameter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
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
public interface FCIBCCsequencecs2 extends Serializable {

    FCIBCC getFCIBCC();

    Tariff getTariff();

}
