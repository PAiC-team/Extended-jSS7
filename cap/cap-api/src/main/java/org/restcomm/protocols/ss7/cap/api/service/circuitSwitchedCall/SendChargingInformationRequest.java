
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.SCIBillingChargingCharacteristics;

/**
 *
 sendChargingInformation {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT SendChargingInformationArg {bound} RETURN RESULT
 * FALSE ERRORS {missingParameter | unexpectedComponentSequence | unexpectedParameter | parameterOutOfRange | systemFailure |
 * taskRefused | unexpectedDataValue | unknownLegID} CODE opcode-sendChargingInformation} -- Direction: gsmSCF -> gsmSSF, Timer:
 * Tsci -- This operation is used to instruct the gsmSSF on the charging information to send by the gsmSSF. -- The charging
 * information can either be sent back by means of signalling or internal -- if the gsmSSF is located in the local exchange. In
 * the local exchange -- this information may be used to update the charge meter or to create a standard call record.
 *
 * SendChargingInformationArg {PARAMETERS-BOUND : bound}::= SEQUENCE { sCIBillingChargingCharacteristics [0]
 * SCIBillingChargingCharacteristics {bound}, partyToCharge [1] SendingSideID, extensions [2] Extensions {bound} OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendChargingInformationRequest extends CircuitSwitchedCallMessage {

    SCIBillingChargingCharacteristics getSCIBillingChargingCharacteristics();

    SendingSideID getPartyToCharge();

    CAPExtensions getExtensions();

}