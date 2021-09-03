package org.restcomm.protocols.ss7.map.api;

import java.util.EnumSet;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public enum MAPApplicationContextName {
    /**
     * Look at http://www.oid-info.com/get/0.4.0.0.1.0.19.2
     */

    // -- Mobility Services
    // --- Location management services
    networkLocUpContext(1), locationCancellationContext(2), interVlrInfoRetrievalContext(15), msPurgingContext(27),
    gprsLocationUpdateContext(32), mmEventReportingContext(42),

    // --- Handover services
    handoverControlContext(11),

    // --- Authentication management services
    infoRetrievalContext(14), authenticationFailureReportContext(39),

    // --- IMEI management services
    equipmentMngtContext(13),

    // --- Subscriber management services
    subscriberDataMngtContext(16),

    // --- Fault recovery services
    resetContext(10),
    // networkLocUpContext(1), doubled in Location management services

    // --- Subscriber Information services
    anyTimeEnquiryContext(29), subscriberInfoEnquiryContext(28), anyTimeInfoHandlingContext(43),
    subscriberDataModificationNotificationContext(22),

    // -- oam
    tracingContext(17),
    // handoverControlContext(11), doubled in Handover services
    imsiRetrievalContext(26),

    // -- Call Handling Services
    locationInfoRetrievalContext(5), roamingNumberEnquiryContext(3), callControlTransferContext(6),
    groupCallControlContext(31), groupCallInfoRetrievalContext(45), reportingContext(7), istAlertingContext(4),
    ServiceTerminationContext(9), resourceManagementContext(44),

    // -- Supplementary services
    networkFunctionalSsContext(18), networkUnstructuredSsContext(19), ssInvocationNotificationContext(36),
    callCompletionContext(8),

    // -- short message service
    shortMsgGatewayContext(20), shortMsgMORelayContext(21), shortMsgMTRelayContext(25), shortMsgMTVgcsRelayContext(41),
    shortMsgAlertContext(23), mwdMngtContext(24),

    // -- Network-Requested PDP Context Activation services
    gprsLocationInfoRetrievalContext(33), failureReportContext(34), gprsNotifyContext(35),

    // -- Location Service (lms)
    locationSvcEnquiryContext(38), locationSvcGatewayContext(37);

    private int code;

    private MAPApplicationContextName(int code) {
        this.code = code;
    }

    public int getApplicationContextCode() {
        return this.code;
    }

    public static MAPApplicationContextName getInstance(Long code) {

        EnumSet<MAPApplicationContextName> lst = EnumSet.allOf(MAPApplicationContextName.class);
        for (MAPApplicationContextName el : lst) {
            if (el.code == code)
                return el;
        }

        return null;
    }

}
