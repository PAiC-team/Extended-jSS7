package org.restcomm.protocols.ss7.map.service.lsm;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaEventInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPriority;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPrivacyCheck;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationType;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.SLRArgExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.SupportedGADShapes;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 * @author amit bhayani
 *
 */
public class MAPDialogLsmImpl extends MAPDialogImpl implements MAPDialogLsm {

    /**
     * @param mapApplicationContext
     * @param tcapDialog
     * @param mapProviderImpl
     * @param mapService
     * @param origReference
     * @param destReference
     */
    protected MAPDialogLsmImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog, MAPProviderImpl mapProviderImpl,
            MAPServiceBase mapService, AddressString origReference, AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addProvideSubscriberLocationRequest
     * (org.restcomm.protocols.ss7.map.api.service.lsm.LocationType,
     * org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString,
     * org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID, java.lang.Boolean,
     * org.restcomm.protocols.ss7.map.api.primitives.IMSI, org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString,
     * org.restcomm.protocols.ss7.map.api.primitives.LMSI, org.restcomm.protocols.ss7.map.api.primitives.IMEI,
     * java.lang.Integer, org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS,
     * org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer, java.util.BitSet, java.lang.Byte,
     * java.lang.Integer, org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword,
     * org.restcomm.protocols.ss7.map.api.service.lsm.LCSPrivacyCheck,
     * org.restcomm.protocols.ss7.map.api.service.lsm.AreaEventInfo, byte[])
     */
    public Long addProvideSubscriberLocationRequest(LocationType locationType, ISDNAddressString mlcNumber,
            LCSClientID lcsClientID, boolean privacyOverride, IMSI imsi, ISDNAddressString msisdn, LMSI lmsi, IMEI imei,
            LCSPriority lcsPriority, LCSQoS lcsQoS, MAPExtensionContainer extensionContainer,
            SupportedGADShapes supportedGADShapes, Integer lcsReferenceNumber, Integer lcsServiceTypeID,
            LCSCodeword lcsCodeword, LCSPrivacyCheck lcsPrivacyCheck, AreaEventInfo areaEventInfo, GSNAddress hgmlcAddress,
            boolean moLrShortCircuitIndicator, PeriodicLDRInfo periodicLDRInfo, ReportingPLMNList reportingPLMNList)
            throws MAPException {
        return this.addProvideSubscriberLocationRequest(_Timer_Default, locationType, mlcNumber, lcsClientID, privacyOverride,
                imsi, msisdn, lmsi, imei, lcsPriority, lcsQoS, extensionContainer, supportedGADShapes, lcsReferenceNumber,
                lcsServiceTypeID, lcsCodeword, lcsPrivacyCheck, areaEventInfo, hgmlcAddress, moLrShortCircuitIndicator,
                periodicLDRInfo, reportingPLMNList);
    }

    public Long addProvideSubscriberLocationRequest(int customInvokeTimeout, LocationType locationType,
            ISDNAddressString mlcNumber, LCSClientID lcsClientID, boolean privacyOverride, IMSI imsi, ISDNAddressString msisdn,
            LMSI lmsi, IMEI imei, LCSPriority lcsPriority, LCSQoS lcsQoS, MAPExtensionContainer extensionContainer,
            SupportedGADShapes supportedGADShapes, Integer lcsReferenceNumber, Integer lcsServiceTypeID,
            LCSCodeword lcsCodeword, LCSPrivacyCheck lcsPrivacyCheck, AreaEventInfo areaEventInfo, GSNAddress hgmlcAddress,
            boolean moLrShortCircuitIndicator, PeriodicLDRInfo periodicLDRInfo, ReportingPLMNList reportingPLMNList)
            throws MAPException {

        if (locationType == null || mlcNumber == null) {
            throw new MAPException(
                    "addProvideSubscriberLocationRequest: Mandatory parameters locationType or mlcNumber cannot be null");
        }

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcEnquiryContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "addProvideSubscriberLocationRequest: Bad application context name for addProvideSubscriberLocationRequest: must be locationSvcEnquiryContext_V3");

        try {
            Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
            if (customInvokeTimeout == _Timer_Default)
                invoke.setTimeout(getLongTimer());
            else
                invoke.setTimeout(customInvokeTimeout);

            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.provideSubscriberLocation);
            invoke.setOperationCode(operationCode);

            ProvideSubscriberLocationRequestImpl provideSubscriberLocationRequest = new ProvideSubscriberLocationRequestImpl(locationType, mlcNumber,
                    lcsClientID, privacyOverride, imsi, msisdn, lmsi, imei, lcsPriority, lcsQoS, extensionContainer,
                    supportedGADShapes, lcsReferenceNumber, lcsServiceTypeID, lcsCodeword, lcsPrivacyCheck, areaEventInfo,
                    hgmlcAddress, moLrShortCircuitIndicator, periodicLDRInfo, reportingPLMNList);

