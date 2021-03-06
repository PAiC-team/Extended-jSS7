package org.restcomm.protocols.ss7.map.service.lsm;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.MAPServiceBaseImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener;
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
 * @author amit bhayani
 *
 */
public class MAPServiceLsmImpl extends MAPServiceBaseImpl implements MAPServiceLsm {

    /**
     * @param mapProviderImpl
     */
    public MAPServiceLsmImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm#createNewDialog
     * (org.restcomm.protocols.ss7.map.api.MAPApplicationContext, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress,
     * org.restcomm.protocols.ss7.map.api.dialog.AddressString, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress,
     * org.restcomm.protocols.ss7.map.api.dialog.AddressString)
     */
    public MAPDialogLsm createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogLsm createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTrId) throws MAPException {
        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogLsm because MAPServiceLsm is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);

        MAPDialogLsmImpl dialog = new MAPDialogLsmImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this, origReference,
                destReference);

        this.putMAPDialogIntoCollection(dialog);

        return dialog;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm# addMAPServiceListener
     * (org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener)
     */
    public void addMAPServiceListener(MAPServiceLsmListener mapServiceLsmListener) {
        super.addMAPServiceListener(mapServiceLsmListener);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm# removeMAPServiceListener
     * (org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener)
     */
    public void removeMAPServiceListener(MAPServiceLsmListener mapServiceLsmListener) {
        super.removeMAPServiceListener(mapServiceLsmListener);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.MAPServiceBaseImpl#createNewDialogIncoming
     * (org.restcomm.protocols.ss7.map.api.MAPApplicationContext, org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    @Override
    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext mapApplicationContext, Dialog tcapDialog) {
        return new MAPDialogLsmImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#isServingService(org
     * .mobicents.protocols.ss7.map.api.MAPApplicationContext)
     */
    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {

        int vers = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (mapDialogApplicationContext.getApplicationContextName()) {
            case locationSvcEnquiryContext:
            case locationSvcGatewayContext:
                if (vers == 3) {
                    return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
                } else if (vers > 3) {
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#processComponent(org
     * .mobicents.protocols.ss7.tcap.asn.comp.ComponentType, org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode,
     * org.restcomm.protocols.ss7.tcap.asn.comp.Parameter, org.restcomm.protocols.ss7.map.api.MAPDialog, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, MAPDialog mapDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws MAPParsingComponentException {

        // if an application-context-name different from version 1 is
        // received in a syntactically correct TC-
        // BEGIN indication primitive but is not acceptable from a load
        // control point of view, the MAP PM
        // shall ignore this dialogue request. The MAP-user is not informed.
//        if (componentType == ComponentType.Invoke && this.mapProviderImpl.isCongested()) {
//            // we reject all lms services when congestion
//            return;
//        }

        MAPDialogLsmImpl mAPDialogLsmImpl = (MAPDialogLsmImpl) mapDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);

        long operationCodeValueInt = operationCodeValue;
        int operationCodeValueInt2 = (int) operationCodeValueInt;
        switch (operationCodeValueInt2) {
            case MAPOperationCode.provideSubscriberLocation:
                if (componentType == ComponentType.Invoke) {
                    this.provideSubscriberLocationReq(parameter, mAPDialogLsmImpl, invokeId);
                } else {
                    this.provideSubscriberLocationRes(parameter, mAPDialogLsmImpl, invokeId,
                            componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.subscriberLocationReport:
                if (componentType == ComponentType.Invoke) {
                    this.subscriberLocationReportReq(parameter, mAPDialogLsmImpl, invokeId);
                } else {
                    this.subscriberLocationReportRes(parameter, mAPDialogLsmImpl, invokeId,
                            componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.sendRoutingInfoForLCS:
                if (componentType == ComponentType.Invoke) {
                    this.sendRoutingInfoForLCSReq(parameter, mAPDialogLsmImpl, invokeId);
                } else {
                    this.sendRoutingInfoForLCSRes(parameter, mAPDialogLsmImpl, invokeId, componentType == ComponentType.ReturnResult);
                }
                break;
            default:
                throw new MAPParsingComponentException("MAPServiceLsm: unknown incoming operation code: " + operationCodeValueInt,
                        MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void provideSubscriberLocationReq(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding provideSubscriberLocationRes: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding provideSubscriberLocationReq: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ProvideSubscriberLocationRequestImpl ind = new ProvideSubscriberLocationRequestImpl();
        ind.decodeData(ais, buf.length);

        ind.setInvokeId(invokeId);
        ind.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(ind);
            ((MAPServiceLsmListener) serLis).onProvideSubscriberLocationRequest(ind);
        }

    }

    private void provideSubscriberLocationRes(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding provideSubscriberLocationRes: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding provideSubscriberLocationRes: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ProvideSubscriberLocationResponseImpl provideSubsLoctResInd = new ProvideSubscriberLocationResponseImpl();
        provideSubsLoctResInd.decodeData(ais, buf.length);

        provideSubsLoctResInd.setInvokeId(invokeId);
        provideSubsLoctResInd.setMAPDialog(mapDialogImpl);
        provideSubsLoctResInd.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(provideSubsLoctResInd);
            ((MAPServiceLsmListener) serLis).onProvideSubscriberLocationResponse(provideSubsLoctResInd);
        }

    }

    private void subscriberLocationReportReq(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding subscriberLocationReport: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding subscriberLocationReportReq: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SubscriberLocationReportRequestImpl ind = new SubscriberLocationReportRequestImpl();
        ind.decodeData(ais, buf.length);

        ind.setInvokeId(invokeId);
        ind.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(ind);
            ((MAPServiceLsmListener) serLis).onSubscriberLocationReportRequest(ind);
        }
    }

    private void subscriberLocationReportRes(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding subscriberLocationReport: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding subscriberLocationReportRes: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SubscriberLocationReportResponseImpl resInd = new SubscriberLocationReportResponseImpl();
        resInd.decodeData(ais, buf.length);

        resInd.setInvokeId(invokeId);
        resInd.setMAPDialog(mapDialogImpl);
        resInd.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(resInd);
            ((MAPServiceLsmListener) serLis).onSubscriberLocationReportResponse(resInd);
        }
    }

    private void sendRoutingInfoForLCSReq(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForLCS: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForLCSReq: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendRoutingInfoForLCSRequestImpl ind = new SendRoutingInfoForLCSRequestImpl();
        ind.decodeData(ais, buf.length);

        ind.setInvokeId(invokeId);
        ind.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(ind);
            ((MAPServiceLsmListener) serLis).onSendRoutingInfoForLCSRequest(ind);
        }
    }

    private void sendRoutingInfoForLCSRes(Parameter parameter, MAPDialogLsmImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        if (parameter == null) {
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForLCS: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding sendRoutingInfoForLCSRes: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendRoutingInfoForLCSResponseImpl resInd = new SendRoutingInfoForLCSResponseImpl();
        resInd.decodeData(ais, buf.length);

        resInd.setInvokeId(invokeId);
        resInd.setMAPDialog(mapDialogImpl);
        resInd.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            serLis.onMAPMessage(resInd);
            ((MAPServiceLsmListener) serLis).onSendRoutingInfoForLCSResponse(resInd);
        }
    }

}
