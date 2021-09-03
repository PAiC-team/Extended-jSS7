
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/*
 *
 * @author cristian veliscu
 *
 */
public interface MAPServiceCallHandlingListener extends MAPServiceListener {

     void onSendRoutingInformationRequest(SendRoutingInformationRequest sendRoutingInformationRequest);

     void onSendRoutingInformationResponse(SendRoutingInformationResponse sendRoutingInformationResponse);

     void onProvideRoamingNumberRequest(ProvideRoamingNumberRequest provideRoamingNumberRequest);

     void onProvideRoamingNumberResponse(ProvideRoamingNumberResponse provideRoamingNumberResponse);

     void onIstCommandRequest(IstCommandRequest istCommandRequest);

     void onIstCommandResponse(IstCommandResponse istCommandResponse);

}