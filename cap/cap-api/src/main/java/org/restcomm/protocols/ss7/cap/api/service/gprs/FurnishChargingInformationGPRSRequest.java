
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELFCIGPRSBillingChargingCharacteristics;

/**
 *
 furnishChargingInformationGPRS {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT FurnishChargingInformationGPRSArg {bound}
 * RETURN RESULT FALSE ERRORS {missingParameter | taskRefused | unexpectedComponentSequence | unexpectedDataValue |
 * unexpectedParameter | unknownPDPID} CODE opcode-furnishChargingInformationGPRS} -- Direction: gsmSCF -> gprsSSF, Timer: Tfcig
 * -- This operation is used to request the gprsSSF to generate, register a logical record or to -- include some information in
 * the default logical GPRS record. -- The registered logical record is intended for off line charging of the GPRS session -- or
 * PDP Context.
 *
 * FurnishChargingInformationGPRSArg {PARAMETERS-BOUND : bound} ::= FCIGPRSBillingChargingCharacteristics{bound}
 *
 * FCIGPRSBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(1 .. 160)) (CONSTRAINED BY {-- shall
 * be the result of the BER-encoded value of type - CAMEL-FCIGPRSBillingChargingCharacteristics {bound}}) -- This parameter
 * indicates the GPRS billing and/or charging characteristics. -- The violation of the UserDefinedConstraint shall be handled as
 * an ASN.1 syntax error.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FurnishChargingInformationGPRSRequest extends GprsMessage {

    CAMELFCIGPRSBillingChargingCharacteristics getFCIGPRSBillingChargingCharacteristics();

}