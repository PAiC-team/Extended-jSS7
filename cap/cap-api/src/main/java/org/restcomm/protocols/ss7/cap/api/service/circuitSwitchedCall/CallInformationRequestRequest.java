
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformationType;

/**
 *
 callInformationRequest {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT CallInformationRequestArg {bound} RETURN RESULT
 * FALSE ERRORS {missingParameter | parameterOutOfRange | requestedInfoError | systemFailure | taskRefused |
 * unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownLegID} CODE opcode-callInformationRequest}
 * -- Direction: gsmSCF -> gsmSSF, Timer: Tcirq -- This operation is used to request the gsmSSF to record specific information
 * about a single -- call party and report it to the gsmSCF (with a CallInformationReport operation).
 *
 * CallInformationRequestArg {PARAMETERS-BOUND : bound}::= SEQUENCE { requestedInformationTypeList [0]
 * RequestedInformationTypeList, extensions [2] Extensions {bound} OPTIONAL, legID [3] SendingSideID DEFAULT sendingSideID:leg2,
 * ... } -- OPTIONAL denotes network operator optional.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallInformationRequestRequest extends CircuitSwitchedCallMessage {

    ArrayList<RequestedInformationType> getRequestedInformationTypeList();

    CAPExtensions getExtensions();

    SendingSideID getLegID();

}