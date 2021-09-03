
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPServicePdpContextActivationListener extends MAPServiceListener {

    void onSendRoutingInfoForGprsRequest(SendRoutingInfoForGprsRequest sendRoutingInfoForGprsRequest);

    void onSendRoutingInfoForGprsResponse(SendRoutingInfoForGprsResponse sendRoutingInfoForGprsResponse);

}
