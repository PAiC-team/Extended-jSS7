
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ExtExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ISTSupportIndicator;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

import java.util.ArrayList;

/*
 *
 * @author cristian veliscu
 * @author eva ogallar
 *
 */
public interface MAPDialogCallHandling extends MAPDialog {

     Long addSendRoutingInformationRequest(ISDNAddressString msisdn, CUGCheckInfo cugCheckInfo,
            Integer numberOfForwarding, InterrogationType interrogationType, boolean orInterrogation, Integer orCapability,
            ISDNAddressString gmscAddress, CallReferenceNumber callReferenceNumber, ForwardingReason forwardingReason,
            ExtBasicServiceCode basicServiceGroup, ExternalSignalInfo networkSignalInfo, CamelInfo camelInfo,
            boolean suppressionOfAnnouncement, MAPExtensionContainer extensionContainer, AlertingPattern alertingPattern,
            boolean ccbsCall, Integer supportedCCBSPhase, ExtExternalSignalInfo additionalSignalInfo,
            ISTSupportIndicator istSupportIndicator, boolean prePagingSupported,
            CallDiversionTreatmentIndicator callDiversionTreatmentIndicator, boolean longFTNSupported, boolean suppressVtCSI,
            boolean suppressIncomingCallBarring, boolean gsmSCFInitiatedCall, ExtBasicServiceCode basicServiceGroup2,
            ExternalSignalInfo networkSignalInfo2, SuppressMTSS supressMTSS, boolean mtRoamingRetrySupported,
            EMLPPPriority callPriority) throws MAPException;

     Long addSendRoutingInformationRequest(ISDNAddressString msisdn, CUGCheckInfo cugCheckInfo,
            Integer numberOfForwarding, ExternalSignalInfo networkSignalInfo) throws MAPException;

     Long addSendRoutingInformationRequest(int customInvokeTimeout, ISDNAddressString msisdn, CUGCheckInfo cugCheckInfo,
            Integer numberOfForwarding, ExternalSignalInfo networkSignalInfo) throws MAPException;

     Long addSendRoutingInformationRequest(int customInvokeTimeout, ISDNAddressString msisdn, CUGCheckInfo cugCheckInfo,
            Integer numberOfForwarding, InterrogationType interrogationType, boolean orInterrogation, Integer orCapability,
            ISDNAddressString gmscAddress, CallReferenceNumber callReferenceNumber, ForwardingReason forwardingReason,
            ExtBasicServiceCode basicServiceGroup, ExternalSignalInfo networkSignalInfo, CamelInfo camelInfo,
            boolean suppressionOfAnnouncement, MAPExtensionContainer extensionContainer, AlertingPattern alertingPattern,
            boolean ccbsCall, Integer supportedCCBSPhase, ExtExternalSignalInfo additionalSignalInfo,
            ISTSupportIndicator istSupportIndicator, boolean prePagingSupported,
            CallDiversionTreatmentIndicator callDiversionTreatmentIndicator, boolean longFTNSupported, boolean suppressVtCSI,
            boolean suppressIncomingCallBarring, boolean gsmSCFInitiatedCall, ExtBasicServiceCode basicServiceGroup2,
            ExternalSignalInfo networkSignalInfo2, SuppressMTSS supressMTSS, boolean mtRoamingRetrySupported,
            EMLPPPriority callPriority) throws MAPException;

     void addSendRoutingInformationResponse(long invokeId, IMSI imsi, ExtendedRoutingInfo extRoutingInfo,
            CUGCheckInfo cugCheckInfo, boolean cugSubscriptionFlag, SubscriberInfo subscriberInfo, ArrayList<SSCode> ssList,
            ExtBasicServiceCode basicService, boolean forwardingInterrogationRequired, ISDNAddressString vmscAddress,
            MAPExtensionContainer extensionContainer, NAEAPreferredCI naeaPreferredCI, CCBSIndicators ccbsIndicators,
            ISDNAddressString msisdn, NumberPortabilityStatus nrPortabilityStatus, Integer istAlertTimer,
            SupportedCamelPhases supportedCamelPhases, OfferedCamel4CSIs offeredCamel4CSIs, RoutingInfo routingInfo2,
            ArrayList<SSCode> ssList2, ExtBasicServiceCode basicService2, AllowedServices allowedServices,
            UnavailabilityCause unavailabilityCause, boolean releaseResourcesSupported, ExternalSignalInfo gsmBearerCapability)
            throws MAPException;

