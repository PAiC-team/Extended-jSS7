
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FCIBCCCAMELsequence1SMS;

/**
 *
<code>
furnishChargingInformationSMS {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT FurnishChargingInformationSMSArg {bound}
   RETURN RESULT FALSE ERRORS {
     missingParameter | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter}
   CODE opcode-furnishChargingInformationSMS}
   -- Direction: gsmSCF gsmSSF or gprsSSF, Timer: T fcisms
   -- This operation is used to request the smsSSF to generate, register a charging record
   -- or to include some information in the default SM record. The registered charging record is
   -- intended for off line charging of the Short Message.

FurnishChargingInformationSMSArg {PARAMETERS-BOUND : bound} ::= FCISMSBillingChargingCharacteristics {bound}

FCISMSBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(5 .. 255)) (CONSTRAINED BY {
-- shall be the result of the BER-encoded value of type - CAMEL-FCISMSBillingChargingCharacteristics {bound}})
-- This parameter indicates the SMS billing and/or charging characteristics.
-- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FurnishChargingInformationSMSRequest extends SmsMessage {

    FCIBCCCAMELsequence1SMS getFCIBCCCAMELsequence1();

//    CAMELFCISMSBillingChargingCharacteristics getCAMELFCISMSBillingChargingCharacteristics();

}
