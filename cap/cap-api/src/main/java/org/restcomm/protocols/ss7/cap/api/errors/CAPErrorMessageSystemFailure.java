
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 systemFailure ERROR ::= { PARAMETER UnavailableNetworkResource CODE errcode-systemFailure } -- The operation could not be
 * completed due to a system failure at the serving physical entity.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessageSystemFailure extends CAPErrorMessage {

    UnavailableNetworkResource getUnavailableNetworkResource();

}