            AsnOutputStream asnOs = new AsnOutputStream();
            provideSubscriberLocationRequest.encodeData(asnOs);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(provideSubscriberLocationRequest.getTagClass());
            parameter.setPrimitive(provideSubscriberLocationRequest.getIsPrimitive());
            parameter.setTag(provideSubscriberLocationRequest.getTag());
            parameter.setData(asnOs.toByteArray());

            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addProvideSubscriberLocationResponse(long, byte[],
     * byte[], byte[], java.lang.Integer, byte[], org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer,
     * java.lang.Boolean, org.restcomm.protocols.ss7.map.api.service.lsm. CellGlobalIdOrServiceAreaIdOrLAI, java.lang.Boolean,
     * org.mobicents.protocols .ss7.map.api.service.lsm.AccuracyFulfilmentIndicator)
     */
    public void addProvideSubscriberLocationResponse(long invokeId, ExtGeographicalInformation locationEstimate,
            PositioningDataInformation geranPositioningData, UtranPositioningDataInfo utranPositioningData,
            Integer ageOfLocationEstimate, AddGeographicalInformation additionalLocationEstimate,
            MAPExtensionContainer extensionContainer, boolean deferredMTLRResponseIndicator,
            CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI, boolean saiPresent,
            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, VelocityEstimate velocityEstimate,
            boolean moLrShortCircuitIndicator, GeranGANSSpositioningData geranGANSSpositioningData,
            UtranGANSSpositioningData utranGANSSpositioningData, ServingNodeAddress targetServingNodeForHandover)
            throws MAPException {

        if (locationEstimate == null) {
            throw new MAPException("addProvideSubscriberLocationResponse: Mandatory parameters locationEstimate cannot be null");
        }

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcEnquiryContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "Bad application context name for addProvideSubscriberLocationResponse: must be locationSvcEnquiryContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.provideSubscriberLocation);
        resultLast.setOperationCode(operationCode);

        ProvideSubscriberLocationResponseImpl provideSubscriberLocationResponseIndication = new ProvideSubscriberLocationResponseImpl(locationEstimate,
                geranPositioningData, utranPositioningData, ageOfLocationEstimate, additionalLocationEstimate,
                extensionContainer, deferredMTLRResponseIndicator, cellGlobalIdOrServiceAreaIdOrLAI, saiPresent,
                accuracyFulfilmentIndicator, velocityEstimate, moLrShortCircuitIndicator, geranGANSSpositioningData,
                utranGANSSpositioningData, targetServingNodeForHandover);

        AsnOutputStream asnOs = new AsnOutputStream();
        provideSubscriberLocationResponseIndication.encodeData(asnOs);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(provideSubscriberLocationResponseIndication.getTagClass());
        parameter.setPrimitive(provideSubscriberLocationResponseIndication.getIsPrimitive());
        parameter.setTag(provideSubscriberLocationResponseIndication.getTag());
        parameter.setData(asnOs.toByteArray());

        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addSubscriberLocationReportRequestIndication
     * (org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent, org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID,
     * org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo, org.restcomm.protocols.ss7.map.api.dialog.IMSI,
     * org.restcomm.protocols.ss7.map.api.dialog.AddressString, org.restcomm.protocols.ss7.map.api.dialog.AddressString,
     * org.restcomm.protocols.ss7.map.api.dialog.AddressString, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, int, java.lang.String, org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData,
     * java.lang.String, org.restcomm.protocols.ss7.map.api.service.lsm. CellGlobalIdOrServiceAreaIdOrLAI, java.lang.String,
     * int, boolean, org.mobicents .protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator)
     */
    public Long addSubscriberLocationReportRequest(LCSEvent lcsEvent, LCSClientID lcsClientID, LCSLocationInfo lcsLocationInfo,
            ISDNAddressString msisdn, IMSI imsi, IMEI imei, ISDNAddressString naEsrd, ISDNAddressString naEsrk,
            ExtGeographicalInformation locationEstimate, Integer ageOfLocationEstimate,
            SLRArgExtensionContainer slrArgExtensionContainer, AddGeographicalInformation addLocationEstimate,
            DeferredmtlrData deferredmtlrData, Integer lcsReferenceNumber, PositioningDataInformation geranPositioningData,
            UtranPositioningDataInfo utranPositioningData, CellGlobalIdOrServiceAreaIdOrLAI cellIdOrSai,
            GSNAddress hgmlcAddress, Integer lcsServiceTypeID, boolean saiPresent, boolean pseudonymIndicator,
            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, VelocityEstimate velocityEstimate, Integer sequenceNumber,
            PeriodicLDRInfo periodicLDRInfo, boolean moLrShortCircuitIndicator,
            GeranGANSSpositioningData geranGANSSpositioningData, UtranGANSSpositioningData utranGANSSpositioningData,
            ServingNodeAddress targetServingNodeForHandover) throws MAPException {
        return this.addSubscriberLocationReportRequest(_Timer_Default, lcsEvent, lcsClientID, lcsLocationInfo, msisdn, imsi,
                imei, naEsrd, naEsrk, locationEstimate, ageOfLocationEstimate, slrArgExtensionContainer, addLocationEstimate,
                deferredmtlrData, lcsReferenceNumber, geranPositioningData, utranPositioningData, cellIdOrSai, hgmlcAddress,
                lcsServiceTypeID, saiPresent, pseudonymIndicator, accuracyFulfilmentIndicator, velocityEstimate,
                sequenceNumber, periodicLDRInfo, moLrShortCircuitIndicator, geranGANSSpositioningData,
                utranGANSSpositioningData, targetServingNodeForHandover);
    }

    public Long addSubscriberLocationReportRequest(int customInvokeTimeout, LCSEvent lcsEvent, LCSClientID lcsClientID,
            LCSLocationInfo lcsLocationInfo, ISDNAddressString msisdn, IMSI imsi, IMEI imei, ISDNAddressString naEsrd,
            ISDNAddressString naEsrk, ExtGeographicalInformation locationEstimate, Integer ageOfLocationEstimate,
            SLRArgExtensionContainer slrArgExtensionContainer, AddGeographicalInformation addLocationEstimate,
            DeferredmtlrData deferredmtlrData, Integer lcsReferenceNumber, PositioningDataInformation geranPositioningData,
            UtranPositioningDataInfo utranPositioningData, CellGlobalIdOrServiceAreaIdOrLAI cellIdOrSai,
            GSNAddress hgmlcAddress, Integer lcsServiceTypeID, boolean saiPresent, boolean pseudonymIndicator,
            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, VelocityEstimate velocityEstimate, Integer sequenceNumber,
            PeriodicLDRInfo periodicLDRInfo, boolean moLrShortCircuitIndicator,
            GeranGANSSpositioningData geranGANSSpositioningData, UtranGANSSpositioningData utranGANSSpositioningData,
            ServingNodeAddress targetServingNodeForHandover) throws MAPException {

        if (lcsEvent == null || lcsClientID == null || lcsLocationInfo == null) {
            throw new MAPException("Mandatory parameters lCSEvent, lCSClientID or lCSLocationInfo cannot be null");
        }
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcEnquiryContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "Bad application context name for addSubscriberLocationReportRequest: must be locationSvcEnquiryContext_V3");

        try {
            Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
            if (customInvokeTimeout == _Timer_Default)
                invoke.setTimeout(getMediumTimer());
            else
                invoke.setTimeout(customInvokeTimeout);

            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.subscriberLocationReport);
            invoke.setOperationCode(operationCode);

            SubscriberLocationReportRequestImpl subscriberLocationReportRequest = new SubscriberLocationReportRequestImpl(lcsEvent, lcsClientID,
                    lcsLocationInfo, msisdn, imsi, imei, naEsrd, naEsrk, locationEstimate, ageOfLocationEstimate,
                    slrArgExtensionContainer, addLocationEstimate, deferredmtlrData, lcsReferenceNumber, geranPositioningData,
                    utranPositioningData, cellIdOrSai, hgmlcAddress, lcsServiceTypeID, saiPresent, pseudonymIndicator,
                    accuracyFulfilmentIndicator, velocityEstimate, sequenceNumber, periodicLDRInfo, moLrShortCircuitIndicator,
                    geranGANSSpositioningData, utranGANSSpositioningData, targetServingNodeForHandover);

            AsnOutputStream asnOs = new AsnOutputStream();
            subscriberLocationReportRequest.encodeData(asnOs);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(subscriberLocationReportRequest.getTagClass());
            parameter.setPrimitive(subscriberLocationReportRequest.getIsPrimitive());
            parameter.setTag(subscriberLocationReportRequest.getTag());
            parameter.setData(asnOs.toByteArray());

            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addSubscriberLocationReportResponseIndication(long,
     * org.restcomm.protocols.ss7.map.api.dialog.MAPExtensionContainer,
     * org.restcomm.protocols.ss7.map.api.dialog.AddressString, org.restcomm.protocols.ss7.map.api.dialog.AddressString)
     */
    public void addSubscriberLocationReportResponse(long invokeId, ISDNAddressString naEsrd, ISDNAddressString naEsrk,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcEnquiryContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "Bad application context name for addSubscriberLocationReportResponse: must be locationSvcEnquiryContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.subscriberLocationReport);
        resultLast.setOperationCode(operationCode);

        SubscriberLocationReportResponseImpl subscriberLocationReportResponseIndication = new SubscriberLocationReportResponseImpl(naEsrd, naEsrk,
                extensionContainer);

        AsnOutputStream asnOs = new AsnOutputStream();
        subscriberLocationReportResponseIndication.encodeData(asnOs);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(subscriberLocationReportResponseIndication.getTagClass());
        parameter.setPrimitive(subscriberLocationReportResponseIndication.getIsPrimitive());
        parameter.setTag(subscriberLocationReportResponseIndication.getTag());
        parameter.setData(asnOs.toByteArray());

        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addSendRoutingInforForLCSRequestIndication
     * (org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString,
     * org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberIdentity,
     * org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer)
     */
    public Long addSendRoutingInfoForLCSRequest(ISDNAddressString mlcNumber, SubscriberIdentity targetMS,
            MAPExtensionContainer extensionContainer) throws MAPException {
        return this.addSendRoutingInfoForLCSRequest(_Timer_Default, mlcNumber, targetMS, extensionContainer);
    }

    public Long addSendRoutingInfoForLCSRequest(int customInvokeTimeout, ISDNAddressString mlcNumber,
            SubscriberIdentity targetMS, MAPExtensionContainer extensionContainer) throws MAPException {

        if (mlcNumber == null || targetMS == null) {
            throw new MAPException("Mandatory parameters mlcNumber or targetMS cannot be null");
        }
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcGatewayContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "Bad application context name for addSendRoutingInfoForLCSRequest: must be locationSvcGatewayContext_V3");

        try {
            Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
            if (customInvokeTimeout == _Timer_Default)
                invoke.setTimeout(getMediumTimer());
            else
                invoke.setTimeout(customInvokeTimeout);

            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForLCS);
            invoke.setOperationCode(operationCode);

            SendRoutingInfoForLCSRequestImpl sendRoutingInfoForLCSRequest = new SendRoutingInfoForLCSRequestImpl(mlcNumber, targetMS, extensionContainer);

            AsnOutputStream asnOs = new AsnOutputStream();
            sendRoutingInfoForLCSRequest.encodeData(asnOs);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(sendRoutingInfoForLCSRequest.getTagClass());
            parameter.setPrimitive(sendRoutingInfoForLCSRequest.getIsPrimitive());
            parameter.setTag(sendRoutingInfoForLCSRequest.getTag());
            parameter.setData(asnOs.toByteArray());

            invoke.setParameter(parameter);

            Long invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);

            this.sendInvokeComponent(invoke);

            return invokeId;
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm# addSendRoutingInforForLCSResponseIndication
     * (org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberIdentity,
     * org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo,
     * org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer, byte[], byte[], byte[], byte[])
     */
    public void addSendRoutingInfoForLCSResponse(long invokeId, SubscriberIdentity targetMS, LCSLocationInfo lcsLocationInfo,
            MAPExtensionContainer extensionContainer, GSNAddress vgmlcAddress, GSNAddress hGmlcAddress, GSNAddress pprAddress,
            GSNAddress additionalVGmlcAddress) throws MAPException {

        if (targetMS == null || lcsLocationInfo == null) {
            throw new MAPException("Mandatory parameters targetMS or lcsLocationInfo cannot be null");
        }
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.locationSvcGatewayContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3)
            throw new MAPException(
                    "Bad application context name for addSendRoutingInfoForLCSResponse: must be locationSvcGatewayContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();
        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForLCS);
        resultLast.setOperationCode(operationCode);

        SendRoutingInfoForLCSResponseImpl sendRoutingInfoForLCSResponseIndication = new SendRoutingInfoForLCSResponseImpl(targetMS, lcsLocationInfo,
                extensionContainer, vgmlcAddress, hGmlcAddress, pprAddress, additionalVGmlcAddress);

        AsnOutputStream asnOs = new AsnOutputStream();
        sendRoutingInfoForLCSResponseIndication.encodeData(asnOs);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendRoutingInfoForLCSResponseIndication.getTagClass());
        parameter.setPrimitive(sendRoutingInfoForLCSResponseIndication.getIsPrimitive());
        parameter.setTag(sendRoutingInfoForLCSResponseIndication.getTag());
        parameter.setData(asnOs.toByteArray());

        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);

    }
}
