
package org.restcomm.protocols.ss7.cap.api.errors;

import java.io.Serializable;

/**
 * Base class of MAP ReturnError messages
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessage extends Serializable {

    Long getErrorCode();

    boolean isEmParameterless();

    boolean isEmCancelFailed();

    boolean isEmRequestedInfoError();

    boolean isEmSystemFailure();

    boolean isEmTaskRefused();

    CAPErrorMessageParameterless getEmParameterless();

    CAPErrorMessageCancelFailed getEmCancelFailed();

    CAPErrorMessageRequestedInfoError getEmRequestedInfoError();

    CAPErrorMessageSystemFailure getEmSystemFailure();

    CAPErrorMessageTaskRefused getEmTaskRefused();

}