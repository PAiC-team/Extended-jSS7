
package org.restcomm.protocols.ss7.map.api.service.mobility;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ForwardCheckSSIndicationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ResetRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceMobilityListener extends MAPServiceListener {

    // -- Location Management Service
    void onUpdateLocationRequest(UpdateLocationRequest updateLocationRequestIndication);

    void onUpdateLocationResponse(UpdateLocationResponse updateLocationResponseIndication);

    void onCancelLocationRequest(CancelLocationRequest cancelLocationRequest);

    void onCancelLocationResponse(CancelLocationResponse cancelLocationResponse);

    void onSendIdentificationRequest(SendIdentificationRequest sendIdentificationRequest);

    void onSendIdentificationResponse(SendIdentificationResponse sendIdentificationResponse);

    void onUpdateGprsLocationRequest(UpdateGprsLocationRequest updateGprsLocationRequest);

    void onUpdateGprsLocationResponse(UpdateGprsLocationResponse updateGprsLocationResponse);

    void onPurgeMSRequest(PurgeMSRequest purgeMSRequest);

    void onPurgeMSResponse(PurgeMSResponse purgeMSResponse);

    // -- Authentication management services
    void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest sendAuthenticationInfoRequestIndication);

    void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse sendAuthenticationInfoResponseIndication);

    void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest authenticationFailureReportRequestIndication);

    void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse authenticationFailureReportResponseIndication);

    // -- Fault Recovery services
    void onResetRequest(ResetRequest resetRequestIndication);

    void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest forwardCheckSSIndicationRequestIndication);

    void onRestoreDataRequest(RestoreDataRequest restoreDataRequestIndication);

    void onRestoreDataResponse(RestoreDataResponse restoreDataResponseIndication);

    // -- Subscriber Information services
    void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest anyTimeInterrogationRequest);

    void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse anyTimeInterrogationResponse);

    void onAnyTimeSubscriptionInterrogationRequest(AnyTimeSubscriptionInterrogationRequest anyTimeSubscriptionInterrogationRequest);

    void onAnyTimeSubscriptionInterrogationResponse(AnyTimeSubscriptionInterrogationResponse anyTimeSubscriptionInterrogationResponse);

    void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest provideSubscriberInfoRequest);

    void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse provideSubscriberInfoResponse);

    // -- Subscriber Management services
    void onInsertSubscriberDataRequest(InsertSubscriberDataRequest insertSubscriberDataRequest);

    void onInsertSubscriberDataResponse(InsertSubscriberDataResponse insertSubscriberDataResponse);

    void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest deleteSubscriberDataRequest);

    void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse deleteSubscriberDataResponse);

    // -- International mobile equipment identities management services
    void onCheckImeiRequest(CheckImeiRequest checkImeiRequest);

    void onCheckImeiResponse(CheckImeiResponse checkImeiResponse);

    // -- OAM service: activateTraceMode operation can be present in networkLocUpContext and gprsLocationUpdateContext application contexts
    void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility activateTraceModeRequestMobilityIndication);

    void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility activateTraceModeResponseMobilityIndication);

}