     void addSendRoutingInformationResponse(long invokeId, IMSI imsi, CUGCheckInfo cugCheckInfo, RoutingInfo routingInfo2)
            throws MAPException;

     void addSendRoutingInformationResponse_NonLast(long invokeId, IMSI imsi, ExtendedRoutingInfo extRoutingInfo,
            CUGCheckInfo cugCheckInfo, boolean cugSubscriptionFlag, SubscriberInfo subscriberInfo, ArrayList<SSCode> ssList,
            ExtBasicServiceCode basicService, boolean forwardingInterrogationRequired, ISDNAddressString vmscAddress,
            MAPExtensionContainer extensionContainer, NAEAPreferredCI naeaPreferredCI, CCBSIndicators ccbsIndicators,
            ISDNAddressString msisdn, NumberPortabilityStatus nrPortabilityStatus, Integer istAlertTimer,
            SupportedCamelPhases supportedCamelPhases, OfferedCamel4CSIs offeredCamel4CSIs, RoutingInfo routingInfo2,
            ArrayList<SSCode> ssList2, ExtBasicServiceCode basicService2, AllowedServices allowedServices,
            UnavailabilityCause unavailabilityCause, boolean releaseResourcesSupported, ExternalSignalInfo gsmBearerCapability)
            throws MAPException;

     Long addProvideRoamingNumberRequest(IMSI imsi, ISDNAddressString mscNumber, ISDNAddressString msisdn, LMSI lmsi,
            ExternalSignalInfo gsmBearerCapability, ExternalSignalInfo networkSignalInfo, boolean suppressionOfAnnouncement,
            ISDNAddressString gmscAddress, CallReferenceNumber callReferenceNumber, boolean orInterrogation,
            MAPExtensionContainer extensionContainer, AlertingPattern alertingPattern, boolean ccbsCall,
            SupportedCamelPhases supportedCamelPhasesInInterrogatingNode, ExtExternalSignalInfo additionalSignalInfo,
            boolean orNotSupportedInGMSC, boolean prePagingSupported, boolean longFTNSupported, boolean suppressVtCsi,
            OfferedCamel4CSIs offeredCamel4CSIsInInterrogatingNode, boolean mtRoamingRetrySupported, PagingArea pagingArea,
            EMLPPPriority callPriority, boolean mtrfIndicator, ISDNAddressString oldMSCNumber) throws MAPException;

     Long addProvideRoamingNumberRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString mscNumber,
            ISDNAddressString msisdn, LMSI lmsi, ExternalSignalInfo gsmBearerCapability, ExternalSignalInfo networkSignalInfo,
            boolean suppressionOfAnnouncement, ISDNAddressString gmscAddress, CallReferenceNumber callReferenceNumber,
            boolean orInterrogation, MAPExtensionContainer extensionContainer, AlertingPattern alertingPattern,
            boolean ccbsCall, SupportedCamelPhases supportedCamelPhasesInInterrogatingNode,
            ExtExternalSignalInfo additionalSignalInfo, boolean orNotSupportedInGMSC, boolean prePagingSupported,
            boolean longFTNSupported, boolean suppressVtCsi, OfferedCamel4CSIs offeredCamel4CSIsInInterrogatingNode,
            boolean mtRoamingRetrySupported, PagingArea pagingArea, EMLPPPriority callPriority, boolean mtrfIndicator,
            ISDNAddressString oldMSCNumber) throws MAPException;

     void addProvideRoamingNumberResponse(long invokeId, ISDNAddressString roamingNumber,
            MAPExtensionContainer extensionContainer, boolean releaseResourcesSupported, ISDNAddressString vmscAddress)
            throws MAPException;

     Long addIstCommandRequest(IMSI imsi, MAPExtensionContainer extensionContainer) throws MAPException;

     Long addIstCommandRequest(int customInvokeTimeout, IMSI imsi, MAPExtensionContainer extensionContainer) throws MAPException;

     void addIstCommandResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException;
}