
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.FCIBCCCAMELsequence1;

/**
 *
<code>
furnishChargingInformation {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT FurnishChargingInformationArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownLegID}
  CODE opcode-furnishChargingInformation
}
-- Direction: gsmSCF -> gsmSSF, Timer: T fci
-- This operation is used to request the gsmSSF to generate, register a call record
-- or to include some information in the default call record.
-- The registered call record is intended for off line charging of the call.

FurnishChargingInformationArg {PARAMETERS-BOUND : bound} ::= FCIBillingChargingCharacteristics{bound}

FCIBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minFCIBillingChargingLength .. bound.&maxFCIBillingChargingLength))
(CONSTRAINED BY {
-- shall be the result of the BER-encoded value of type
-- CAMEL-FCIBillingChargingCharacteristics {bound}})
-- This parameter indicates the billing and/or charging characteristics.
-- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.

minFCIBillingChargingLength ::= 5
maxFCIBillingChargingLength ::= 255

CAMEL-FCIBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= CHOICE{
  fCIBCCCAMELsequence1 [0] SEQUENCE {
    freeFormatData       [0] OCTET STRING (SIZE( bound.&minFCIBillingChargingDataLength .. bound.&maxFCIBillingChargingDataLength)),
    partyToCharge        [1] SendingSideID DEFAULT sendingSideID: leg1,
    appendFreeFormatData [2] AppendFreeFormatData DEFAULT overwrite,
    ...
  }
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FurnishChargingInformationRequest extends CircuitSwitchedCallMessage {

    FCIBCCCAMELsequence1 getFCIBCCCAMELsequence1();

}