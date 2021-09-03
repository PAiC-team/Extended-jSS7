
package org.restcomm.protocols.ss7.map.service.pdpContextActivation;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.MAPServiceBaseImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivationListener;
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
public class MAPServicePdpContextActivationImpl extends MAPServiceBaseImpl implements MAPServicePdpContextActivation {

    protected Logger logger = Logger.getLogger(MAPServicePdpContextActivationImpl.class);

    public MAPServicePdpContextActivationImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /*
     * Creating a new outgoing MAP PdpContextActivation dialog and adding it to the MAPProvider.dialog collection
     */
    public MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogPdpContextActivation because MAPServicePdpContextActivation is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTransactionId);
        MAPDialogPdpContextActivationImpl dialog = new MAPDialogPdpContextActivationImpl(mapApplicationContext, tcapDialog,
                this.mapProviderImpl, this, origReference, destReference);

        this.putMAPDialogIntoCollection(dialog);

        return dialog;
    }

    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext mapApplicationContext, Dialog tcapDialog) {
        return new MAPDialogPdpContextActivationImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    public void addMAPServiceListener(MAPServicePdpContextActivationListener mapServicePdpContextActivationListener) {
        super.addMAPServiceListener(mapServicePdpContextActivationListener);
    }

    public void removeMAPServiceListener(MAPServicePdpContextActivationListener mapServicePdpContextActivationListener) {
        super.removeMAPServiceListener(mapServicePdpContextActivationListener);
    }

    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName mapApplicationContextName = mapDialogApplicationContext.getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (mapApplicationContextName) {
        case gprsLocationInfoRetrievalContext:
            if (mapDialogApplicationContextVersion >= 3 && mapDialogApplicationContextVersion <= 4) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 4) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 2;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);

    }

    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

        // if an application-context-name different from version 1 is
        // received in a syntactically correct TC-
        // BEGIN indication primitive but is not acceptable from a load
        // control point of view, the MAP PM
        // shall ignore this dialogue request. The MAP-user is not informed.
//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // TODO: we need to care of congestion control
//        }

        MAPDialogPdpContextActivationImpl mapDialogPdpContextActivationImpl = (MAPDialogPdpContextActivationImpl) mapDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);
        MAPApplicationContextName mapApplicationContextName = mapDialog.getApplicationContext().getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialog.getApplicationContext().getApplicationContextVersion().getVersion();
        int operationCodeValueInt = (int) (long) operationCodeValue;

        switch (operationCodeValueInt) {
            case MAPOperationCode.sendRoutingInfoForGprs:
                if (mapApplicationContextName == MAPApplicationContextName.gprsLocationInfoRetrievalContext
                        || mapApplicationContextName == MAPApplicationContextName.shortMsgMTRelayContext) {
                    if (componentType == ComponentType.Invoke)
                        this.sendRoutingInfoForGprsRequest(parameter, mapDialogPdpContextActivationImpl, invokeId);
                    else
                        this.sendRoutingInfoForGprsResponse(parameter, mapDialogPdpContextActivationImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            default:
                throw new MAPParsingComponentException("MAPServicePdpContextActivation: unknown incoming operation code: "
                        + operationCodeValueInt, MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void sendRoutingInfoForGprsRequest(Parameter parameter, MAPDialogPdpContextActivationImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForGprsRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForGprsRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendRoutingInfoForGprsRequestImpl sendRoutingInfoForGprsRequestIndication = new SendRoutingInfoForGprsRequestImpl();
        sendRoutingInfoForGprsRequestIndication.decodeData(ais, buf.length);

        sendRoutingInfoForGprsRequestIndication.setInvokeId(invokeId);
        sendRoutingInfoForGprsRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInfoForGprsRequestIndication);
                ((MAPServicePdpContextActivationListener) serLis).onSendRoutingInfoForGprsRequest(sendRoutingInfoForGprsRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onSendRoutingInfoForGprsRequest: " + e.getMessage(), e);
            }
        }
    }

    private void sendRoutingInfoForGprsResponse(Parameter parameter, MAPDialogPdpContextActivationImpl mapDialogImpl,
            Long invokeId, boolean returnResultNotLast) throws MAPParsingComponentException {

        SendRoutingInfoForGprsResponseImpl sendRoutingInfoForGprsResponseIndication = new SendRoutingInfoForGprsResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForGprsResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForGprsResponse: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        sendRoutingInfoForGprsResponseIndication.decodeData(ais, buf.length);

        sendRoutingInfoForGprsResponseIndication.setInvokeId(invokeId);
        sendRoutingInfoForGprsResponseIndication.setMAPDialog(mapDialogImpl);
        sendRoutingInfoForGprsResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(sendRoutingInfoForGprsResponseIndication);
                ((MAPServicePdpContextActivationListener) serLis).onSendRoutingInfoForGprsResponse(sendRoutingInfoForGprsResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onSendRoutingInfoForGprsResponse: " + e.getMessage(), e);
            }
        }
    }
}
