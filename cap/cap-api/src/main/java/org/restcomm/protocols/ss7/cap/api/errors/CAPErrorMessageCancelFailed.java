
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 cancelFailed ERROR ::= { PARAMETER SEQUENCE { problem [0] ENUMERATED { unknownOperation (0), tooLate (1),
 * operationNotCancellable (2) }, operation [1] InvokeID, ... } CODE errcode-cancelFailed }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessageCancelFailed extends CAPErrorMessage {

    CancelProblem getCancelProblem();

}
