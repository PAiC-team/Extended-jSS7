
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.IPSSPCapabilities;

/**
 *
 assistRequestInstructions {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT AssistRequestInstructionsArg {bound} RETURN
 * RESULT FALSE ERRORS {missingCustomerRecord | missingParameter | systemFailure | taskRefused | unexpectedComponentSequence |
 * unexpectedDataValue | unexpectedParameter} CODE opcode-assistRequestInstructions} -- Direction: gsmSSF -> gsmSCF or gsmSRF ->
 * gsmSCF, Timer: Tari -- This operation is used when there is an assist procedure and may be -- sent by the gsmSSF or gsmSRF to
 * the gsmSCF. This operation is sent by the -- assisting gsmSSF to gsmSCF, when the initiating gsmSSF has set up a connection
 * to -- the gsmSRF or to the assisting gsmSSF as a result of receiving an -- EstablishTemporaryConnection from -- the gsmSCF.
 * -- Refer to clause 11 for a description of the procedures associated with this operation.
 *
 * AssistRequestInstructionsArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { correlationID [0] CorrelationID {bound},
 * iPSSPCapabilities [2] IPSSPCapabilities {bound}, extensions [3] Extensions {bound} OPTIONAL, ... } -- OPTIONAL denotes
 * network operator specific use. The value of the correlationID may be the -- Called Party Number supplied by the initiating
 * gsmSSF.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AssistRequestInstructionsRequest extends CircuitSwitchedCallMessage {

    /**
     * Use Digits.getGenericNumber() for CorrelationID
     *
     * @return
     */
    Digits getCorrelationID();

    IPSSPCapabilities getIPSSPCapabilities();

    CAPExtensions getExtensions();
}