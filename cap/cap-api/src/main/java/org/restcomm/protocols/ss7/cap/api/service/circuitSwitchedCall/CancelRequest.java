
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CallSegmentToCancel;

/**
 *
 cancel {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT CancelArg {bound} RETURN RESULT FALSE ERRORS {cancelFailed |
 * missingParameter | taskRefused | unknownCSID} CODE opcode-cancel} -- Direction: gsmSCF -> gsmSSF, or gsmSCF -> gsmSRF, Timer:
 * T can -- This operation cancels the correlated previous operation or all previous requests. The following -- operations can
 * be canceled: PlayAnnouncement, PromptAndCollectUserInformation.
 *
 * CancelArg {PARAMETERS-BOUND : bound} ::= CHOICE { invokeID [0] InvokeID, allRequests [1] NULL, callSegmentToCancel [2]
 * CallSegmentToCancel {bound} } -- The InvokeID has the same value as that which was used for the operation to be cancelled.
 *
 * InvokeID ::= TCInvokeIdSet (from TCAP)
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CancelRequest extends CircuitSwitchedCallMessage {

    Integer getInvokeID();

    boolean getAllRequests();

    CallSegmentToCancel getCallSegmentToCancel();

}