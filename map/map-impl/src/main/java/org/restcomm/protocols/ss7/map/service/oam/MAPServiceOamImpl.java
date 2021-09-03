
package org.restcomm.protocols.ss7.map.service.oam;

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
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPServiceOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPServiceOamListener;
import org.restcomm.protocols.ss7.map.dialog.ServingCheckDataImpl;
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
public class MAPServiceOamImpl extends MAPServiceBaseImpl implements MAPServiceOam {

    protected Logger loger = Logger.getLogger(MAPServiceOamImpl.class);

    public MAPServiceOamImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /*
     * Creating a new outgoing MAP OAM dialog and adding it to the MAPProvider.dialog collection
     */
    public MAPDialogOam createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogOam createNewDialog(MAPApplicationContext appCntx, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogOam because MAPServiceOam is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        MAPDialogOamImpl dialog = new MAPDialogOamImpl(appCntx, tcapDialog, this.mapProviderImpl, this, origReference,
                destReference);

        this.putMAPDialogIntoCollection(dialog);

        return dialog;
    }

    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext appCntx, Dialog tcapDialog) {
        return new MAPDialogOamImpl(appCntx, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    public void addMAPServiceListener(MAPServiceOamListener mapServiceOamListener) {
        super.addMAPServiceListener(mapServiceOamListener);
    }

    public void removeMAPServiceListener(MAPServiceOamListener mapServiceOamListener) {
        super.removeMAPServiceListener(mapServiceOamListener);
    }

    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName mapApplicationContextName = mapDialogApplicationContext.getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (mapApplicationContextName) {

        case imsiRetrievalContext:
            if (mapDialogApplicationContextVersion >= 2 && mapDialogApplicationContextVersion <= 2) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 2) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 3;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }

        case tracingContext:
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

        case MAPOperationCode.activateTraceMode:
            return MAPApplicationContext.getInstance(MAPApplicationContextName.tracingContext, MAPApplicationContextVersion.version1);

        }

        return null;
    }

    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

        // if an application-context-name different from version 1 is
        // received in a syntactically correct TC-
        // BEGIN indication primitive but is not acceptable from a load
        // control point of view, the MAP PM
        // shall ignore this dialogue request. The MAP-user is not informed.
//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // TODO: we need to care of it
//        }

        MAPDialogOamImpl mapDialogOamImpl = (MAPDialogOamImpl) mapDialog;

        Long ocValue = operationCode.getLocalOperationCode();
        if (ocValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);
        MAPApplicationContextName acn = mapDialog.getApplicationContext().getApplicationContextName();
        int mapDialogAApplicationContextVersion = mapDialog.getApplicationContext().getApplicationContextVersion().getVersion();
        int operationCodeValueInt = (int) (long) ocValue;

        switch (operationCodeValueInt) {
            case MAPOperationCode.activateTraceMode:
                if (acn == MAPApplicationContextName.tracingContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processActivateTraceModeRequest(parameter, mapDialogOamImpl, invokeId);
                    else
                        this.processActivateTraceModeResponse(parameter, mapDialogOamImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.sendIMSI:
                if (acn == MAPApplicationContextName.imsiRetrievalContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processSendImsiRequest(parameter, mapDialogOamImpl, invokeId);
                    else
                        this.processSendImsiResponse(parameter, mapDialogOamImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            default:
                throw new MAPParsingComponentException("MAPServiceOam: unknown incoming operation code: " + operationCodeValueInt,
                        MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void processActivateTraceModeRequest(Parameter parameter, MAPDialogOamImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding processActivateTraceModeRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding processActivateTraceModeRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ActivateTraceModeRequestImpl_Oam activateTraceModeRequestOamIndication = new ActivateTraceModeRequestImpl_Oam();
        activateTraceModeRequestOamIndication.decodeData(ais, buf.length);

        activateTraceModeRequestOamIndication.setInvokeId(invokeId);
        activateTraceModeRequestOamIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(activateTraceModeRequestOamIndication);
                ((MAPServiceOamListener) serLis).onActivateTraceModeRequest_Oam(activateTraceModeRequestOamIndication);
            } catch (Exception e) {
                loger.error("Error processing ActivateTraceModeRequestOamIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processActivateTraceModeResponse(Parameter parameter, MAPDialogOamImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        ActivateTraceModeResponseImpl_Oam activateTraceModeResponseOamIndication = new ActivateTraceModeResponseImpl_Oam();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding processActivateTraceModeResponse: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf);
            activateTraceModeResponseOamIndication.decodeData(ais, buf.length);
        }

        activateTraceModeResponseOamIndication.setInvokeId(invokeId);
        activateTraceModeResponseOamIndication.setMAPDialog(mapDialogImpl);
        activateTraceModeResponseOamIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                ((MAPServiceOamListener) serLis).onActivateTraceModeResponse_Oam(activateTraceModeResponseOamIndication);
            } catch (Exception e) {
                loger.error("Error processing ActivateTraceModeResponseOamIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processSendImsiRequest(Parameter parameter, MAPDialogOamImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding processSendImsiRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding processSendImsiRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendImsiRequestImpl sendImsiRequestIndication = new SendImsiRequestImpl();
        sendImsiRequestIndication.decodeData(ais, buf.length);

        sendImsiRequestIndication.setInvokeId(invokeId);
        sendImsiRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendImsiRequestIndication);
                ((MAPServiceOamListener) serLis).onSendImsiRequest(sendImsiRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing SendImsiRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void processSendImsiResponse(Parameter parameter, MAPDialogOamImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding processSendImsiResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding processSendImsiResponse: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendImsiResponseImpl sendImsiResponseIndication = new SendImsiResponseImpl();
        sendImsiResponseIndication.decodeData(ais, buf.length);

        sendImsiResponseIndication.setInvokeId(invokeId);
        sendImsiResponseIndication.setMAPDialog(mapDialogImpl);
        sendImsiResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendImsiResponseIndication);
                ((MAPServiceOamListener) serLis).onSendImsiResponse(sendImsiResponseIndication);
            } catch (Exception e) {
                loger.error("Error processing SendImsiResponseIndication: " + e.getMessage(), e);
            }
        }
    }

}
