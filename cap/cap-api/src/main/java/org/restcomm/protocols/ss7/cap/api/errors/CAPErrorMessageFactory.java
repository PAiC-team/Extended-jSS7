
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 * The factory of CAP ReturnError messages
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessageFactory {

    /**
     * Generate the empty message depends of the error code (for incoming messages)
     *
     * @param errorCode
     * @return
     */
    CAPErrorMessage createMessageFromErrorCode(Long errorCode);

    CAPErrorMessageParameterless createCAPErrorMessageParameterless(Long errorCode);

    CAPErrorMessageCancelFailed createCAPErrorMessageCancelFailed(CancelProblem cancelProblem);

    CAPErrorMessageRequestedInfoError createCAPErrorMessageRequestedInfoError(
            RequestedInfoErrorParameter requestedInfoErrorParameter);

    CAPErrorMessageSystemFailure createCAPErrorMessageSystemFailure(UnavailableNetworkResource unavailableNetworkResource);

    CAPErrorMessageTaskRefused createCAPErrorMessageTaskRefused(TaskRefusedParameter taskRefusedParameter);

}
