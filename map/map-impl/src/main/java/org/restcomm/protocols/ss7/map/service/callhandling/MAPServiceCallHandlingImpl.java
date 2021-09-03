
package org.restcomm.protocols.ss7.map.service.callhandling;

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
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandlingListener;
import org.restcomm.protocols.ss7.map.dialog.ServingCheckDataImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/*
 *
 * @author cristian veliscu
 * @author eva ogallar
 */
public class MAPServiceCallHandlingImpl extends MAPServiceBaseImpl implements MAPServiceCallHandling {

    private static final Logger logger = Logger.getLogger(MAPServiceCallHandlingImpl.class);

    // Include these constants in MAPApplicationContextName and MAPOperationCode
    // sendRoutingInfo_Request: add constant to MAPMessageType
    // sendRoutingInfo_Response: add constant to MAPMessageType
    protected static final int version = 3;

    public MAPServiceCallHandlingImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    @Override
    public MAPDialogCallHandling createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    @Override
    public MAPDialogCallHandling createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException(
                    "Cannot create MAPDialogRoutingInformation because MAPServiceRoutingInformation is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTransactionId);
        MAPDialogCallHandlingImpl dialog = new MAPDialogCallHandlingImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this,
                origReference, destReference);

        this.putMAPDialogIntoCollection(dialog);
        return dialog;
    }

    @Override
    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext mapApplicationContext, Dialog tcapDialog) {
        return new MAPDialogCallHandlingImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    @Override
    public void addMAPServiceListener(MAPServiceCallHandlingListener mapServiceCallHandlingListener) {
        super.addMAPServiceListener(mapServiceCallHandlingListener);
    }

    @Override
    public void removeMAPServiceListener(MAPServiceCallHandlingListener mapServiceCallHandlingListener) {
        super.removeMAPServiceListener(mapServiceCallHandlingListener);
    }

    @Override
    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName ctx = mapDialogApplicationContext.getApplicationContextName();
        int appCtxVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (ctx) {
            case locationInfoRetrievalContext:
                if (appCtxVersion >= 1 && appCtxVersion <= 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }
            case roamingNumberEnquiryContext:
                if (appCtxVersion >= 1 && appCtxVersion <= 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }
            case ServiceTerminationContext:
                if (appCtxVersion == 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
                }
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    @Override
    public MAPApplicationContext getMAPv1ApplicationContext(int operationCode, Invoke invoke) {
        switch (operationCode) {
            case MAPOperationCode.sendRoutingInfo:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.locationInfoRetrievalContext,
                        MAPApplicationContextVersion.version1);
            case MAPOperationCode.provideRoamingNumber:
                return MAPApplicationContext.getInstance(MAPApplicationContextName.roamingNumberEnquiryContext,
                        MAPApplicationContextVersion.version1);
        }

        return null;
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // we reject all supplementary services when congestion
//            return;
//        }

        MAPDialogCallHandlingImpl mapDialogImpl = (MAPDialogCallHandlingImpl) mapDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);
        MAPApplicationContextName acn = mapDialog.getApplicationContext().getApplicationContextName();
        int appCtxVersion = mapDialog.getApplicationContext().getApplicationContextVersion().getVersion();
        int operationCodeValueInt = (int) (long) operationCodeValue;

        switch (operationCodeValueInt) {
            case MAPOperationCode.sendRoutingInfo:
                if (componentType == ComponentType.Invoke)
                    this.sendRoutingInformationRequest(parameter, mapDialogImpl, invokeId);
                else if (componentType == ComponentType.ReturnResult || componentType == ComponentType.ReturnResultLast)
                    this.sendRoutingInformationResponse(parameter, mapDialogImpl, invokeId,
                            componentType == ComponentType.ReturnResult);
                break;

            case MAPOperationCode.provideRoamingNumber:
                if (componentType == ComponentType.Invoke)
                    this.provideRoamingNumberRequest(parameter, mapDialogImpl, invokeId);
                else if (componentType == ComponentType.ReturnResult || componentType == ComponentType.ReturnResultLast)
                    this.provideRoamingNumberResponse(parameter, mapDialogImpl, invokeId,
                            componentType == ComponentType.ReturnResult);
                break;

            case MAPOperationCode.istCommand:
                if (componentType == ComponentType.Invoke)
                    this.istCommandRequest(parameter, mapDialogImpl, invokeId);
                else if (componentType == ComponentType.ReturnResult || componentType == ComponentType.ReturnResultLast)
                    this.istCommandResponse(parameter, mapDialogImpl, invokeId, componentType == ComponentType.ReturnResult);
                break;
            default:
            throw new MAPParsingComponentException("MAPServiceCallHandling: unknown incoming operation code: " + operationCodeValueInt,
                    MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void sendRoutingInformationRequest(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        SendRoutingInformationRequestImpl sendRoutingInformationRequestIndication = new SendRoutingInformationRequestImpl(version);

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding SendRoutingInformationRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        // No matter what MAP version V1,V2,V3: tag=Tag.SEQUENCE and tagClass=Tag.CLASS_UNIVERSAL
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding SendRoutingInformationRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        sendRoutingInformationRequestIndication.decodeData(ais, buf.length);
        sendRoutingInformationRequestIndication.setInvokeId(invokeId);
        sendRoutingInformationRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInformationRequestIndication);
                ((MAPServiceCallHandlingListener) serLis).onSendRoutingInformationRequest(sendRoutingInformationRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing SendRoutingInformationRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void sendRoutingInformationResponse(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        SendRoutingInformationResponseImpl sendRoutingInformationResponseIndication = new SendRoutingInformationResponseImpl(version);

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding SendRoutingInformationResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version >= 3) { // tag=3 and tagClass=Tag.CLASS_CONTEXT_SPECIFIC
            if (parameter.getTag() != SendRoutingInformationResponseImpl.TAG_sendRoutingInfoRes
                    || parameter.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding SendRoutingInformationResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding SendRoutingInformationResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        sendRoutingInformationResponseIndication.decodeData(ais, buf.length);
        sendRoutingInformationResponseIndication.setInvokeId(invokeId);
        sendRoutingInformationResponseIndication.setMAPDialog(mapDialogImpl);
        sendRoutingInformationResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInformationResponseIndication);
                ((MAPServiceCallHandlingListener) serLis).onSendRoutingInformationResponse(sendRoutingInformationResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing SendRoutingInformationResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    private void provideRoamingNumberRequest(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        ProvideRoamingNumberRequestImpl provideRoamingNumberRequestIndication = new ProvideRoamingNumberRequestImpl(version);

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding ProvideRoamingNumberRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        // No matter what MAP version V1,V2,V3: tag=Tag.SEQUENCE and tagClass=Tag.CLASS_UNIVERSAL
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding ProvideRoamingNumberRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        provideRoamingNumberRequestIndication.decodeData(ais, buf.length);
        provideRoamingNumberRequestIndication.setInvokeId(invokeId);
        provideRoamingNumberRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(provideRoamingNumberRequestIndication);
                ((MAPServiceCallHandlingListener) serLis).onProvideRoamingNumberRequest(provideRoamingNumberRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing ProvideRoamingNumberRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void provideRoamingNumberResponse(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        long version = mapDialogImpl.getApplicationContext().getApplicationContextVersion().getVersion();
        ProvideRoamingNumberResponseImpl provideRoamingNumberResponseIndication = new ProvideRoamingNumberResponseImpl(version);

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding ProvideRoamingNumberResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (version >= 3) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding ProvideRoamingNumberResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                    || !parameter.isPrimitive())
                throw new MAPParsingComponentException(
                        "Error while decoding ProvideRoamingNumberResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        provideRoamingNumberResponseIndication.decodeData(ais, buf.length);
        provideRoamingNumberResponseIndication.setInvokeId(invokeId);
        provideRoamingNumberResponseIndication.setMAPDialog(mapDialogImpl);
        provideRoamingNumberResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(provideRoamingNumberResponseIndication);
                ((MAPServiceCallHandlingListener) serLis).onProvideRoamingNumberResponse(provideRoamingNumberResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing ProvideRoamingNumberResponseIndication: " + e.getMessage(), e);
            }
        }
    }

    private void istCommandRequest(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        IstCommandRequestImpl istCommandRequestIndication = new IstCommandRequestImpl();

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding IstCommandRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        // No matter what MAP version V1,V2,V3: tag=Tag.SEQUENCE and tagClass=Tag.CLASS_UNIVERSAL
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding IstCommandRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);

        istCommandRequestIndication.decodeData(ais, buf.length);
        istCommandRequestIndication.setInvokeId(invokeId);
        istCommandRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(istCommandRequestIndication);
                ((MAPServiceCallHandlingListener) serLis).onIstCommandRequest(istCommandRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing istCommandRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void istCommandResponse(Parameter parameter, MAPDialogCallHandlingImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        IstCommandResponseImpl istCommandResponseIndication = new IstCommandResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive()) {
                throw new MAPParsingComponentException(
                        "Error while decoding IstCommandResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
            }
            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
            istCommandResponseIndication.decodeData(ais, buf.length);
        }

        istCommandResponseIndication.setInvokeId(invokeId);
        istCommandResponseIndication.setMAPDialog(mapDialogImpl);
        istCommandResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(istCommandResponseIndication);
                ((MAPServiceCallHandlingListener) serLis).onIstCommandResponse(istCommandResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing IstCommandResponseIndication: " + e.getMessage(), e);
            }
        }
    }
}
