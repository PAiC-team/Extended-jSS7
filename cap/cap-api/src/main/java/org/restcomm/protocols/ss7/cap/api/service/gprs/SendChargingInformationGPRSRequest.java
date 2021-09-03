
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;

/**
 *
 sendChargingInformationGPRS {PARAMETERS-BOUND: bound} OPERATION ::= { ARGUMENT SendChargingInformationGPRSArg { bound} RETURN
 * RESULT FALSE ERRORS {missingParameter | unexpectedComponentSequence | unexpectedParameter | parameterOutOfRange |
 * systemFailure | taskRefused | unexpectedDataValue | unknownPDPID} CODE opcode-sendChargingInformationGPRS} -- Direction:
 * gsmSCF -> gprsSSF, Timer: Tscig -- This operation is used to instruct the gprsSSF on the charging information which the --
 * gprsSSF shall send to the Mobile Station by means of GSM access signalling.
 *
 * SendChargingInformationGPRSArg {PARAMETERS-BOUND: bound}::= SEQUENCE { sCIGPRSBillingChargingCharacteristics [0]
 * SCIGPRSBillingChargingCharacteristics {bound}, ... }
 *
 * SCIGPRSBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (4 .. 255)) (CONSTRAINED BY {-- shall
 * be the result of the BER-encoded value of type CAMEL-SCIGPRSBillingChargingCharacteristics}) -- Indicates AOC information to
 * be sent to a Mobile Station -- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.
 *
 * CAMEL-SCIGPRSBillingChargingCharacteristics ::= SEQUENCE { aOCGPRS [0] AOCGPRS, pDPID [1] PDPID OPTIONAL, ... }
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendChargingInformationGPRSRequest extends GprsMessage {

    CAMELSCIGPRSBillingChargingCharacteristics getSCIGPRSBillingChargingCharacteristics();

}