
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 requestedInfoError ERROR ::= { PARAMETER ENUMERATED { unknownRequestedInfo (1), requestedInfoNotAvailable (2) } CODE
 * errcode-requestedInfoError } -- The requested information cannot be found.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessageRequestedInfoError extends CAPErrorMessage {

    RequestedInfoErrorParameter getRequestedInfoErrorParameter();

}
