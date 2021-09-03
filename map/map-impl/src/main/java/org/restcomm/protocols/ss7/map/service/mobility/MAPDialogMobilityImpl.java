
package org.restcomm.protocols.ss7.map.service.mobility;

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.TMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AccessType;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CurrentSecurityContext;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.EpsAuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.FailureCause;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.ReSynchronisationInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.RequestingNodeType;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.RequestedEquipmentInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIu;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ADDInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.AgeIndicator;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancellationType;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.EPSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.IMSIWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SGSNCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedFeatures;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.TypeOfUpdate;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UESRVCCCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UsedRATType;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.VLRCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallBarringData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallForwardingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallHoldData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallWaitingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClipData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClirData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EctData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSISDNBS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ODBInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AccessRestrictionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSAllocationRetentionPriority;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Category;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ChargingCharacteristics;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSSubscriptionDataWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSSubscriptionDataWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LCSInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAInformationWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MCSSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.NetworkAccessMode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBGeneralData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.RegionalSubscriptionResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SGSNCAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SpecificCSIWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SubscriberStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VlrCamelSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VoiceBroadcastData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VoiceGroupCallData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ZoneCode;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationFailureReportRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationFailureReportResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.SendAuthenticationInfoRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.SendAuthenticationInfoResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.ForwardCheckSSIndicationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.ResetRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.RestoreDataRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.RestoreDataResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.imei.CheckImeiRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.imei.CheckImeiResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.CancelLocationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.CancelLocationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.PurgeMSRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.PurgeMSResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.SendIdentificationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.SendIdentificationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.UpdateGprsLocationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.UpdateGprsLocationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.UpdateLocationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.UpdateLocationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.oam.ActivateTraceModeRequestImpl_Mobility;
import org.restcomm.protocols.ss7.map.service.mobility.oam.ActivateTraceModeResponseImpl_Mobility;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.AnyTimeInterrogationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.AnyTimeInterrogationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ProvideSubscriberInfoRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ProvideSubscriberInfoResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.DeleteSubscriberDataRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.DeleteSubscriberDataResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.InsertSubscriberDataRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.InsertSubscriberDataResponseImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPDialogMobilityImpl extends MAPDialogImpl implements MAPDialogMobility {

    protected MAPDialogMobilityImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog, MAPProviderImpl mapProviderImpl,
            MAPServiceMobility mapService, AddressString origReference, AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }

    public Long addSendAuthenticationInfoRequest(IMSI imsi, int numberOfRequestedVectors, boolean segmentationProhibited,
            boolean immediateResponsePreferred, ReSynchronisationInfo reSynchronisationInfo,
            MAPExtensionContainer extensionContainer, RequestingNodeType requestingNodeType, PlmnId requestingPlmnId,
            Integer numberOfRequestedAdditionalVectors, boolean additionalVectorsAreForEPS) throws MAPException {
        return this.addSendAuthenticationInfoRequest(_Timer_Default, imsi, numberOfRequestedVectors, segmentationProhibited,
                immediateResponsePreferred, reSynchronisationInfo, extensionContainer, requestingNodeType, requestingPlmnId,
                numberOfRequestedAdditionalVectors, additionalVectorsAreForEPS);
    }

    public Long addSendAuthenticationInfoRequest(int customInvokeTimeout, IMSI imsi, int numberOfRequestedVectors,
            boolean segmentationProhibited, boolean immediateResponsePreferred, ReSynchronisationInfo reSynchronisationInfo,
            MAPExtensionContainer extensionContainer, RequestingNodeType requestingNodeType, PlmnId requestingPlmnId,
            Integer numberOfRequestedAdditionalVectors, boolean additionalVectorsAreForEPS) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.infoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for sendAuthenticationInfoRequest: must be infoRetrievalContext_V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendAuthenticationInfo);
        invoke.setOperationCode(operationCode);

        if (imsi != null) {
            // parameter is optional: is no imsi is included we will not add a parameter
            SendAuthenticationInfoRequestImpl sendAuthenticationInfoRequest = new SendAuthenticationInfoRequestImpl(this.mapApplicationContext
                    .getApplicationContextVersion().getVersion(), imsi, numberOfRequestedVectors, segmentationProhibited,
                    immediateResponsePreferred, reSynchronisationInfo, extensionContainer, requestingNodeType,
                    requestingPlmnId, numberOfRequestedAdditionalVectors, additionalVectorsAreForEPS);
            AsnOutputStream aos = new AsnOutputStream();
            sendAuthenticationInfoRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(sendAuthenticationInfoRequest.getTagClass());
            parameter.setPrimitive(sendAuthenticationInfoRequest.getIsPrimitive());
            parameter.setTag(sendAuthenticationInfoRequest.getTag());
            parameter.setData(aos.toByteArray());
            invoke.setParameter(parameter);
        }

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    public void addSendAuthenticationInfoResponse(long invokeId, AuthenticationSetList authenticationSetList,
            MAPExtensionContainer extensionContainer, EpsAuthenticationSetList epsAuthenticationSetList) throws MAPException {
        doAddSendAuthenticationInfoResponse(false, invokeId, authenticationSetList, extensionContainer,
                epsAuthenticationSetList);
    }

    public void addSendAuthenticationInfoResponse_NonLast(long invokeId, AuthenticationSetList authenticationSetList,
            MAPExtensionContainer extensionContainer, EpsAuthenticationSetList epsAuthenticationSetList) throws MAPException {
        doAddSendAuthenticationInfoResponse(true, invokeId, authenticationSetList, extensionContainer, epsAuthenticationSetList);
    }

    protected void doAddSendAuthenticationInfoResponse(boolean nonLast, long invokeId,
            AuthenticationSetList authenticationSetList, MAPExtensionContainer extensionContainer,
            EpsAuthenticationSetList epsAuthenticationSetList) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.infoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException(
                    "Bad application context name for addSendAuthenticationInfoResponse: must be infoRetrievalContext_V2 or V3");
        if (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && nonLast)
            throw new MAPException("Bad application context name for addSendAuthenticationInfoResponse: must be infoRetrievalContext_V2 for NonLast message");

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendAuthenticationInfo);

        Parameter parameter = null;
        if (authenticationSetList != null || extensionContainer != null || epsAuthenticationSetList != null) {

            SendAuthenticationInfoResponseImpl sendAuthenticationInfoResponse = new SendAuthenticationInfoResponseImpl(this.mapApplicationContext
                    .getApplicationContextVersion().getVersion(), authenticationSetList, extensionContainer,
                    epsAuthenticationSetList);
            AsnOutputStream aos = new AsnOutputStream();
            sendAuthenticationInfoResponse.encodeData(aos);

            parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(sendAuthenticationInfoResponse.getTagClass());
            parameter.setPrimitive(sendAuthenticationInfoResponse.getIsPrimitive());
            parameter.setTag(sendAuthenticationInfoResponse.getTag());
            parameter.setData(aos.toByteArray());
        }

        if (nonLast) {
            ReturnResult resultLastNonLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultRequest();

            resultLastNonLast.setInvokeId(invokeId);

            if (parameter != null) {
                resultLastNonLast.setOperationCode(operationCode);
                resultLastNonLast.setParameter(parameter);
            }

            this.sendReturnResultComponent(resultLastNonLast);
        } else {
            ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

            resultLast.setInvokeId(invokeId);

            if (parameter != null) {
                resultLast.setOperationCode(operationCode);
                resultLast.setParameter(parameter);
            }

            this.sendReturnResultLastComponent(resultLast);
        }
    }

    @Override
    public Long addAuthenticationFailureReportRequest(IMSI imsi, FailureCause failureCause, MAPExtensionContainer extensionContainer, Boolean reAttempt,
            AccessType accessType, byte[] rand, ISDNAddressString vlrNumber, ISDNAddressString sgsnNumber) throws MAPException {
        return this.addAuthenticationFailureReportRequest(_Timer_Default, imsi, failureCause, extensionContainer, reAttempt, accessType, rand, vlrNumber,
                sgsnNumber);
    }

    @Override
    public Long addAuthenticationFailureReportRequest(int customInvokeTimeout, IMSI imsi, FailureCause failureCause, MAPExtensionContainer extensionContainer,
            Boolean reAttempt, AccessType accessType, byte[] rand, ISDNAddressString vlrNumber, ISDNAddressString sgsnNumber) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.authenticationFailureReportContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for authenticationFailureReportRequest: must be authenticationFailureReportContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.authenticationFailureReport);
        invoke.setOperationCode(operationCode);

        AuthenticationFailureReportRequestImpl authenticationFailureReportRequest = new AuthenticationFailureReportRequestImpl(imsi, failureCause, extensionContainer, reAttempt, accessType,
                rand, vlrNumber, sgsnNumber);
        AsnOutputStream aos = new AsnOutputStream();
        authenticationFailureReportRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(authenticationFailureReportRequest.getTagClass());
        parameter.setPrimitive(authenticationFailureReportRequest.getIsPrimitive());
        parameter.setTag(authenticationFailureReportRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addAuthenticationFailureReportResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.authenticationFailureReportContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for authenticationFailureReportResponse: must be authenticationFailureReportContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        if (extensionContainer != null) {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.authenticationFailureReport);
            resultLast.setOperationCode(operationCode);

            AuthenticationFailureReportResponseImpl authenticationFailureReportResponse = new AuthenticationFailureReportResponseImpl(extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            authenticationFailureReportResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(authenticationFailureReportResponse.getTagClass());
            parameter.setPrimitive(authenticationFailureReportResponse.getIsPrimitive());
            parameter.setTag(authenticationFailureReportResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    public Long addUpdateLocationRequest(IMSI imsi, ISDNAddressString mscNumber, ISDNAddressString roamingNumber,
            ISDNAddressString vlrNumber, LMSI lmsi, MAPExtensionContainer extensionContainer, VLRCapability vlrCapability,
            boolean informPreviousNetworkEntity, boolean csLCSNotSupportedByUE, GSNAddress vGmlcAddress, ADDInfo addInfo,
            PagingArea pagingArea, boolean skipSubscriberDataUpdate, boolean restorationIndicator) throws MAPException {
        return addUpdateLocationRequest(_Timer_Default, imsi, mscNumber, roamingNumber, vlrNumber, lmsi, extensionContainer,
                vlrCapability, informPreviousNetworkEntity, csLCSNotSupportedByUE, vGmlcAddress, addInfo, pagingArea,
                skipSubscriberDataUpdate, restorationIndicator);
    }

    public Long addUpdateLocationRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString mscNumber,
            ISDNAddressString roamingNumber, ISDNAddressString vlrNumber, LMSI lmsi, MAPExtensionContainer extensionContainer,
            VLRCapability vlrCapability, boolean informPreviousNetworkEntity, boolean csLCSNotSupportedByUE,
            GSNAddress vGmlcAddress, ADDInfo addInfo, PagingArea pagingArea, boolean skipSubscriberDataUpdate,
            boolean restorationIndicator) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkLocUpContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for UpdateLocationRequest: must be networkLocUpContext_V1, V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.updateLocation);
        invoke.setOperationCode(operationCode);

        UpdateLocationRequestImpl updateLocationRequest = new UpdateLocationRequestImpl(this.mapApplicationContext.getApplicationContextVersion().getVersion(),
                imsi, mscNumber, roamingNumber, vlrNumber, lmsi, extensionContainer, vlrCapability,
                informPreviousNetworkEntity, csLCSNotSupportedByUE, vGmlcAddress, addInfo, pagingArea,
                skipSubscriberDataUpdate, restorationIndicator);
        AsnOutputStream aos = new AsnOutputStream();
        updateLocationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(updateLocationRequest.getTagClass());
        parameter.setPrimitive(updateLocationRequest.getIsPrimitive());
        parameter.setTag(updateLocationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    public void addUpdateLocationResponse(long invokeId, ISDNAddressString hlrNumber, MAPExtensionContainer extensionContainer,
            boolean addCapability, boolean pagingAreaCapability) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkLocUpContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for UpdateLocationResponse: must be networkLocUpContext_V1, V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.updateLocation);
        resultLast.setOperationCode(operationCode);

        UpdateLocationResponseImpl updateLocationResponse = new UpdateLocationResponseImpl(this.mapApplicationContext.getApplicationContextVersion()
                .getVersion(), hlrNumber, extensionContainer, addCapability, pagingAreaCapability);
        AsnOutputStream aos = new AsnOutputStream();
        updateLocationResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(updateLocationResponse.getTagClass());
        parameter.setPrimitive(updateLocationResponse.getIsPrimitive());
        parameter.setTag(updateLocationResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. MAPDialogSubscriberInformation
     * #addAnyTimeInterrogationRequest(org.mobicents .protocols.ss7.map.api.primitives.SubscriberIdentity,
     * org.mobicents.protocols .ss7.map.api.service.subscriberInformation.RequestedInfo,
     * org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString,
     * org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer)
     */
    public long addAnyTimeInterrogationRequest(SubscriberIdentity subscriberIdentity, RequestedInfo requestedInfo,
            ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer) throws MAPException {

        return this.addAnyTimeInterrogationRequest(_Timer_Default, subscriberIdentity, requestedInfo, gsmSCFAddress,
                extensionContainer);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * MAPDialogSubscriberInformation#addAnyTimeInterrogationRequest(long,
     * org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity, org.mobicents
     * .protocols.ss7.map.api.service.subscriberInformation.RequestedInfo,
     * org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString,
     * org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer)
     */
    public long addAnyTimeInterrogationRequest(long customInvokeTimeout, SubscriberIdentity subscriberIdentity,
            RequestedInfo requestedInfo, ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer)
            throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.anyTimeEnquiryContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for AnyTimeInterrogationRequest: must be networkLocUpContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.anyTimeInterrogation);
        invoke.setOperationCode(operationCode);

        AnyTimeInterrogationRequestImpl anyTimeInterrogationRequest = new AnyTimeInterrogationRequestImpl(subscriberIdentity, requestedInfo,
                gsmSCFAddress, extensionContainer);

        AsnOutputStream aos = new AsnOutputStream();
        anyTimeInterrogationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(anyTimeInterrogationRequest.getTagClass());
        parameter.setPrimitive(anyTimeInterrogationRequest.getIsPrimitive());
        parameter.setTag(anyTimeInterrogationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * MAPDialogSubscriberInformation#addAnyTimeInterrogationResponse(long)
     */
    public void addAnyTimeInterrogationResponse(long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {
        doAddAnyTimeInterrogationResponse(false, invokeId, subscriberInfo, extensionContainer);
    }

    public void addAnyTimeInterrogationResponse_NonLast(long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {
        doAddAnyTimeInterrogationResponse(true, invokeId, subscriberInfo, extensionContainer);
    }

    protected void doAddAnyTimeInterrogationResponse(boolean nonLast, long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.anyTimeEnquiryContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for AnyTimeInterrogationRequest: must be networkLocUpContext_V3");

        AnyTimeInterrogationResponseImpl anyTimeInterrogationResponse = new AnyTimeInterrogationResponseImpl(subscriberInfo, extensionContainer);
        AsnOutputStream aos = new AsnOutputStream();
        anyTimeInterrogationResponse.encodeData(aos);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.anyTimeInterrogation);

        if (nonLast) {
            ReturnResult resultLastNonLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultRequest();

            resultLastNonLast.setInvokeId(invokeId);
            resultLastNonLast.setOperationCode(operationCode);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(anyTimeInterrogationResponse.getTagClass());
            parameter.setPrimitive(anyTimeInterrogationResponse.getIsPrimitive());
            parameter.setTag(anyTimeInterrogationResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLastNonLast.setParameter(parameter);

            this.sendReturnResultComponent(resultLastNonLast);
        } else {
            ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

            resultLast.setInvokeId(invokeId);
            resultLast.setOperationCode(operationCode);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(anyTimeInterrogationResponse.getTagClass());
            parameter.setPrimitive(anyTimeInterrogationResponse.getIsPrimitive());
            parameter.setTag(anyTimeInterrogationResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);

            this.sendReturnResultLastComponent(resultLast);
        }
    }

    public long addAnyTimeSubscriptionInterrogationRequest(SubscriberIdentity subscriberIdentity,
            RequestedSubscriptionInfo requestedSubscriptionInfo, ISDNAddressString gsmSCFAddress,
            MAPExtensionContainer extensionContainer, boolean isLongFTNSupported) throws MAPException {
        return this.addAnyTimeSubscriptionInterrogationRequest(_Timer_Default, subscriberIdentity, requestedSubscriptionInfo,
                gsmSCFAddress, extensionContainer, isLongFTNSupported);
    }

    public long addAnyTimeSubscriptionInterrogationRequest(int customTimeout, SubscriberIdentity subscriberIdentity,
            RequestedSubscriptionInfo requestedSubscriptionInfo, ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer,
            boolean isLongFTNSupported) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.anyTimeInfoHandlingContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException(
                    "Bad application context name for AnyTimeSubscriptionInterrogationRequest: must be anyTimeInfoHandlingContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.anyTimeSubscriptionInterrogation);
        invoke.setOperationCode(operationCode);

        AnyTimeSubscriptionInterrogationRequestImpl anyTimeSubscriptionInterrogationRequest = new AnyTimeSubscriptionInterrogationRequestImpl(subscriberIdentity, requestedSubscriptionInfo, gsmSCFAddress, extensionContainer, isLongFTNSupported);
        AsnOutputStream aos = new AsnOutputStream();
        anyTimeSubscriptionInterrogationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(anyTimeSubscriptionInterrogationRequest.getTagClass());
        parameter.setPrimitive(anyTimeSubscriptionInterrogationRequest.getIsPrimitive());
        parameter.setTag(anyTimeSubscriptionInterrogationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    public void addAnyTimeSubscriptionInterrogationResponse(long invokeId, CallForwardingData callForwardingData,
            CallBarringData callBarringData, ODBInfo odbInfo, CAMELSubscriptionInfo camelSubscriptionInfo,
            SupportedCamelPhases supportedVlrCamelPhases, SupportedCamelPhases supportedSgsnCamelPhases,
            MAPExtensionContainer extensionContainer, OfferedCamel4CSIs offeredCamel4CSIsInVlr,
            OfferedCamel4CSIs offeredCamel4CSIsInSgsn, ArrayList<MSISDNBS> msisdnBsList,
            ArrayList<CSGSubscriptionData> csgSubscriptionDataList, CallWaitingData callWaitingData, CallHoldData callHoldData,
            ClipData clipData, ClirData clirData, EctData ectData) throws MAPException {
        doAddAnyTimeSubscriptionInterrogationResponse(false, invokeId, callForwardingData, callBarringData, odbInfo,
                camelSubscriptionInfo, supportedVlrCamelPhases, supportedSgsnCamelPhases, extensionContainer,
                offeredCamel4CSIsInVlr, offeredCamel4CSIsInSgsn, msisdnBsList, csgSubscriptionDataList, callWaitingData,
                callHoldData, clipData, clirData, ectData);
    }

    public void addAnyTimeSubscriptionInterrogationResponse_NonLast(long invokeId, CallForwardingData callForwardingData,
            CallBarringData callBarringData, ODBInfo odbInfo, CAMELSubscriptionInfo camelSubscriptionInfo,
            SupportedCamelPhases supportedVlrCamelPhases, SupportedCamelPhases supportedSgsnCamelPhases,
            MAPExtensionContainer extensionContainer, OfferedCamel4CSIs offeredCamel4CSIsInVlr,
            OfferedCamel4CSIs offeredCamel4CSIsInSgsn, ArrayList<MSISDNBS> msisdnBsList,
            ArrayList<CSGSubscriptionData> csgSubscriptionDataList, CallWaitingData callWaitingData, CallHoldData callHoldData,
            ClipData clipData, ClirData clirData, EctData ectData) throws MAPException {
        doAddAnyTimeSubscriptionInterrogationResponse(true, invokeId, callForwardingData, callBarringData, odbInfo,
                camelSubscriptionInfo, supportedVlrCamelPhases, supportedSgsnCamelPhases, extensionContainer,
                offeredCamel4CSIsInVlr, offeredCamel4CSIsInSgsn, msisdnBsList, csgSubscriptionDataList, callWaitingData,
                callHoldData, clipData, clirData, ectData);
    }

    protected void doAddAnyTimeSubscriptionInterrogationResponse(boolean nonLast, long invokeId,
            CallForwardingData callForwardingData, CallBarringData callBarringData, ODBInfo odbInfo,
            CAMELSubscriptionInfo camelSubscriptionInfo, SupportedCamelPhases supportedVlrCamelPhases,
            SupportedCamelPhases supportedSgsnCamelPhases, MAPExtensionContainer extensionContainer,
            OfferedCamel4CSIs offeredCamel4CSIsInVlr, OfferedCamel4CSIs offeredCamel4CSIsInSgsn,
            ArrayList<MSISDNBS> msisdnBsList, ArrayList<CSGSubscriptionData> csgSubscriptionDataList,
            CallWaitingData callWaitingData, CallHoldData callHoldData, ClipData clipData, ClirData clirData, EctData ectData)
            throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.anyTimeInfoHandlingContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for AnyTimeSubscriptionInterrogationRequest: must be anyTimeInfoHandlingContext_V3");

        AnyTimeSubscriptionInterrogationResponseImpl anyTimeSubscriptionInterrogationResponse = new AnyTimeSubscriptionInterrogationResponseImpl(callForwardingData, callBarringData, odbInfo,
                camelSubscriptionInfo, supportedVlrCamelPhases, supportedSgsnCamelPhases, extensionContainer, offeredCamel4CSIsInVlr, offeredCamel4CSIsInSgsn,
                msisdnBsList, csgSubscriptionDataList, callWaitingData, callHoldData, clipData, clirData, ectData);
        AsnOutputStream aos = new AsnOutputStream();
        anyTimeSubscriptionInterrogationResponse.encodeData(aos);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.anyTimeSubscriptionInterrogation);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(anyTimeSubscriptionInterrogationResponse.getTagClass());
        parameter.setPrimitive(anyTimeSubscriptionInterrogationResponse.getIsPrimitive());
        parameter.setTag(anyTimeSubscriptionInterrogationResponse.getTag());
        parameter.setData(aos.toByteArray());

        if (nonLast) {
            ReturnResult resultNonLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultRequest();

            resultNonLast.setInvokeId(invokeId);

            resultNonLast.setOperationCode(operationCode);
            resultNonLast.setParameter(parameter);

            this.sendReturnResultComponent(resultNonLast);
        } else {
            ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

            resultLast.setInvokeId(invokeId);
            resultLast.setOperationCode(operationCode);
            resultLast.setParameter(parameter);

            this.sendReturnResultLastComponent(resultLast);
        }
    }

    @Override
    public long addProvideSubscriberInfoRequest(IMSI imsi, LMSI lmsi, RequestedInfo requestedInfo, MAPExtensionContainer extensionContainer,
            EMLPPPriority callPriority) throws MAPException {
        return this.addProvideSubscriberInfoRequest(_Timer_Default, imsi, lmsi, requestedInfo, extensionContainer, callPriority);
    }

    @Override
    public long addProvideSubscriberInfoRequest(long customInvokeTimeout, IMSI imsi, LMSI lmsi, RequestedInfo requestedInfo,
            MAPExtensionContainer extensionContainer, EMLPPPriority callPriority) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.subscriberInfoEnquiryContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for ProvideSubscriberInfoRequest: must be subscriberInfoEnquiryContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.provideSubscriberInfo);
        invoke.setOperationCode(operationCode);

        ProvideSubscriberInfoRequestImpl provideSubscriberInfoRequest = new ProvideSubscriberInfoRequestImpl(imsi, lmsi, requestedInfo, extensionContainer, callPriority);

        AsnOutputStream aos = new AsnOutputStream();
        provideSubscriberInfoRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(provideSubscriberInfoRequest.getTagClass());
        parameter.setPrimitive(provideSubscriberInfoRequest.getIsPrimitive());
        parameter.setTag(provideSubscriberInfoRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addProvideSubscriberInfoResponse(long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {
        doAddProvideSubscriberInfoResponse(false, invokeId, subscriberInfo, extensionContainer);
    }

    @Override
    public void addProvideSubscriberInfoResponse_NonLast(long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {
        doAddProvideSubscriberInfoResponse(true, invokeId, subscriberInfo, extensionContainer);
    }


    protected void doAddProvideSubscriberInfoResponse(boolean nonLast, long invokeId, SubscriberInfo subscriberInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.subscriberInfoEnquiryContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for ProvideSubscriberInfoResponse: must be subscriberInfoEnquiryContext_V3");

        ProvideSubscriberInfoResponseImpl provideSubscriberInfoResponse = new ProvideSubscriberInfoResponseImpl(subscriberInfo, extensionContainer);
        AsnOutputStream aos = new AsnOutputStream();
        provideSubscriberInfoResponse.encodeData(aos);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.provideSubscriberInfo);

        if (nonLast) {
            ReturnResult resultLastNonLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultRequest();

            resultLastNonLast.setInvokeId(invokeId);

            resultLastNonLast.setOperationCode(operationCode);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(provideSubscriberInfoResponse.getTagClass());
            parameter.setPrimitive(provideSubscriberInfoResponse.getIsPrimitive());
            parameter.setTag(provideSubscriberInfoResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLastNonLast.setParameter(parameter);

            this.sendReturnResultComponent(resultLastNonLast);
        } else {
            ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

            resultLast.setInvokeId(invokeId);

            resultLast.setOperationCode(operationCode);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(provideSubscriberInfoResponse.getTagClass());
            parameter.setPrimitive(provideSubscriberInfoResponse.getIsPrimitive());
            parameter.setTag(provideSubscriberInfoResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);

            this.sendReturnResultLastComponent(resultLast);
        }
    }

    @Override
    public Long addCheckImeiRequest(IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {

        return this.addCheckImeiRequest(_Timer_Default, imei, requestedEquipmentInfo, extensionContainer);
    }

    @Override
    public Long addCheckImeiRequest(long customInvokeTimeout, IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.equipmentMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3)) {
            throw new MAPException("Bad application context name for CheckImeiRequest: must be equipmentMngtContext_V1, V2 or V3");
        }

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default) {
            invoke.setTimeout(getMediumTimer());
        } else {
            invoke.setTimeout(customInvokeTimeout);
        }

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.checkIMEI);
        invoke.setOperationCode(operationCode);

        CheckImeiRequestImpl checkImeiRequest = new CheckImeiRequestImpl(this.mapApplicationContext.getApplicationContextVersion().getVersion(), imei,
                requestedEquipmentInfo, extensionContainer);

        AsnOutputStream aos = new AsnOutputStream();
        checkImeiRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(checkImeiRequest.getTagClass());
        parameter.setPrimitive(checkImeiRequest.getIsPrimitive());
        parameter.setTag(checkImeiRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addCheckImeiResponse(long invokeId, EquipmentStatus equipmentStatus, UESBIIu bmuef,
            MAPExtensionContainer extensionContainer) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.equipmentMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3)) {
            throw new MAPException("Bad application context name for CheckImeiResponse: must be equipmentMngtContext_V1, V2 or V3");
        }

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.checkIMEI);
        resultLast.setOperationCode(operationCode);

        CheckImeiResponseImpl checkImeiResponse = new CheckImeiResponseImpl(this.mapApplicationContext.getApplicationContextVersion().getVersion(),
                equipmentStatus, bmuef, extensionContainer);
        AsnOutputStream aos = new AsnOutputStream();
        checkImeiResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(checkImeiResponse.getTagClass());
        parameter.setPrimitive(checkImeiResponse.getIsPrimitive());
        parameter.setTag(checkImeiResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addCheckImeiRequest_Huawei(IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
            MAPExtensionContainer extensionContainer, IMSI imsi) throws MAPException {

        return this.addCheckImeiRequest_Huawei(_Timer_Default, imei, requestedEquipmentInfo, extensionContainer, imsi);
    }

    @Override
    public Long addCheckImeiRequest_Huawei(long customInvokeTimeout, IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
            MAPExtensionContainer extensionContainer, IMSI imsi) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.equipmentMngtContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3)) {
            throw new MAPException("Bad application context name for CheckImeiRequest: must be equipmentMngtContext_V1, V2 or V3");
        }

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default) {
            invoke.setTimeout(getMediumTimer());
        } else {
            invoke.setTimeout(customInvokeTimeout);
        }

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.checkIMEI);
        invoke.setOperationCode(operationCode);

        CheckImeiRequestImpl checkImeiRequest = new CheckImeiRequestImpl(this.mapApplicationContext.getApplicationContextVersion().getVersion(), imei,
                requestedEquipmentInfo, extensionContainer);
        checkImeiRequest.setIMSI(imsi);

        AsnOutputStream aos = new AsnOutputStream();
        checkImeiRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(checkImeiRequest.getTagClass());
        parameter.setPrimitive(checkImeiRequest.getIsPrimitive());
        parameter.setTag(checkImeiRequest.getTag());
        parameter.setData(aos.toByteArray());
        parameter.setEncodingLength(checkImeiRequest.getEncodedLength());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addInsertSubscriberDataRequest(IMSI imsi, ISDNAddressString msisdn, Category category,
            SubscriberStatus subscriberStatus, ArrayList<ExtBearerServiceCode> bearerServiceList,
            ArrayList<ExtTeleserviceCode> teleserviceList, ArrayList<ExtSSInfo> provisionedSS, ODBData odbData,
            boolean roamingRestrictionDueToUnsupportedFeature, ArrayList<ZoneCode> regionalSubscriptionData,
            ArrayList<VoiceBroadcastData> vbsSubscriptionData, ArrayList<VoiceGroupCallData> vgcsSubscriptionData,
            VlrCamelSubscriptionInfo vlrCamelSubscriptionInfo) throws MAPException {

        return this.addInsertSubscriberDataRequest(_Timer_Default, imsi, msisdn, category, subscriberStatus, bearerServiceList,
                teleserviceList, provisionedSS, odbData, roamingRestrictionDueToUnsupportedFeature, regionalSubscriptionData,
                vbsSubscriptionData, vgcsSubscriptionData, vlrCamelSubscriptionInfo);
    }

    @Override
    public Long addInsertSubscriberDataRequest(long customInvokeTimeout, IMSI imsi, ISDNAddressString msisdn,
            Category category, SubscriberStatus subscriberStatus, ArrayList<ExtBearerServiceCode> bearerServiceList,
            ArrayList<ExtTeleserviceCode> teleserviceList, ArrayList<ExtSSInfo> provisionedSS, ODBData odbData,
            boolean roamingRestrictionDueToUnsupportedFeature, ArrayList<ZoneCode> regionalSubscriptionData,
            ArrayList<VoiceBroadcastData> vbsSubscriptionData, ArrayList<VoiceGroupCallData> vgcsSubscriptionData,
            VlrCamelSubscriptionInfo vlrCamelSubscriptionInfo) throws MAPException {

        return this.addInsertSubscriberDataRequest(customInvokeTimeout, imsi, msisdn, category, subscriberStatus,
                bearerServiceList, teleserviceList, provisionedSS, odbData, roamingRestrictionDueToUnsupportedFeature,
                regionalSubscriptionData, vbsSubscriptionData, vgcsSubscriptionData, vlrCamelSubscriptionInfo, null, null,
                null, false, null, null, false, null, null, null, null, null, null, null, null, false, null, null, false, null,
                null, null, false, false, null);
    }

    @Override
    public Long addInsertSubscriberDataRequest(IMSI imsi, ISDNAddressString msisdn, Category category,
            SubscriberStatus subscriberStatus, ArrayList<ExtBearerServiceCode> bearerServiceList,
            ArrayList<ExtTeleserviceCode> teleserviceList, ArrayList<ExtSSInfo> provisionedSS, ODBData odbData,
            boolean roamingRestrictionDueToUnsupportedFeature, ArrayList<ZoneCode> regionalSubscriptionData,
            ArrayList<VoiceBroadcastData> vbsSubscriptionData, ArrayList<VoiceGroupCallData> vgcsSubscriptionData,
            VlrCamelSubscriptionInfo vlrCamelSubscriptionInfo, MAPExtensionContainer extensionContainer,
            NAEAPreferredCI naeaPreferredCI, GPRSSubscriptionData gprsSubscriptionData,
            boolean roamingRestrictedInSgsnDueToUnsupportedFeature, NetworkAccessMode networkAccessMode,
            LSAInformation lsaInformation, boolean lmuIndicator, LCSInformation lcsInformation, Integer istAlertTimer,
            AgeIndicator superChargerSupportedInHLR, MCSSInfo mcSsInfo,
            CSAllocationRetentionPriority csAllocationRetentionPriority, SGSNCAMELSubscriptionInfo sgsnCamelSubscriptionInfo,
            ChargingCharacteristics chargingCharacteristics, AccessRestrictionData accessRestrictionData, Boolean icsIndicator,
            EPSSubscriptionData epsSubscriptionData, ArrayList<CSGSubscriptionData> csgSubscriptionDataList,
            boolean ueReachabilityRequestIndicator, ISDNAddressString sgsnNumber, DiameterIdentity mmeName,
            Long subscribedPeriodicRAUTAUtimer, boolean vplmnLIPAAllowed, Boolean mdtUserConsent,
            Long subscribedPeriodicLAUtimer) throws MAPException {

        return this.addInsertSubscriberDataRequest(_Timer_Default, imsi, msisdn, category, subscriberStatus, bearerServiceList,
                teleserviceList, provisionedSS, odbData, roamingRestrictionDueToUnsupportedFeature, regionalSubscriptionData,
                vbsSubscriptionData, vgcsSubscriptionData, vlrCamelSubscriptionInfo, extensionContainer, naeaPreferredCI,
                gprsSubscriptionData, roamingRestrictedInSgsnDueToUnsupportedFeature, networkAccessMode, lsaInformation,
                lmuIndicator, lcsInformation, istAlertTimer, superChargerSupportedInHLR, mcSsInfo,
                csAllocationRetentionPriority, sgsnCamelSubscriptionInfo, chargingCharacteristics, accessRestrictionData,
                icsIndicator, epsSubscriptionData, csgSubscriptionDataList, ueReachabilityRequestIndicator, sgsnNumber,
                mmeName, subscribedPeriodicRAUTAUtimer, vplmnLIPAAllowed, mdtUserConsent, subscribedPeriodicLAUtimer);
    }

    @Override
    public Long addInsertSubscriberDataRequest(long customInvokeTimeout, IMSI imsi, ISDNAddressString msisdn,
            Category category, SubscriberStatus subscriberStatus, ArrayList<ExtBearerServiceCode> bearerServiceList,
            ArrayList<ExtTeleserviceCode> teleserviceList, ArrayList<ExtSSInfo> provisionedSS, ODBData odbData,
            boolean roamingRestrictionDueToUnsupportedFeature, ArrayList<ZoneCode> regionalSubscriptionData,
            ArrayList<VoiceBroadcastData> vbsSubscriptionData, ArrayList<VoiceGroupCallData> vgcsSubscriptionData,
            VlrCamelSubscriptionInfo vlrCamelSubscriptionInfo, MAPExtensionContainer extensionContainer,
            NAEAPreferredCI naeaPreferredCI, GPRSSubscriptionData gprsSubscriptionData,
            boolean roamingRestrictedInSgsnDueToUnsupportedFeature, NetworkAccessMode networkAccessMode,
            LSAInformation lsaInformation, boolean lmuIndicator, LCSInformation lcsInformation, Integer istAlertTimer,
            AgeIndicator superChargerSupportedInHLR, MCSSInfo mcSsInfo,
            CSAllocationRetentionPriority csAllocationRetentionPriority, SGSNCAMELSubscriptionInfo sgsnCamelSubscriptionInfo,
            ChargingCharacteristics chargingCharacteristics, AccessRestrictionData accessRestrictionData, Boolean icsIndicator,
            EPSSubscriptionData epsSubscriptionData, ArrayList<CSGSubscriptionData> csgSubscriptionDataList,
            boolean ueReachabilityRequestIndicator, ISDNAddressString sgsnNumber, DiameterIdentity mmeName,
            Long subscribedPeriodicRAUTAUtimer, boolean vplmnLIPAAllowed, Boolean mdtUserConsent,
            Long subscribedPeriodicLAUtimer) throws MAPException {

        boolean isSubscriberDataMngtContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.subscriberDataMngtContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext
                        .getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isSubscriberDataMngtContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext
                        .getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;
        if (isSubscriberDataMngtContext == false && isNetworkLocUpContext == false && isGprsLocationUpdateContext == false)
            throw new MAPException("Bad application context name for InsertSubscriberDataRequest: must be networkLocUpContext_V1, V2 or V3 or "
                            + "subscriberDataMngtContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default) {
            invoke.setTimeout(getMediumTimer());
        } else {
            invoke.setTimeout(customInvokeTimeout);
        }

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.insertSubscriberData);
        invoke.setOperationCode(operationCode);

        InsertSubscriberDataRequestImpl insertSubscriberDataRequest = new InsertSubscriberDataRequestImpl(this.mapApplicationContext.getApplicationContextVersion()
                .getVersion(), imsi, msisdn, category, subscriberStatus, bearerServiceList, teleserviceList, provisionedSS,
                odbData, roamingRestrictionDueToUnsupportedFeature, regionalSubscriptionData, vbsSubscriptionData,
                vgcsSubscriptionData, vlrCamelSubscriptionInfo, extensionContainer, naeaPreferredCI, gprsSubscriptionData,
                roamingRestrictedInSgsnDueToUnsupportedFeature, networkAccessMode, lsaInformation, lmuIndicator,
                lcsInformation, istAlertTimer, superChargerSupportedInHLR, mcSsInfo, csAllocationRetentionPriority,
                sgsnCamelSubscriptionInfo, chargingCharacteristics, accessRestrictionData, icsIndicator, epsSubscriptionData,
                csgSubscriptionDataList, ueReachabilityRequestIndicator, sgsnNumber, mmeName, subscribedPeriodicRAUTAUtimer,
                vplmnLIPAAllowed, mdtUserConsent, subscribedPeriodicLAUtimer);

        AsnOutputStream aos = new AsnOutputStream();
        insertSubscriberDataRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(insertSubscriberDataRequest.getTagClass());
        parameter.setPrimitive(insertSubscriberDataRequest.getIsPrimitive());
        parameter.setTag(insertSubscriberDataRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addInsertSubscriberDataResponse(long invokeId, ArrayList<ExtTeleserviceCode> teleserviceList,
            ArrayList<ExtBearerServiceCode> bearerServiceList, ArrayList<SSCode> ssList, ODBGeneralData odbGeneralData,
            RegionalSubscriptionResponse regionalSubscriptionResponse) throws MAPException {

        this.addInsertSubscriberDataResponse(invokeId, teleserviceList, bearerServiceList, ssList, odbGeneralData,
                regionalSubscriptionResponse, null, null, null, null);
    }

    @Override
    public void addInsertSubscriberDataResponse(long invokeId, ArrayList<ExtTeleserviceCode> teleserviceList,
            ArrayList<ExtBearerServiceCode> bearerServiceList, ArrayList<SSCode> ssList, ODBGeneralData odbGeneralData,
            RegionalSubscriptionResponse regionalSubscriptionResponse, SupportedCamelPhases supportedCamelPhases,
            MAPExtensionContainer extensionContainer, OfferedCamel4CSIs offeredCamel4CSIs, SupportedFeatures supportedFeatures)
            throws MAPException {

        boolean isSubscriberDataMngtContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.subscriberDataMngtContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext
                        .getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isSubscriberDataMngtContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext
                        .getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;
        if (isSubscriberDataMngtContext == false && isNetworkLocUpContext == false && isGprsLocationUpdateContext == false)
            throw new MAPException("Bad application context name for InsertSubscriberDataResponse: must be networkLocUpContext_V1, V2 or V3 or "
                            + "subscriberDataMngtContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.insertSubscriberData);
        resultLast.setOperationCode(operationCode);

        if ((teleserviceList != null || bearerServiceList != null || ssList != null || odbGeneralData != null || regionalSubscriptionResponse != null
                || supportedCamelPhases != null || extensionContainer != null || offeredCamel4CSIs != null || supportedFeatures != null)
                && this.mapApplicationContext.getApplicationContextVersion().getVersion() != 1) {
            InsertSubscriberDataResponseImpl insertSubscriberDataResponse = new InsertSubscriberDataResponseImpl(this.mapApplicationContext.getApplicationContextVersion().getVersion(),
                    teleserviceList, bearerServiceList, ssList, odbGeneralData, regionalSubscriptionResponse, supportedCamelPhases, extensionContainer,
                    offeredCamel4CSIs, supportedFeatures);
            AsnOutputStream aos = new AsnOutputStream();
            insertSubscriberDataResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(insertSubscriberDataResponse.getTagClass());
            parameter.setPrimitive(insertSubscriberDataResponse.getIsPrimitive());
            parameter.setTag(insertSubscriberDataResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addDeleteSubscriberDataRequest(IMSI imsi, ArrayList<ExtBasicServiceCode> basicServiceList, ArrayList<SSCode> ssList,
            boolean roamingRestrictionDueToUnsupportedFeature, ZoneCode regionalSubscriptionIdentifier, boolean vbsGroupIndication,
            boolean vgcsGroupIndication, boolean camelSubscriptionInfoWithdraw, MAPExtensionContainer extensionContainer,
            GPRSSubscriptionDataWithdraw gprsSubscriptionDataWithdraw, boolean roamingRestrictedInSgsnDueToUnsuppportedFeature,
            LSAInformationWithdraw lsaInformationWithdraw, boolean gmlcListWithdraw, boolean istInformationWithdraw, SpecificCSIWithdraw specificCSIWithdraw,
            boolean chargingCharacteristicsWithdraw, boolean stnSrWithdraw, EPSSubscriptionDataWithdraw epsSubscriptionDataWithdraw,
            boolean apnOiReplacementWithdraw, boolean csgSubscriptionDeleted) throws MAPException {

        return this.addDeleteSubscriberDataRequest(_Timer_Default, imsi, basicServiceList, ssList, roamingRestrictionDueToUnsupportedFeature,
                regionalSubscriptionIdentifier, vbsGroupIndication, vgcsGroupIndication, camelSubscriptionInfoWithdraw, extensionContainer,
                gprsSubscriptionDataWithdraw, roamingRestrictedInSgsnDueToUnsuppportedFeature, lsaInformationWithdraw, gmlcListWithdraw,
                istInformationWithdraw, specificCSIWithdraw, chargingCharacteristicsWithdraw, stnSrWithdraw, epsSubscriptionDataWithdraw,
                apnOiReplacementWithdraw, csgSubscriptionDeleted);
    }

    @Override
    public Long addDeleteSubscriberDataRequest(long customInvokeTimeout, IMSI imsi, ArrayList<ExtBasicServiceCode> basicServiceList, ArrayList<SSCode> ssList,
            boolean roamingRestrictionDueToUnsupportedFeature, ZoneCode regionalSubscriptionIdentifier, boolean vbsGroupIndication,
            boolean vgcsGroupIndication, boolean camelSubscriptionInfoWithdraw, MAPExtensionContainer extensionContainer,
            GPRSSubscriptionDataWithdraw gprsSubscriptionDataWithdraw, boolean roamingRestrictedInSgsnDueToUnsuppportedFeature,
            LSAInformationWithdraw lsaInformationWithdraw, boolean gmlcListWithdraw, boolean istInformationWithdraw, SpecificCSIWithdraw specificCSIWithdraw,
            boolean chargingCharacteristicsWithdraw, boolean stnSrWithdraw, EPSSubscriptionDataWithdraw epsSubscriptionDataWithdraw,
            boolean apnOiReplacementWithdraw, boolean csgSubscriptionDeleted) throws MAPException {

        boolean isSubscriberDataMngtContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.subscriberDataMngtContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isSubscriberDataMngtContext = true;
        if (isSubscriberDataMngtContext == false)
            throw new MAPException("Bad application context name for DeleteSubscriberDataRequest: must be subscriberDataMngtContext_V1, V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default) {
            invoke.setTimeout(getMediumTimer());
        } else {
            invoke.setTimeout(customInvokeTimeout);
        }

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.deleteSubscriberData);
        invoke.setOperationCode(operationCode);

        DeleteSubscriberDataRequestImpl deleteSubscriberDataRequest = new DeleteSubscriberDataRequestImpl(imsi, basicServiceList, ssList, roamingRestrictionDueToUnsupportedFeature,
                regionalSubscriptionIdentifier, vbsGroupIndication, vgcsGroupIndication, camelSubscriptionInfoWithdraw, extensionContainer,
                gprsSubscriptionDataWithdraw, roamingRestrictedInSgsnDueToUnsuppportedFeature, lsaInformationWithdraw, gmlcListWithdraw,
                istInformationWithdraw, specificCSIWithdraw, chargingCharacteristicsWithdraw, stnSrWithdraw, epsSubscriptionDataWithdraw,
                apnOiReplacementWithdraw, csgSubscriptionDeleted);

        AsnOutputStream aos = new AsnOutputStream();
        deleteSubscriberDataRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(deleteSubscriberDataRequest.getTagClass());
        parameter.setPrimitive(deleteSubscriberDataRequest.getIsPrimitive());
        parameter.setTag(deleteSubscriberDataRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addDeleteSubscriberDataResponse(long invokeId, RegionalSubscriptionResponse regionalSubscriptionResponse,
            MAPExtensionContainer extensionContainer) throws MAPException {

        boolean isSubscriberDataMngtContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.subscriberDataMngtContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isSubscriberDataMngtContext = true;
        if (isSubscriberDataMngtContext == false)
            throw new MAPException("Bad application context name for DeleteSubscriberDataResponse: must be subscriberDataMngtContext_V1, V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.deleteSubscriberData);
        resultLast.setOperationCode(operationCode);

        if ((regionalSubscriptionResponse != null || extensionContainer != null) && this.mapApplicationContext.getApplicationContextVersion().getVersion() != 1) {
            DeleteSubscriberDataResponseImpl deleteSubscriberDataResponse = new DeleteSubscriberDataResponseImpl(regionalSubscriptionResponse, extensionContainer);
            AsnOutputStream aos = new AsnOutputStream();
            deleteSubscriberDataResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(deleteSubscriberDataResponse.getTagClass());
            parameter.setPrimitive(deleteSubscriberDataResponse.getIsPrimitive());
            parameter.setTag(deleteSubscriberDataResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addCancelLocationRequest(IMSI imsi, IMSIWithLMSI imsiWithLmsi, CancellationType cancellationType,
            MAPExtensionContainer extensionContainer, TypeOfUpdate typeOfUpdate, boolean mtrfSupportedAndAuthorized,
            boolean mtrfSupportedAndNotAuthorized, ISDNAddressString newMSCNumber, ISDNAddressString newVLRNumber, LMSI newLmsi)
            throws MAPException {

        return this.addCancelLocationRequest(_Timer_Default, imsi, imsiWithLmsi, cancellationType, extensionContainer,
                typeOfUpdate, mtrfSupportedAndAuthorized, mtrfSupportedAndNotAuthorized, newMSCNumber, newVLRNumber, newLmsi);
    }

    @Override
    public Long addCancelLocationRequest(int customInvokeTimeout, IMSI imsi, IMSIWithLMSI imsiWithLmsi,
            CancellationType cancellationType, MAPExtensionContainer extensionContainer, TypeOfUpdate typeOfUpdate,
            boolean mtrfSupportedAndAuthorized, boolean mtrfSupportedAndNotAuthorized, ISDNAddressString newMSCNumber,
            ISDNAddressString newVLRNumber, LMSI newLmsi) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationCancellationContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for CancelLocationRequest: must be locationCancellationContext_V1, V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.cancelLocation);
        invoke.setOperationCode(operationCode);

        CancelLocationRequestImpl cancelLocationRequest = new CancelLocationRequestImpl(imsi, imsiWithLmsi, cancellationType, extensionContainer,
                typeOfUpdate, mtrfSupportedAndAuthorized, mtrfSupportedAndNotAuthorized, newMSCNumber, newVLRNumber, newLmsi,
                this.mapApplicationContext.getApplicationContextVersion().getVersion());

        AsnOutputStream aos = new AsnOutputStream();
        cancelLocationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(cancelLocationRequest.getTagClass());
        parameter.setPrimitive(cancelLocationRequest.getIsPrimitive());
        parameter.setTag(cancelLocationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;

    }

    @Override
    public void addCancelLocationResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationCancellationContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1
                        && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for CancelLocationResponse: must be locationCancellationContext_V1, V2 or V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.cancelLocation);
        resultLast.setOperationCode(operationCode);

        if (extensionContainer != null) {
            CancelLocationResponseImpl cancelLocationResponse = new CancelLocationResponseImpl(extensionContainer);

            AsnOutputStream aos = new AsnOutputStream();
            cancelLocationResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(cancelLocationResponse.getTagClass());
            parameter.setPrimitive(cancelLocationResponse.getIsPrimitive());
            parameter.setTag(cancelLocationResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);

    }

    @Override
    public Long addSendIdentificationRequest(TMSI tmsi, Integer numberOfRequestedVectors, boolean segmentationProhibited,
            MAPExtensionContainer extensionContainer, ISDNAddressString mscNumber, LAIFixedLength previousLAI,
            Integer hopCounter, boolean mtRoamingForwardingSupported, ISDNAddressString newVLRNumber, LMSI lmsi)
            throws MAPException {
        return this.addSendIdentificationRequest(_Timer_Default, tmsi, numberOfRequestedVectors, segmentationProhibited,
                extensionContainer, mscNumber, previousLAI, hopCounter, mtRoamingForwardingSupported, newVLRNumber, lmsi);
    }

    @Override
    public Long addSendIdentificationRequest(int customInvokeTimeout, TMSI tmsi, Integer numberOfRequestedVectors,
            boolean segmentationProhibited, MAPExtensionContainer extensionContainer, ISDNAddressString mscNumber,
            LAIFixedLength previousLAI, Integer hopCounter, boolean mtRoamingForwardingSupported,
            ISDNAddressString newVLRNumber, LMSI lmsi) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.interVlrInfoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for SendIdentificationRequest: must be interVlrInfoRetrievalContext_V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendIdentification);
        invoke.setOperationCode(operationCode);

        SendIdentificationRequestImpl sendIdentificationRequest = new SendIdentificationRequestImpl(tmsi, numberOfRequestedVectors,
                segmentationProhibited, extensionContainer, mscNumber, previousLAI, hopCounter, mtRoamingForwardingSupported,
                newVLRNumber, lmsi, this.mapApplicationContext.getApplicationContextVersion().getVersion());

        AsnOutputStream aos = new AsnOutputStream();
        sendIdentificationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendIdentificationRequest.getTagClass());
        parameter.setPrimitive(sendIdentificationRequest.getIsPrimitive());
        parameter.setTag(sendIdentificationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addSendIdentificationResponse(long invokeId, IMSI imsi, AuthenticationSetList authenticationSetList,
            CurrentSecurityContext currentSecurityContext, MAPExtensionContainer extensionContainer) throws MAPException {
        doAddSendIdentificationResponse(false, invokeId, imsi, authenticationSetList, currentSecurityContext,
                extensionContainer);
    }

    @Override
    public void addSendIdentificationResponse_NonLast(long invokeId, IMSI imsi, AuthenticationSetList authenticationSetList,
            CurrentSecurityContext currentSecurityContext, MAPExtensionContainer extensionContainer) throws MAPException {
        doAddSendIdentificationResponse(true, invokeId, imsi, authenticationSetList, currentSecurityContext,
                extensionContainer);
    }

    protected void doAddSendIdentificationResponse(boolean nonLast, long invokeId, IMSI imsi,
            AuthenticationSetList authenticationSetList, CurrentSecurityContext currentSecurityContext,
            MAPExtensionContainer extensionContainer) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.interVlrInfoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException(
                    "Bad application context name for AddSendIdentificationResponse: must be interVlrInfoRetrievalContext_V2 or V3");
        if (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2 && nonLast)
            throw new MAPException(
                    "Bad application context name for AddSendIdentificationResponse: must be interVlrInfoRetrievalContext_V2 for nonLast operation");

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendIdentification);

        SendIdentificationResponseImpl sendIdentificationResponse = new SendIdentificationResponseImpl(imsi, authenticationSetList,
                currentSecurityContext, extensionContainer, this.mapApplicationContext.getApplicationContextVersion().getVersion());
        AsnOutputStream aos = new AsnOutputStream();
        sendIdentificationResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendIdentificationResponse.getTagClass());
        parameter.setPrimitive(sendIdentificationResponse.getIsPrimitive());
        parameter.setTag(sendIdentificationResponse.getTag());
        parameter.setData(aos.toByteArray());

        if (nonLast) {
            ReturnResult resultLastNonLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultRequest();

            resultLastNonLast.setInvokeId(invokeId);
            resultLastNonLast.setOperationCode(operationCode);
            resultLastNonLast.setParameter(parameter);

            this.sendReturnResultComponent(resultLastNonLast);
        } else {
            ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

            resultLast.setInvokeId(invokeId);
            resultLast.setOperationCode(operationCode);
            resultLast.setParameter(parameter);

            this.sendReturnResultLastComponent(resultLast);
        }
    }

    @Override
    public Long addUpdateGprsLocationRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString sgsnNumber,
            GSNAddress sgsnAddress, MAPExtensionContainer extensionContainer, SGSNCapability sgsnCapability,
            boolean informPreviousNetworkEntity, boolean psLCSNotSupportedByUE, GSNAddress vGmlcAddress, ADDInfo addInfo,
            EPSInfo epsInfo, boolean servingNodeTypeIndicator, boolean skipSubscriberDataUpdate, UsedRATType usedRATType,
            boolean gprsSubscriptionDataNotNeeded, boolean nodeTypeIndicator, boolean areaRestricted,
            boolean ueReachableIndicator, boolean epsSubscriptionDataNotNeeded, UESRVCCCapability uesrvccCapability)
            throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.gprsLocationUpdateContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException("Bad application context name for UpdateGprsLocationRequest: must be gprsLocationUpdateContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.updateGprsLocation);
        invoke.setOperationCode(operationCode);

        UpdateGprsLocationRequestImpl updateGprsLocationRequest = new UpdateGprsLocationRequestImpl(imsi, sgsnNumber, sgsnAddress,
                extensionContainer, sgsnCapability, informPreviousNetworkEntity, psLCSNotSupportedByUE, vGmlcAddress, addInfo,
                epsInfo, servingNodeTypeIndicator, skipSubscriberDataUpdate, usedRATType, gprsSubscriptionDataNotNeeded,
                nodeTypeIndicator, areaRestricted, ueReachableIndicator, epsSubscriptionDataNotNeeded, uesrvccCapability,
                this.mapApplicationContext.getApplicationContextVersion().getVersion());

        AsnOutputStream aos = new AsnOutputStream();
        updateGprsLocationRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(updateGprsLocationRequest.getTagClass());
        parameter.setPrimitive(updateGprsLocationRequest.getIsPrimitive());
        parameter.setTag(updateGprsLocationRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;

    }

    @Override
    public Long addUpdateGprsLocationRequest(IMSI imsi, ISDNAddressString sgsnNumber, GSNAddress sgsnAddress,
            MAPExtensionContainer extensionContainer, SGSNCapability sgsnCapability, boolean informPreviousNetworkEntity,
            boolean psLCSNotSupportedByUE, GSNAddress vGmlcAddress, ADDInfo addInfo, EPSInfo epsInfo,
            boolean servingNodeTypeIndicator, boolean skipSubscriberDataUpdate, UsedRATType usedRATType,
            boolean gprsSubscriptionDataNotNeeded, boolean nodeTypeIndicator, boolean areaRestricted,
            boolean ueReachableIndicator, boolean epsSubscriptionDataNotNeeded, UESRVCCCapability uesrvccCapability)
            throws MAPException {
        return addUpdateGprsLocationRequest(_Timer_Default, imsi, sgsnNumber, sgsnAddress, extensionContainer, sgsnCapability,
                informPreviousNetworkEntity, psLCSNotSupportedByUE, vGmlcAddress, addInfo, epsInfo, servingNodeTypeIndicator,
                skipSubscriberDataUpdate, usedRATType, gprsSubscriptionDataNotNeeded, nodeTypeIndicator, areaRestricted,
                ueReachableIndicator, epsSubscriptionDataNotNeeded, uesrvccCapability);
    }

    @Override
    public void addUpdateGprsLocationResponse(long invokeId, ISDNAddressString hlrNumber,
            MAPExtensionContainer extensionContainer, boolean addCapability, boolean sgsnMmeSeparationSupported)
            throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.gprsLocationUpdateContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3))
            throw new MAPException(
                    "Bad application context name for UpdateGprsLocationResponse: must be gprsLocationUpdateContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.updateGprsLocation);
        resultLast.setOperationCode(operationCode);

        UpdateGprsLocationResponseImpl updateGprsLocationResponse = new UpdateGprsLocationResponseImpl(hlrNumber, extensionContainer, addCapability,
                sgsnMmeSeparationSupported);

        AsnOutputStream aos = new AsnOutputStream();
        updateGprsLocationResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(updateGprsLocationResponse.getTagClass());
        parameter.setPrimitive(updateGprsLocationResponse.getIsPrimitive());
        parameter.setTag(updateGprsLocationResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);

    }

    @Override
    public Long addPurgeMSRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString vlrNumber,
            ISDNAddressString sgsnNumber, MAPExtensionContainer extensionContainer) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.msPurgingContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
                && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)))
            throw new MAPException(
                    "Bad application context name for PurgeMSRequest: must be msPurgingContext_V2 or msPurgingContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.purgeMS);
        invoke.setOperationCode(operationCode);

        PurgeMSRequestImpl purgeMSRequest = new PurgeMSRequestImpl(imsi, vlrNumber, sgsnNumber, extensionContainer, this.mapApplicationContext
                .getApplicationContextVersion().getVersion());

        AsnOutputStream aos = new AsnOutputStream();
        purgeMSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(purgeMSRequest.getTagClass());
        parameter.setPrimitive(purgeMSRequest.getIsPrimitive());
        parameter.setTag(purgeMSRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;

    }

    @Override
    public Long addPurgeMSRequest(IMSI imsi, ISDNAddressString vlrNumber, ISDNAddressString sgsnNumber,
            MAPExtensionContainer extensionContainer) throws MAPException {
        return addPurgeMSRequest(_Timer_Default, imsi, vlrNumber, sgsnNumber, extensionContainer);
    }

    @Override
    public void addPurgeMSResponse(long invokeId, boolean freezeTMSI, boolean freezePTMSI,
            MAPExtensionContainer extensionContainer, boolean freezeMTMSI) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.msPurgingContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3) && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)))
            throw new MAPException("Bad application context name for PurgeMSResponse: must be msPurgingContext_V3 OR  msPurgingContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.purgeMS);
        resultLast.setOperationCode(operationCode);

        PurgeMSResponseImpl purgeMSResponse = new PurgeMSResponseImpl(freezeTMSI, freezePTMSI, extensionContainer, freezeMTMSI);

        if (this.mapApplicationContext.getApplicationContextVersion().getVersion() >= 3 && (freezeTMSI || freezePTMSI || extensionContainer != null || freezeMTMSI)) {
            AsnOutputStream aos = new AsnOutputStream();
            purgeMSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(purgeMSResponse.getTagClass());
            parameter.setPrimitive(purgeMSResponse.getIsPrimitive());
            parameter.setTag(purgeMSResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addResetRequest(NetworkResource networkResource, ISDNAddressString hlrNumber, ArrayList<IMSI> hlrList) throws MAPException {
        return addResetRequest(_Timer_Default, networkResource, hlrNumber, hlrList);
    }

    @Override
    public Long addResetRequest(int customInvokeTimeout, NetworkResource networkResource, ISDNAddressString hlrNumber, ArrayList<IMSI> hlrList)
            throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.resetContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2) && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version1)))
            throw new MAPException("Bad application context name for ResetRequest: must be resetContext_V1 or V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.reset);
        invoke.setOperationCode(operationCode);

        int version = this.mapApplicationContext.getApplicationContextVersion().getVersion();
        ResetRequestImpl resetRequest = new ResetRequestImpl(networkResource, hlrNumber, hlrList, version);

        AsnOutputStream aos = new AsnOutputStream();
        resetRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(resetRequest.getTagClass());
        parameter.setPrimitive(resetRequest.getIsPrimitive());
        parameter.setTag(resetRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addForwardCheckSSIndicationRequest() throws MAPException {
        return addForwardCheckSSIndicationRequest(_Timer_Default);
    }

    @Override
    public Long addForwardCheckSSIndicationRequest(int customInvokeTimeout) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkLocUpContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
                        && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2) && (this.mapApplicationContext
                        .getApplicationContextVersion() != MAPApplicationContextVersion.version1)))
            throw new MAPException("Bad application context name for ForwardCheckSSIndicationRequest: must be networkLocUpContext_V1, V2 or V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class4);
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getShortTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.forwardCheckSsIndication);
        invoke.setOperationCode(operationCode);

        ForwardCheckSSIndicationRequestImpl forwardCheckSSIndicationRequest = new ForwardCheckSSIndicationRequestImpl();

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public Long addRestoreDataRequest(IMSI imsi, LMSI lmsi, VLRCapability vlrCapability, MAPExtensionContainer extensionContainer, boolean restorationIndicator)
            throws MAPException {
        return addRestoreDataRequest(_Timer_Default, imsi, lmsi, vlrCapability, extensionContainer, restorationIndicator);
    }

    @Override
    public Long addRestoreDataRequest(int customInvokeTimeout, IMSI imsi, LMSI lmsi, VLRCapability vlrCapability, MAPExtensionContainer extensionContainer,
            boolean restorationIndicator) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkLocUpContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3) && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)))
            throw new MAPException("Bad application context name for RestoreDataRequest: must be networkLocUpContext_V2 or networkLocUpContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.restoreData);
        invoke.setOperationCode(operationCode);

        RestoreDataRequestImpl restoreDataRequest = new RestoreDataRequestImpl(imsi, lmsi, vlrCapability, extensionContainer, restorationIndicator);

        AsnOutputStream aos = new AsnOutputStream();
        restoreDataRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(restoreDataRequest.getTagClass());
        parameter.setPrimitive(restoreDataRequest.getIsPrimitive());
        parameter.setTag(restoreDataRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addRestoreDataResponse(long invokeId, ISDNAddressString hlrNumber, boolean msNotReachable, MAPExtensionContainer extensionContainer)
            throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkLocUpContext)
                || ((this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3) && (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)))
            throw new MAPException("Bad application context name for RestoreDataResponse: must be networkLocUpContext_V2 or networkLocUpContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.restoreData);
        resultLast.setOperationCode(operationCode);

        RestoreDataResponseImpl restoreDataResponse = new RestoreDataResponseImpl(hlrNumber, msNotReachable, extensionContainer);

        AsnOutputStream aos = new AsnOutputStream();
        restoreDataResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(restoreDataResponse.getTagClass());
        parameter.setPrimitive(restoreDataResponse.getIsPrimitive());
        parameter.setTag(restoreDataResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addActivateTraceModeRequest(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {
        return this.addActivateTraceModeRequest(_Timer_Default, imsi, traceReference, traceType, omcId, extensionContainer, traceReference2, traceDepthList,
                traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
    }

    @Override
    public Long addActivateTraceModeRequest(int customInvokeTimeout, IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {

        boolean isTracingContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.tracingContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isTracingContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;

        if (!isTracingContext && !isNetworkLocUpContext && !isGprsLocationUpdateContext)
            throw new MAPException(
                    "Bad application context name for activateTraceModeRequest: must be tracingContext_V1, V2 or V3, networkLocUpContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.activateTraceMode);
        invoke.setOperationCode(operationCode);

        ActivateTraceModeRequestImpl_Mobility activateTraceModeRequestImplMobility = new ActivateTraceModeRequestImpl_Mobility(imsi, traceReference, traceType, omcId, extensionContainer, traceReference2,
                traceDepthList, traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
        AsnOutputStream aos = new AsnOutputStream();
        activateTraceModeRequestImplMobility.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(activateTraceModeRequestImplMobility.getTagClass());
        parameter.setPrimitive(activateTraceModeRequestImplMobility.getIsPrimitive());
        parameter.setTag(activateTraceModeRequestImplMobility.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addActivateTraceModeResponse(long invokeId, MAPExtensionContainer extensionContainer, boolean traceSupportIndicator) throws MAPException {
        boolean isTracingContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.tracingContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isTracingContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;

        if (!isTracingContext && !isNetworkLocUpContext && !isGprsLocationUpdateContext)
            throw new MAPException("Bad application context name for activateTraceModeResponse: must be tracingContext_V1, V2 or V3, networkLocUpContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        if ((traceSupportIndicator || extensionContainer != null) && this.mapApplicationContext.getApplicationContextVersion().getVersion() >= 3) {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.activateTraceMode);
            resultLast.setOperationCode(operationCode);

            ActivateTraceModeResponseImpl_Mobility activateTraceModeResponseImplMobility = new ActivateTraceModeResponseImpl_Mobility(extensionContainer, traceSupportIndicator);
            AsnOutputStream aos = new AsnOutputStream();
            activateTraceModeResponseImplMobility.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(activateTraceModeResponseImplMobility.getTagClass());
            parameter.setPrimitive(activateTraceModeResponseImplMobility.getIsPrimitive());
            parameter.setTag(activateTraceModeResponseImplMobility.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

}
