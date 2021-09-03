
package org.restcomm.protocols.ss7.map.service.mobility;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.MAPServiceBaseImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobilityListener;
import org.restcomm.protocols.ss7.map.dialog.ServingCheckDataImpl;
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
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPServiceMobilityImpl extends MAPServiceBaseImpl implements MAPServiceMobility {

    protected Logger logger = Logger.getLogger(MAPServiceMobilityImpl.class);

    public MAPServiceMobilityImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /*
     * Creating a new outgoing MAP Mobility dialog and adding it to the MAPProvider.dialog collection
     */
    public MAPDialogMobility createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogMobility createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTransactionId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogMobility because MAPServiceMobility is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTransactionId);
        MAPDialogMobilityImpl mapDialogMobility = new MAPDialogMobilityImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this,
                origReference, destReference);

        this.putMAPDialogIntoCollection(mapDialogMobility);

        return mapDialogMobility;
    }

    @Override
    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext mapApplicationContext, Dialog tcapDialog) {
        return new MAPDialogMobilityImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    public void addMAPServiceListener(MAPServiceMobilityListener mapServiceMobilityListener) {
        super.addMAPServiceListener(mapServiceMobilityListener);
    }

    public void removeMAPServiceListener(MAPServiceMobilityListener mapServiceMobilityListener) {
        super.removeMAPServiceListener(mapServiceMobilityListener);
    }

    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName ctx = mapDialogApplicationContext.getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (ctx) {

        // -- Authentication management services
        case infoRetrievalContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case authenticationFailureReportContext:
            if (mapDialogApplicationContextVersion >= 3 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

            // -- Location management services
        case networkLocUpContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case locationCancellationContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case interVlrInfoRetrievalContext:
            if (mapDialogApplicationContextVersion >= 2 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case gprsLocationUpdateContext:
            if (mapDialogApplicationContextVersion == 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case msPurgingContext:
            if (mapDialogApplicationContextVersion >= 2 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

            // -- Fault recovery
        case resetContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 2) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 2) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

            // -- International mobile equipment identities management services
        case equipmentMngtContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

            // -- Subscriber Information services
        case anyTimeEnquiryContext:
        case anyTimeInfoHandlingContext:
        case subscriberInfoEnquiryContext:
            if (mapDialogApplicationContextVersion >= 3 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

            // -- Subscriber Management services
        case subscriberDataMngtContext:
            if (mapDialogApplicationContextVersion >= 1 && mapDialogApplicationContextVersion <= 3) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 3) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    @Override
    public MAPApplicationContext getMAPv1ApplicationContext(int operationCode, Invoke invoke) {

        switch (operationCode) {

        // -- Location management services
        case MAPOperationCode.updateLocation:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.networkLocUpContext, MAPApplicationContextVersion.version1);

        case MAPOperationCode.cancelLocation:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.locationCancellationContext, MAPApplicationContextVersion.version1);

            // -- Authentication management services
        case MAPOperationCode.sendParameters:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.infoRetrievalContext, MAPApplicationContextVersion.version1);

            // -- Fault recovery services
        case MAPOperationCode.reset:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.resetContext, MAPApplicationContextVersion.version1);

            // -- IMEI services
        case MAPOperationCode.checkIMEI:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.equipmentMngtContext, MAPApplicationContextVersion.version1);

            // -- Subscriber Management services
        case MAPOperationCode.insertSubscriberData:
        case MAPOperationCode.deleteSubscriberData:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.subscriberDataMngtContext, MAPApplicationContextVersion.version1);

        }

        return null;
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

        // if an application-context-name different from version 1 is
        // received in a syntactically correct TC-
        // BEGIN indication primitive but is not acceptable from a load
        // control point of view, the MAP PM
        // shall ignore this dialogue request. The MAP-user is not informed.
