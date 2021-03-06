package org.restcomm.protocols.ss7.map.api.errors;

/**
 * MAP Error codes Carried by ReturnError primitive
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorCode {

    // -- values band
    int minimalCodeValue = 1;
    int maximumCodeValue = 72;

    // -- generic error codes
    int systemFailure = 34;
    int dataMissing = 35;
    int unexpectedDataValue = 36;
    int facilityNotSupported = 21;
    int incompatibleTerminal = 28;
    int resourceLimitation = 51;

    // -- call handling error codes
    int noRoamingNumberAvailable = 39;
    int absentSubscriber = 27;
    int busySubscriber = 45;
    int noSubscriberReply = 46;
    int callBarred = 13;
    int forwardingFailed = 47;
    int orNotAllowed = 48;
    int forwardingViolation = 14;
    int cugReject = 15;

    // -- identification and numbering errors
    int unknownSubscriber = 1;
    int numberChanged = 44;
    int unknownMSC = 3;
    int unidentifiedSubscriber = 5;
    int unknownEquipment = 7;

    // -- subscription error codes
    int roamingNotAllowed = 8;
    int illegalSubscriber = 9;
    int illegalEquipment = 12;
    int bearerServiceNotProvisioned = 10;
    int teleserviceNotProvisioned = 11;

    // -- short message service errors
    int subscriberBusyForMTSMS = 31;
    int smDeliveryFailure = 32;
    int messageWaitingListFull = 33;
    int absentSubscriberSM = 6;

    // -- location service errors
    int unauthorizedRequestingNetwork = 52;
    int unauthorizedLCSClient = 53;
    int positionMethodFailure = 54;
    int unknownOrUnreachableLCSClient = 58;
    int mmEventNotSupported = 59;

    // -- supplementary service errors
    int illegalSSOperation = 16;
    int ssErrorStatus = 17;
    int ssNotAvailable = 18;
    int ssSubscriptionViolation = 19;
    int ssIncompatibility = 20;
    int unknownAlphabet = 71;
    int ussdBusy = 72;
    int pwRegistrationFailure = 37;
    int negativePWCheck = 38;
    int numberOfPWAttemptsViolation = 43;
    int shortTermDenial = 29;
    int longTermDenial = 30;

    /**
     * systemFailure | unexpectedDataValue | facilityNotSupported | sm-DeliveryFailure} dataMissing | unidentifiedSubscriber |
     * illegalSubscriber | illegalEquipment | subscriberBusyForMT-SMS | absentSubscriberSM unknownSubscriber |
     * teleserviceNotProvisioned | callBarred | messageWaitingListFull absentSubscriber unauthorizedRequestingNetwork
     * unauthorizedLCSClient positionMethodFailure resourceLimitation unknownOrUnreachableLCSClient
     *
     */

}
