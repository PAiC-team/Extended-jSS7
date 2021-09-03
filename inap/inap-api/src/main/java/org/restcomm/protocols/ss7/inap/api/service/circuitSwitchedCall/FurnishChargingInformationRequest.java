package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive.FCIBCCcs1;
import org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive.FCIBCCsequencecs2;

/**
*
<code>
furnishChargingInformation {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT FurnishChargingInformationArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter }
  CODE opcode-furnishChargingInformation
}
-- Direction: SCF -> SSF, Timer: Tfci
-- This operation is used to request the SSF to generate, register a call record or to include some
-- information in the default call record.
-- The registered call record is intended for off line charging of the call.

FurnishChargingInformationArg {PARAMETERS-BOUND : bound} ::= FCIBillingChargingCharacteristics {bound}

FCIBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= CHOICE {
  fCIBCCcs1 OCTET STRING (SIZE (bound.&minFCIBCcs1Length..bound.&maxFCIbccCS1Length)),
-- Its content is network operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this parameter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
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
}

-- This parameter indicates the billing and/or charging characteristics.
-- Its content is network operator specific.
-- An example datatype definition for this parameter is given below:
ETSI
61 ETSI EN 301 140-1 V1.3.4 (1999-06)
-- FCIBillingChargingCharacteristics ::= CHOICE {
-- completeChargingrecord [0] OCTET STRING (SIZE (min..max)),
-- correlationID [1] CorrelationID,
-- scenario2Dot3 [2] SEQUENCE {
-- chargeParty [0] LegID OPTIONAL,
-- chargeLevel [1] OCTET STRING (SIZE (min..max)) OPTIONAL,
-- chargeItems [2] SET OF Attribute OPTIONAL
-- }
-- }

</code>
*
*
* @author sergey vetyutnev
*
*/
public interface FurnishChargingInformationRequest {

    FCIBCCcs1 getFCIBCCcs1();

    FCIBCCsequencecs2 getFCIBCCsequencecs2();

}
