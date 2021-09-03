
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceOamListener extends MAPServiceListener {

    void onActivateTraceModeRequest_Oam(ActivateTraceModeRequest_Oam activateTraceModeRequestOamIndication);

    void onActivateTraceModeResponse_Oam(ActivateTraceModeResponse_Oam activateTraceModeResponseOamIndication);

    void onSendImsiRequest(SendImsiRequest sendImsiRequestIndication);

    void onSendImsiResponse(SendImsiResponse sendImsiResponseIndication);

}
