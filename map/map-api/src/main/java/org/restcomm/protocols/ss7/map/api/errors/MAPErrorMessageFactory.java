
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;

/**
 * The factory of MAP ReturnError messages
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageFactory {

    /**
     * Generate the empty message depends of the error code (for incoming messages)
     *
     * @param errorCode
     * @return
     */
    MAPErrorMessage createMessageFromErrorCode(Long errorCode);

    MAPErrorMessageParameterless createMAPErrorMessageParameterless(Long errorCode);

    MAPErrorMessageExtensionContainer createMAPErrorMessageExtensionContainer(Long errorCode,
            MAPExtensionContainer extensionContainer);

    MAPErrorMessageSMDeliveryFailure createMAPErrorMessageSMDeliveryFailure(long mapProtocolVersion,
            SMEnumeratedDeliveryFailureCause smEnumeratedDeliveryFailureCause, byte[] signalInfo,
            MAPExtensionContainer extensionContainer);

    MAPErrorMessageFacilityNotSup createMAPErrorMessageFacilityNotSup(MAPExtensionContainer extensionContainer,
            Boolean shapeOfLocationEstimateNotSupported, Boolean neededLcsCapabilityNotSupportedInServingNode);

    MAPErrorMessageSystemFailure createMAPErrorMessageSystemFailure(long mapVersion, NetworkResource networkResource,
            AdditionalNetworkResource additionalNetworkResource, MAPExtensionContainer extensionContainer);

    MAPErrorMessageUnknownSubscriber createMAPErrorMessageUnknownSubscriber(MAPExtensionContainer extensionContainer,
            UnknownSubscriberDiagnostic unknownSubscriberDiagnostic);

    MAPErrorMessageAbsentSubscriberSM createMAPErrorMessageAbsentSubscriberSM(
            AbsentSubscriberDiagnosticSM absentSubscriberDiagnosticSM, MAPExtensionContainer extensionContainer,
            AbsentSubscriberDiagnosticSM additionalAbsentSubscriberDiagnosticSM);

    MAPErrorMessageAbsentSubscriber createMAPErrorMessageAbsentSubscriber(Boolean mwdSet);

    MAPErrorMessageSubscriberBusyForMtSms createMAPErrorMessageSubscriberBusyForMtSms(
            MAPExtensionContainer extensionContainer, Boolean gprsConnectionSuspended);

    MAPErrorMessageCallBarred createMAPErrorMessageCallBarred(Long mapVersion, CallBarringCause callBarringCause,
            MAPExtensionContainer extensionContainer, Boolean unauthorisedMessageOriginator);

    MAPErrorMessageAbsentSubscriber createMAPErrorMessageAbsentSubscriber(MAPExtensionContainer extensionContainer,
            AbsentSubscriberReason absentSubscriberReason);

    MAPErrorMessageUnauthorizedLCSClient createMAPErrorMessageUnauthorizedLCSClient(
            UnauthorizedLCSClientDiagnostic unauthorizedLCSClientDiagnostic, MAPExtensionContainer extensionContainer);

    MAPErrorMessagePositionMethodFailure createMAPErrorMessagePositionMethodFailure(
            PositionMethodFailureDiagnostic positionMethodFailureDiagnostic, MAPExtensionContainer extensionContainer);

    MAPErrorMessageBusySubscriber createMAPErrorMessageBusySubscriber(MAPExtensionContainer extensionContainer,
            boolean ccbsPossible, boolean ccbsBusy);

    MAPErrorMessageCUGReject createMAPErrorMessageCUGReject(CUGRejectCause cugRejectCause,
            MAPExtensionContainer extensionContainer);

    MAPErrorMessageRoamingNotAllowed createMAPErrorMessageRoamingNotAllowed(
            RoamingNotAllowedCause roamingNotAllowedCause, MAPExtensionContainer extensionContainer,
            AdditionalRoamingNotAllowedCause additionalRoamingNotAllowedCause);

    MAPErrorMessageSsErrorStatus createMAPErrorMessageSsErrorStatus(int data);

    MAPErrorMessageSsErrorStatus createMAPErrorMessageSsErrorStatus(boolean qBit, boolean pBit, boolean rBit,
            boolean aBit);

    MAPErrorMessageSsIncompatibility createMAPErrorMessageSsIncompatibility(SSCode ssCode,
            BasicServiceCode basicService, SSStatus ssStatus);

    MAPErrorMessagePwRegistrationFailure createMAPErrorMessagePwRegistrationFailure(
            PWRegistrationFailureCause pwRegistrationFailureCause);

}
