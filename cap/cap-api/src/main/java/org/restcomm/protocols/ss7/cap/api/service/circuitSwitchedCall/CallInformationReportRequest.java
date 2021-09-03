
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformation;

/**
 *
 callInformationReport {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT CallInformationReportArg {bound} RETURN RESULT
 * FALSE ALWAYS RESPONDS FALSE CODE opcode-callInformationReport} -- Direction: gsmSSF -> gsmSCF, Timer: T cirp -- This
 * operation is used to send specific call information for a single call party to the gsmSCF as -- requested by the gsmSCF in a
 * previous CallInformationRequest.
 *
 * CallInformationReportArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { requestedInformationList [0] RequestedInformationList
 * {bound}, extensions [2] Extensions {bound} OPTIONAL, legID [3] ReceivingSideID DEFAULT receivingSideID:leg2, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallInformationReportRequest extends CircuitSwitchedCallMessage {

    ArrayList<RequestedInformation> getRequestedInformationList();

    CAPExtensions getExtensions();

    ReceivingSideID getLegID();

}