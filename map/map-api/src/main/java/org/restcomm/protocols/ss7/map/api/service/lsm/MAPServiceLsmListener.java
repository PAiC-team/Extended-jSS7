package org.restcomm.protocols.ss7.map.api.service.lsm;

import org.restcomm.protocols.ss7.map.api.MAPServiceListener;

/**
 * @author amit bhayani
 *
 */
public interface MAPServiceLsmListener extends MAPServiceListener {

    void onProvideSubscriberLocationRequest(ProvideSubscriberLocationRequest provideSubscriberLocationRequestIndication);

    void onProvideSubscriberLocationResponse(ProvideSubscriberLocationResponse provideSubscriberLocationResponseIndication);

    void onSubscriberLocationReportRequest(SubscriberLocationReportRequest subscriberLocationReportRequestIndication);

    void onSubscriberLocationReportResponse(SubscriberLocationReportResponse subscriberLocationReportResponseIndication);

    void onSendRoutingInfoForLCSRequest(SendRoutingInfoForLCSRequest sendRoutingInfoForLCSRequestIndication);

    void onSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse sendRoutingInfoForLCSResponseIndication);
}
