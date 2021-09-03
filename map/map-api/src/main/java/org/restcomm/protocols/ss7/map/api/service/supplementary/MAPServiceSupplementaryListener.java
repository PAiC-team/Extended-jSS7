
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceSupplementaryListener extends MAPServiceListener {

    void onRegisterSSRequest(RegisterSSRequest registerSSRequest);

    void onRegisterSSResponse(RegisterSSResponse registerSSResponse);

    void onEraseSSRequest(EraseSSRequest eraseSSRequest);

    void onEraseSSResponse(EraseSSResponse eraseSSResponse);

    void onActivateSSRequest(ActivateSSRequest activateSSRequest);

    void onActivateSSResponse(ActivateSSResponse activateSSResponse);

    void onDeactivateSSRequest(DeactivateSSRequest deactivateSSRequest);

    void onDeactivateSSResponse(DeactivateSSResponse deactivateSSResponse);

    void onInterrogateSSRequest(InterrogateSSRequest interrogateSSRequest);

    void onInterrogateSSResponse(InterrogateSSResponse interrogateSSResponse);

    void onGetPasswordRequest(GetPasswordRequest getPasswordRequest);

    void onGetPasswordResponse(GetPasswordResponse getPasswordResponse);

    void onRegisterPasswordRequest(RegisterPasswordRequest registerPasswordRequest);

    void onRegisterPasswordResponse(RegisterPasswordResponse registerPasswordResponse);

    void onProcessUnstructuredSSRequest(ProcessUnstructuredSSRequest processUnstructuredSSRequest);

    void onProcessUnstructuredSSResponse(ProcessUnstructuredSSResponse processUnstructuredSSResponse);

    void onUnstructuredSSRequest(UnstructuredSSRequest unstructuredSSRequest);

    void onUnstructuredSSResponse(UnstructuredSSResponse unstructuredSSResponse);

    void onUnstructuredSSNotifyRequest(UnstructuredSSNotifyRequest unstructuredSSNotifyRequest);

    void onUnstructuredSSNotifyResponse(UnstructuredSSNotifyResponse unstructuredSSNotifyResponse);
}