//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // we agree mobility services when congestion
//        }

        MAPDialogMobilityImpl mapDialogMobilityImpl = (MAPDialogMobilityImpl) mapDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);
        MAPApplicationContextName mapApplicationContextName = mapDialog.getApplicationContext().getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialog.getApplicationContext().getApplicationContextVersion().getVersion();
        int operationCodeValueInt = (int) (long) operationCodeValue;

        switch (operationCodeValueInt) {

        // -- Location management services
            case MAPOperationCode.updateLocation:
                if (mapApplicationContextName == MAPApplicationContextName.networkLocUpContext) {
                    if (componentType == ComponentType.Invoke)
                        this.updateLocationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.updateLocationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.cancelLocation:
                if (mapApplicationContextName == MAPApplicationContextName.locationCancellationContext) {
                    if (componentType == ComponentType.Invoke)
                        this.cancelLocationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.cancelLocationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.sendIdentification:
                if (mapApplicationContextName == MAPApplicationContextName.interVlrInfoRetrievalContext) {
                    if (componentType == ComponentType.Invoke)
                        this.SendIdentificationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.SendIdentificationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.updateGprsLocation:
                if (mapApplicationContextName == MAPApplicationContextName.gprsLocationUpdateContext) {
                    if (componentType == ComponentType.Invoke)
                        this.updateGprsLocationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.updateGprsLocationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.purgeMS:
                if (mapApplicationContextName == MAPApplicationContextName.msPurgingContext) {
                    if (componentType == ComponentType.Invoke)
                        this.purgeMSRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.purgeMSResponse(parameter, mapDialogMobilityImpl, invokeId, componentType == ComponentType.ReturnResult);
                }
                break;

            // -- Authentication management services
            case MAPOperationCode.sendAuthenticationInfo:
                if (mapApplicationContextName == MAPApplicationContextName.infoRetrievalContext && mapDialogApplicationContextVersion >= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.sendAuthenticationInfoRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.sendAuthenticationInfoResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.authenticationFailureReport:
                if (mapApplicationContextName == MAPApplicationContextName.authenticationFailureReportContext && mapDialogApplicationContextVersion >= 3) {
                    if (componentType == ComponentType.Invoke)
                        this.authenticationFailureReportRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.authenticationFailureReportResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            // -- Fault Recovery services
            case MAPOperationCode.reset:
                if (mapApplicationContextName == MAPApplicationContextName.resetContext && mapDialogApplicationContextVersion <= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.resetRequest(parameter, mapDialogMobilityImpl, invokeId);
                }
                break;
            case MAPOperationCode.forwardCheckSsIndication:
                if (mapApplicationContextName == MAPApplicationContextName.networkLocUpContext) {
                    if (componentType == ComponentType.Invoke)
                        this.forwardCheckSsIndicationRequest(parameter, mapDialogMobilityImpl, invokeId);
                }
                break;
            case MAPOperationCode.restoreData:
                if (mapApplicationContextName == MAPApplicationContextName.networkLocUpContext && mapDialogApplicationContextVersion >= 2) {
                    if (componentType == ComponentType.Invoke)
                        this.restoreDataRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.restoreDataResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            // -- Subscriber Information services
            case MAPOperationCode.anyTimeInterrogation:
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processAnyTimeInterrogationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processAnyTimeInterrogationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.anyTimeSubscriptionInterrogation:
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeInfoHandlingContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processAnyTimeSubscriptionInterrogationRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processAnyTimeSubscriptionInterrogationResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.provideSubscriberInfo:
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processProvideSubscriberInfoRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processProvideSubscriberInfoResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            // -- IMEI services
            case MAPOperationCode.checkIMEI:
                if (mapApplicationContextName == MAPApplicationContextName.equipmentMngtContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processCheckImeiRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processCheckImeiResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            // -- Subscriber management services
            case MAPOperationCode.insertSubscriberData:
                if (mapApplicationContextName == MAPApplicationContextName.subscriberDataMngtContext
                        || mapApplicationContextName == MAPApplicationContextName.networkLocUpContext
                        || mapApplicationContextName == MAPApplicationContextName.gprsLocationUpdateContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processInsertSubscriberDataRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processInsertSubscriberDataResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.deleteSubscriberData:
                if (mapApplicationContextName == MAPApplicationContextName.subscriberDataMngtContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processDeleteSubscriberDataRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processDeleteSubscriberDataResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            // -- OAM service: activateTraceMode operation can be present in
            // networkLocUpContext and gprsLocationUpdateContext application
            // contexts
            case MAPOperationCode.activateTraceMode:
                if (mapApplicationContextName == MAPApplicationContextName.networkLocUpContext
                        || mapApplicationContextName == MAPApplicationContextName.gprsLocationUpdateContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processActivateTraceModeRequest(parameter, mapDialogMobilityImpl, invokeId);
                    else
                        this.processActivateTraceModeResponse(parameter, mapDialogMobilityImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            default:
                throw new MAPParsingComponentException("MAPServiceMobility: unknown incoming operation code: " + operationCodeValueInt,
                        MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    // -- Location management services
    private void updateLocationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding updateLocationRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding updateLocationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        UpdateLocationRequestImpl updateLocationRequestIndication = new UpdateLocationRequestImpl(version);
        updateLocationRequestIndication.decodeData(ais, buf.length);

        updateLocationRequestIndication.setInvokeId(invokeId);
        updateLocationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(updateLocationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onUpdateLocationRequest(updateLocationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing UpdateLocationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void updateLocationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding updateLocationResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version >= 2) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding updateLocationResponse V2_3: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding updateLocationResponse V1: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        UpdateLocationResponseImpl updateLocationResponseIndication = new UpdateLocationResponseImpl(version);
        updateLocationResponseIndication.decodeData(ais, buf.length);

        updateLocationResponseIndication.setInvokeId(invokeId);
        updateLocationResponseIndication.setMAPDialog(mapDialogImpl);
        updateLocationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(updateLocationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onUpdateLocationResponse(updateLocationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing UpdateLocationResponse: " + e.getMessage(), e);
            }
        }
    }

    private void cancelLocationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding cancelLocationRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version == 3) {
            if (parameter.getTag() != CancelLocationRequestImpl.TAG_cancelLocationRequest
                    || parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding cancelLocationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if ((!(parameter.getTag() == Tag.SEQUENCE || parameter.getTag() == Tag.STRING_OCTET))
                    || parameter.getTagClass() != Tag.CLASS_UNIVERSAL)
                throw new MAPParsingComponentException("Error while decoding cancelLocationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        CancelLocationRequestImpl cancelLocationRequestIndication = new CancelLocationRequestImpl(version);
        cancelLocationRequestIndication.decodeData(ais, buf.length);

        cancelLocationRequestIndication.setInvokeId(invokeId);
        cancelLocationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(cancelLocationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onCancelLocationRequest(cancelLocationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing CancelLocationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void cancelLocationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        CancelLocationResponseImpl cancelLocationResponseIndication = new CancelLocationResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding cancelLocationResponse V2_3: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);

            cancelLocationResponseIndication.decodeData(ais, buf.length);
        }

        cancelLocationResponseIndication.setInvokeId(invokeId);
        cancelLocationResponseIndication.setMAPDialog(mapDialogImpl);
        cancelLocationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(cancelLocationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onCancelLocationResponse(cancelLocationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing CancelLocationResponse: " + e.getMessage(), e);
            }
        }
    }

    private void SendIdentificationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding sendIdentificationRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version == 3) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding sendIdentificationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding sendIdentificationRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        SendIdentificationRequestImpl sendIdentificationRequestIndication = new SendIdentificationRequestImpl(version);
        sendIdentificationRequestIndication.decodeData(ais, buf.length);

        sendIdentificationRequestIndication.setInvokeId(invokeId);
        sendIdentificationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendIdentificationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onSendIdentificationRequest(sendIdentificationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing SendIdentificationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void SendIdentificationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        SendIdentificationResponseImpl sendIdentificationResponseIndication = new SendIdentificationResponseImpl(version);

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding SendIdentificationResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version == 3) {
            if (parameter.getTag() != SendIdentificationResponseImpl._TAG_SendIdentificationResponse
                    || parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding sendIdentificationResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding sendIdentificationResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        sendIdentificationResponseIndication.decodeData(ais, buf.length);

        sendIdentificationResponseIndication.setInvokeId(invokeId);
        sendIdentificationResponseIndication.setMAPDialog(mapDialogImpl);
        sendIdentificationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendIdentificationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onSendIdentificationResponse(sendIdentificationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing SendIdentificationResponse: " + e.getMessage(), e);
            }
        }
    }

    private void updateGprsLocationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding updateGprsLocationRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version == 3) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding updateGprsLocationRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        UpdateGprsLocationRequestImpl updateGprsLocationRequestIndication = new UpdateGprsLocationRequestImpl();
        updateGprsLocationRequestIndication.decodeData(ais, buf.length);

        updateGprsLocationRequestIndication.setInvokeId(invokeId);
        updateGprsLocationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(updateGprsLocationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onUpdateGprsLocationRequest(updateGprsLocationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing UpdateGprsLocationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void updateGprsLocationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        UpdateGprsLocationResponseImpl updateGprsLocationResponseIndication = new UpdateGprsLocationResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding updateGprsLocationResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding updateGprsLocationResponse V3: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        updateGprsLocationResponseIndication.decodeData(ais, buf.length);

        updateGprsLocationResponseIndication.setInvokeId(invokeId);
        updateGprsLocationResponseIndication.setMAPDialog(mapDialogImpl);
        updateGprsLocationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(updateGprsLocationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onUpdateGprsLocationResponse(updateGprsLocationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing UpdateGprsLocationResponse: " + e.getMessage(), e);
            }
        }
    }

    private void purgeMSRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding PurgeMSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version == 3) {
            if (parameter.getTag() != PurgeMSRequestImpl._TAG_PurgeMSRequest
                    || parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding PurgeMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (version == 2) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding PurgeMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
        PurgeMSRequestImpl purgeMSRequestIndication = new PurgeMSRequestImpl(version);
        purgeMSRequestIndication.decodeData(ais, buf.length);

        purgeMSRequestIndication.setInvokeId(invokeId);
        purgeMSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(purgeMSRequestIndication);
                ((MAPServiceMobilityListener) serLis).onPurgeMSRequest(purgeMSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing PurgeMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void purgeMSResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        PurgeMSResponseImpl purgeMSResponseIndication = new PurgeMSResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding PurgeMSResponse V3: Bad tag or tagClass or parameter is primitive, received tag="
                        + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);

            purgeMSResponseIndication.decodeData(ais, buf.length);
        }

        purgeMSResponseIndication.setInvokeId(invokeId);
        purgeMSResponseIndication.setMAPDialog(mapDialogImpl);
        purgeMSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(purgeMSResponseIndication);
                ((MAPServiceMobilityListener) serLis).onPurgeMSResponse(purgeMSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing PurgeMSResponse: " + e.getMessage(), e);
            }
        }
    }

    // -- Authentication management services
    private void sendAuthenticationInfoRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        SendAuthenticationInfoRequestImpl sendAuthenticationInfoRequestIndication = new SendAuthenticationInfoRequestImpl(version);
        if (version >= 3) {
            if (parameter != null) {
                if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                        || parameter.isPrimitive())
                    throw new MAPParsingComponentException(
                            "Error while decoding sendAuthenticationInfoRequest V3: Bad tag or tagClass or parameter is primitive, received tag="
                                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

                byte[] buf = parameter.getData();
                AsnInputStream ais = new AsnInputStream(buf);
                sendAuthenticationInfoRequestIndication.decodeData(ais, buf.length);
            }
        } else {
            if (parameter == null)
                throw new MAPParsingComponentException(
                        "Error while decoding sendAuthenticationInfoRequest V2: Parameter is mandatory but not found",
                        MAPParsingComponentExceptionReason.MistypedParameter);

            if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding sendAuthenticationInfoRequest V2: Bad tag or tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            sendAuthenticationInfoRequestIndication.decodeData(ais, buf.length);
        }

        sendAuthenticationInfoRequestIndication.setInvokeId(invokeId);
        sendAuthenticationInfoRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendAuthenticationInfoRequestIndication);
                ((MAPServiceMobilityListener) serLis).onSendAuthenticationInfoRequest(sendAuthenticationInfoRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing SendAuthenticationInfoRequest: " + e.getMessage(), e);
            }
        }
    }

    private void sendAuthenticationInfoResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        SendAuthenticationInfoResponseImpl sendAuthenticationInfoResponseIndication = new SendAuthenticationInfoResponseImpl(version);
        if (version >= 3) {
            if (parameter != null) {
                if (parameter.getTag() != SendAuthenticationInfoResponseImpl._TAG_General
                        || parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || parameter.isPrimitive())
                    throw new MAPParsingComponentException(
                            "Error while decoding sendAuthenticationInfoResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

                byte[] buf = parameter.getData();
                AsnInputStream ais = new AsnInputStream(buf);
                sendAuthenticationInfoResponseIndication.decodeData(ais, buf.length);
            }
        } else {
            if (parameter != null) {
                if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                        || parameter.isPrimitive())
                    throw new MAPParsingComponentException(
                            "Error while decoding sendAuthenticationInfoResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

                byte[] buf = parameter.getData();
                AsnInputStream ais = new AsnInputStream(buf);
                sendAuthenticationInfoResponseIndication.decodeData(ais, buf.length);
            }
        }

        sendAuthenticationInfoResponseIndication.setInvokeId(invokeId);
        sendAuthenticationInfoResponseIndication.setMAPDialog(mapDialogImpl);
        sendAuthenticationInfoResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendAuthenticationInfoResponseIndication);
                ((MAPServiceMobilityListener) serLis).onSendAuthenticationInfoResponse(sendAuthenticationInfoResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing SendAuthenticationInfoResponse: " + e.getMessage(), e);
            }
        }
    }

    private void authenticationFailureReportRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        AuthenticationFailureReportRequestImpl authenticationFailureReportRequestIndication = new AuthenticationFailureReportRequestImpl();
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding authenticationFailureReportRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding authenticationFailureReportRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        authenticationFailureReportRequestIndication.decodeData(ais, buf.length);
        authenticationFailureReportRequestIndication.setInvokeId(invokeId);
        authenticationFailureReportRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(authenticationFailureReportRequestIndication);
                ((MAPServiceMobilityListener) serLis).onAuthenticationFailureReportRequest(authenticationFailureReportRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing AuthenticationFailureReportRequest: " + e.getMessage(), e);
            }
        }
    }

    private void authenticationFailureReportResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        AuthenticationFailureReportResponseImpl authenticationFailureReportResponseIndication = new AuthenticationFailureReportResponseImpl();
        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding authenticationFailureReportResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            authenticationFailureReportResponseIndication.decodeData(ais, buf.length);
        }

        authenticationFailureReportResponseIndication.setInvokeId(invokeId);
        authenticationFailureReportResponseIndication.setMAPDialog(mapDialogImpl);
        authenticationFailureReportResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(authenticationFailureReportResponseIndication);
                ((MAPServiceMobilityListener) serLis).onAuthenticationFailureReportResponse(authenticationFailureReportResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing AuthenticationFailureReportResponse: " + e.getMessage(), e);
            }
        }
    }

    // -- Fault Recovery services
    private void resetRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding resetRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding resetRequest: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        ResetRequestImpl resetRequestIndication = new ResetRequestImpl(version);
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        resetRequestIndication.decodeData(ais, buf.length);

        resetRequestIndication.setInvokeId(invokeId);
        resetRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(resetRequestIndication);
                ((MAPServiceMobilityListener) serLis).onResetRequest(resetRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing ResetRequest: " + e.getMessage(), e);
            }
        }
    }

    private void forwardCheckSsIndicationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {

        ForwardCheckSSIndicationRequestImpl forwardCheckSSIndicationRequestIndication = new ForwardCheckSSIndicationRequestImpl();

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(forwardCheckSSIndicationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onForwardCheckSSIndicationRequest(forwardCheckSSIndicationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing ForwardCheckSsIndicationRequest: " + e.getMessage(), e);
            }
        }
    }

    private void restoreDataRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding restoreDataRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding restoreDataRequest: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        RestoreDataRequestImpl restoreDataRequestIndication = new RestoreDataRequestImpl();
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        restoreDataRequestIndication.decodeData(ais, buf.length);

        restoreDataRequestIndication.setInvokeId(invokeId);
        restoreDataRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(restoreDataRequestIndication);
                ((MAPServiceMobilityListener) serLis).onRestoreDataRequest(restoreDataRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing RestoreDataRequest: " + e.getMessage(), e);
            }
        }
    }

    private void restoreDataResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding restoreDataResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding restoreDataResponse: Bad tag or tagClass or parameter is primitive, received tag="
                    + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        RestoreDataResponseImpl restoreDataResponseIndication = new RestoreDataResponseImpl();
        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        restoreDataResponseIndication.decodeData(ais, buf.length);

        restoreDataResponseIndication.setInvokeId(invokeId);
        restoreDataResponseIndication.setMAPDialog(mapDialogImpl);
        restoreDataResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(restoreDataResponseIndication);
                ((MAPServiceMobilityListener) serLis).onRestoreDataResponse(restoreDataResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing RestoreDataResponse: " + e.getMessage(), e);
            }
        }
    }

    // -- Subscriber Information services
    private void processAnyTimeInterrogationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding AnyTimeInterrogationRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding AnyTimeInterrogationRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        AnyTimeInterrogationRequestImpl anyTimeInterrogationRequestIndication = new AnyTimeInterrogationRequestImpl();
        anyTimeInterrogationRequestIndication.decodeData(ais, buf.length);
        anyTimeInterrogationRequestIndication.setInvokeId(invokeId);
        anyTimeInterrogationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(anyTimeInterrogationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onAnyTimeInterrogationRequest(anyTimeInterrogationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing AnyTimeInterrogationRequestIndication: " + e.getMessage(), e);
            }
        }

    }

    private void processAnyTimeInterrogationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding AnyTimeInterrogationResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding AnyTimeInterrogationResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        AnyTimeInterrogationResponseImpl anyTimeInterrogationResponseIndication = new AnyTimeInterrogationResponseImpl();
        anyTimeInterrogationResponseIndication.decodeData(ais, buf.length);
        anyTimeInterrogationResponseIndication.setInvokeId(invokeId);
        anyTimeInterrogationResponseIndication.setMAPDialog(mapDialogImpl);
        anyTimeInterrogationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(anyTimeInterrogationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onAnyTimeInterrogationResponse(anyTimeInterrogationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing AnyTimeInterrogationResponseIndication: " + e.getMessage(), e);
            }
        }

    }

    private void processAnyTimeSubscriptionInterrogationRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding AnyTimeSubscriptionInterrogationRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding AnyTimeSubscriptionInterrogationRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        AnyTimeSubscriptionInterrogationRequestImpl anyTimeSubscriptionInterrogationRequestIndication = new AnyTimeSubscriptionInterrogationRequestImpl();
        anyTimeSubscriptionInterrogationRequestIndication.decodeData(ais, buf.length);
        anyTimeSubscriptionInterrogationRequestIndication.setInvokeId(invokeId);
        anyTimeSubscriptionInterrogationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(anyTimeSubscriptionInterrogationRequestIndication);
                ((MAPServiceMobilityListener) serLis).onAnyTimeSubscriptionInterrogationRequest(anyTimeSubscriptionInterrogationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing AnyTimeSubscriptionInterrogationRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processAnyTimeSubscriptionInterrogationResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl,
            Long invokeId, boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding AnyTimeSubscriptionInterrogationResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding AnyTimeSubscriptionInterrogationResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        AnyTimeSubscriptionInterrogationResponseImpl anyTimeSubscriptionInterrogationResponseIndication = new AnyTimeSubscriptionInterrogationResponseImpl();
        anyTimeSubscriptionInterrogationResponseIndication.decodeData(ais, buf.length);
        anyTimeSubscriptionInterrogationResponseIndication.setInvokeId(invokeId);
        anyTimeSubscriptionInterrogationResponseIndication.setMAPDialog(mapDialogImpl);
        anyTimeSubscriptionInterrogationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(anyTimeSubscriptionInterrogationResponseIndication);
                ((MAPServiceMobilityListener) serLis).onAnyTimeSubscriptionInterrogationResponse(anyTimeSubscriptionInterrogationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing AnyTimeSubscriptionInterrogationResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processProvideSubscriberInfoRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding ProvideSubscriberInfoRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding ProvideSubscriberInfoRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        ProvideSubscriberInfoRequestImpl provideSubscriberInfoRequestIndication = new ProvideSubscriberInfoRequestImpl();
        provideSubscriberInfoRequestIndication.decodeData(ais, buf.length);
        provideSubscriberInfoRequestIndication.setInvokeId(invokeId);
        provideSubscriberInfoRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(provideSubscriberInfoRequestIndication);
                ((MAPServiceMobilityListener) serLis).onProvideSubscriberInfoRequest(provideSubscriberInfoRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing ProvideSubscriberInfoRequest: " + e.getMessage(), e);
            }
        }

    }

    private void processProvideSubscriberInfoResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding ProvideSubscriberInfoResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding ProvideSubscriberInfoResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        ProvideSubscriberInfoResponseImpl provideSubscriberInfoResponseIndication = new ProvideSubscriberInfoResponseImpl();
        provideSubscriberInfoResponseIndication.decodeData(ais, buf.length);
        provideSubscriberInfoResponseIndication.setInvokeId(invokeId);
        provideSubscriberInfoResponseIndication.setMAPDialog(mapDialogImpl);
        provideSubscriberInfoResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(provideSubscriberInfoResponseIndication);
                ((MAPServiceMobilityListener) serLis).onProvideSubscriberInfoResponse(provideSubscriberInfoResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing ProvideSubscriberInfoResponseIndication: " + e.getMessage(), e);
            }
        }

    }

    // - IMEI services
    private void processCheckImeiRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding CheckImeiRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        if (version >= 3) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding CheckImeiRequest: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding CheckImeiRequest V1 or V2: Bad tag or tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        CheckImeiRequestImpl checkImeiRequestIndication = new CheckImeiRequestImpl(version);
        checkImeiRequestIndication.decodeData(ais, parameter.getEncodingLength());

        checkImeiRequestIndication.setInvokeId(invokeId);
        checkImeiRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(checkImeiRequestIndication);
                ((MAPServiceMobilityListener) serLis).onCheckImeiRequest(checkImeiRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing CheckImeiRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processCheckImeiResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding CheckImeiResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        if (version >= 3) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding CheckImeiResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.ENUMERATED || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding CheckImeiResponse: Bad tag or tagClass or parameter is not primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        CheckImeiResponseImpl checkImeiResponseIndication = new CheckImeiResponseImpl(version);
        checkImeiResponseIndication.decodeData(ais, buf.length);
        checkImeiResponseIndication.setInvokeId(invokeId);
        checkImeiResponseIndication.setMAPDialog(mapDialogImpl);
        checkImeiResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(checkImeiResponseIndication);
                ((MAPServiceMobilityListener) serLis).onCheckImeiResponse(checkImeiResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing CheckImeiResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    // - Subscriber management services
    private void processInsertSubscriberDataRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding InsertSubscriberDataRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding InsertSubscriberDataRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        InsertSubscriberDataRequestImpl insertSubscriberDataRequestIndication = new InsertSubscriberDataRequestImpl(version);
        insertSubscriberDataRequestIndication.decodeData(ais, buf.length);
        insertSubscriberDataRequestIndication.setInvokeId(invokeId);
        insertSubscriberDataRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(insertSubscriberDataRequestIndication);
                ((MAPServiceMobilityListener) serLis).onInsertSubscriberDataRequest(insertSubscriberDataRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing InsertSubscriberDataRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processInsertSubscriberDataResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        InsertSubscriberDataResponseImpl insertSubscriberDataResponseIndication = new InsertSubscriberDataResponseImpl(version);

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding InsertSubscriberDataResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            insertSubscriberDataResponseIndication.decodeData(ais, buf.length);
        }

        insertSubscriberDataResponseIndication.setInvokeId(invokeId);
        insertSubscriberDataResponseIndication.setMAPDialog(mapDialogImpl);
        insertSubscriberDataResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(insertSubscriberDataResponseIndication);
                ((MAPServiceMobilityListener) serLis).onInsertSubscriberDataResponse(insertSubscriberDataResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing InsertSubscriberDataResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processDeleteSubscriberDataRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding DeleteSubscriberDataRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding DeleteSubscriberDataRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        DeleteSubscriberDataRequestImpl deleteSubscriberDataRequestIndication = new DeleteSubscriberDataRequestImpl();
        deleteSubscriberDataRequestIndication.decodeData(ais, buf.length);
        deleteSubscriberDataRequestIndication.setInvokeId(invokeId);
        deleteSubscriberDataRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(deleteSubscriberDataRequestIndication);
                ((MAPServiceMobilityListener) serLis).onDeleteSubscriberDataRequest(deleteSubscriberDataRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing DeleteSubscriberDataRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processDeleteSubscriberDataResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        DeleteSubscriberDataResponseImpl deleteSubscriberDataResponseIndication = new DeleteSubscriberDataResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding DeleteSubscriberDataResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            deleteSubscriberDataResponseIndication.decodeData(ais, buf.length);
        }

        deleteSubscriberDataResponseIndication.setInvokeId(invokeId);
        deleteSubscriberDataResponseIndication.setMAPDialog(mapDialogImpl);
        deleteSubscriberDataResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(deleteSubscriberDataResponseIndication);
                ((MAPServiceMobilityListener) serLis).onDeleteSubscriberDataResponse(deleteSubscriberDataResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing DeleteSubscriberDataResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processActivateTraceModeRequest(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding processActivateTraceModeRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding processActivateTraceModeRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ActivateTraceModeRequestImpl_Mobility activateTraceModeRequestMobilityIndication = new ActivateTraceModeRequestImpl_Mobility();
        activateTraceModeRequestMobilityIndication.decodeData(ais, buf.length);

        activateTraceModeRequestMobilityIndication.setInvokeId(invokeId);
        activateTraceModeRequestMobilityIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(activateTraceModeRequestMobilityIndication);
                ((MAPServiceMobilityListener) serLis).onActivateTraceModeRequest_Mobility(activateTraceModeRequestMobilityIndication);
            } catch (Exception e) {
                logger.error("Error processing ActivateTraceModeRequestMobilityIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processActivateTraceModeResponse(Parameter parameter, MAPDialogMobilityImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        ActivateTraceModeResponseImpl_Mobility activateTraceModeResponseMobility = new ActivateTraceModeResponseImpl_Mobility();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding processActivateTraceModeResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            activateTraceModeResponseMobility.decodeData(ais, buf.length);
        }

        activateTraceModeResponseMobility.setInvokeId(invokeId);
        activateTraceModeResponseMobility.setMAPDialog(mapDialogImpl);
        activateTraceModeResponseMobility.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(activateTraceModeResponseMobility);
                ((MAPServiceMobilityListener) serLis).onActivateTraceModeResponse_Mobility(activateTraceModeResponseMobility);
            } catch (Exception e) {
                logger.error("Error processing ActivateTraceModeResponseMobility: " + e.getMessage(), e);
            }
        }
    }

}
