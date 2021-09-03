package org.restcomm.protocols.ss7.cap.service.gprs;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.CAPDialogImpl;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.CAPServiceBaseImpl;
import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPDialogGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPServiceGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPServiceGprsListener;
import org.restcomm.protocols.ss7.cap.dialog.ServingCheckDataImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPServiceGprsImpl extends CAPServiceBaseImpl implements CAPServiceGprs {

    protected Logger logger = Logger.getLogger(CAPServiceGprsImpl.class);

    public CAPServiceGprsImpl(CAPProviderImpl capProviderImpl) {
        super(capProviderImpl);
    }

    @Override
    public CAPDialogGprs createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress,
            SccpAddress sccpCalledPartyAddress) throws CAPException {
        return this.createNewDialog(capApplicationContext, sccpCallingPartyAddress, sccpCalledPartyAddress, null);
    }

    @Override
    public CAPDialogGprs createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress,
            SccpAddress sccpCalledPartyAddress, Long localTrId) throws CAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new CAPException("Cannot create CAPDialogGprs because CAPServiceGprs is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        CAPDialogGprsImpl dialog = new CAPDialogGprsImpl(capApplicationContext, tcapDialog, this.capProviderImpl, this);

        this.putCAPDialogIntoCollection(dialog);

        return dialog;
    }

    @Override
    public void addCAPServiceListener(CAPServiceGprsListener capServiceListener) {
        super.addCAPServiceListener(capServiceListener);
    }

    @Override
    public void removeCAPServiceListener(CAPServiceGprsListener capServiceListener) {
        super.removeCAPServiceListener(capServiceListener);
    }

    @Override
    protected CAPDialogImpl createNewDialogIncoming(CAPApplicationContext capApplicationContext, Dialog tcapDialog) {
        return new CAPDialogGprsImpl(capApplicationContext, tcapDialog, this.capProviderImpl, this);
    }

    @Override
    public ServingCheckData isServingService(CAPApplicationContext capDialogApplicationContext) {
        switch (capDialogApplicationContext) {
            case CapV3_gprsSSF_gsmSCF:
            case CapV3_gsmSCF_gprsSSF:
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, CAPDialog capDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws CAPParsingComponentException {

        CAPDialogGprsImpl capDialogGprsImpl = (CAPDialogGprsImpl) capDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        CAPApplicationContext acn = capDialog.getApplicationContext();
        int operationCodeValueInt = (int) (long) operationCodeValue;

        switch (operationCodeValueInt) {
            case CAPOperationCode.initialDPGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF) {
                    if (componentType == ComponentType.Invoke) {
                        this.initialDpGprsRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.requestReportGPRSEvent:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.requestReportGPRSEventRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.applyChargingGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.applyChargingGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.entityReleasedGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.entityReleasedGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }

                    if (componentType == ComponentType.ReturnResultLast) {
                        this.entityReleasedGPRSResponse(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.connectGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.connectGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.continueGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.continueGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.releaseGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.releaseGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.resetTimerGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.resetTimerGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.furnishChargingInformationGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.furnishChargingInformationGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.cancelGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.cancelGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.sendChargingInformationGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.sendChargingInformationGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.applyChargingReportGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.applyChargingReportGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        this.applyChargingReportGPRSResponse(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.eventReportGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.eventReportGPRSRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        this.eventReportGPRSResponse(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            case CAPOperationCode.activityTestGPRS:
                if (acn == CAPApplicationContext.CapV3_gprsSSF_gsmSCF || acn == CAPApplicationContext.CapV3_gsmSCF_gprsSSF) {
                    if (componentType == ComponentType.Invoke) {
                        this.activityTestRequest(parameter, capDialogGprsImpl, invokeId);
                    }
                    if (componentType == ComponentType.ReturnResultLast) {
                        this.activityTestResponse(parameter, capDialogGprsImpl, invokeId);
                    }
                }
                break;
            default:
                throw new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void initialDpGprsRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding initialDpGprsRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding initialDpGprsRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        InitialDpGprsRequestImpl initialDpGprsRequestIndication = new InitialDpGprsRequestImpl();
        initialDpGprsRequestIndication.decodeData(ais, buf.length);

        initialDpGprsRequestIndication.setInvokeId(invokeId);
        initialDpGprsRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(initialDpGprsRequestIndication);
                ((CAPServiceGprsListener) serLis).onInitialDpGprsRequest(initialDpGprsRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing initialDpRequest: " + e.getMessage(), e);
            }
        }
    }

    private void requestReportGPRSEventRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding requestReportGPRSEventRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding requestReportGPRSEventRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        RequestReportGPRSEventRequestImpl requestReportGPRSEventRequestIndication = new RequestReportGPRSEventRequestImpl();
        requestReportGPRSEventRequestIndication.decodeData(ais, buf.length);

        requestReportGPRSEventRequestIndication.setInvokeId(invokeId);
        requestReportGPRSEventRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(requestReportGPRSEventRequestIndication);
                ((CAPServiceGprsListener) serLis).onRequestReportGPRSEventRequest(requestReportGPRSEventRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing requestReportGPRSEventRequest: " + e.getMessage(), e);
            }
        }
    }

    private void applyChargingGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding applyChargingGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException(
                    "Error while decoding applyChargingGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ApplyChargingGPRSRequestImpl applyChargingGPRSRequestIndication = new ApplyChargingGPRSRequestImpl();
        applyChargingGPRSRequestIndication.decodeData(ais, buf.length);

        applyChargingGPRSRequestIndication.setInvokeId(invokeId);
        applyChargingGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(applyChargingGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onApplyChargingGPRSRequest(applyChargingGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing applyChargingGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void entityReleasedGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding entityReleasedGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding entityReleasedGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        EntityReleasedGPRSRequestImpl entityReleasedGPRSRequestIndication = new EntityReleasedGPRSRequestImpl();
        entityReleasedGPRSRequestIndication.decodeData(ais, buf.length);

        entityReleasedGPRSRequestIndication.setInvokeId(invokeId);
        entityReleasedGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(entityReleasedGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onEntityReleasedGPRSRequest(entityReleasedGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing entityReleasedGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void entityReleasedGPRSResponse(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        EntityReleasedGPRSResponseImpl entityReleasedGPRSResponseIndication = new EntityReleasedGPRSResponseImpl();

        entityReleasedGPRSResponseIndication.setInvokeId(invokeId);
        entityReleasedGPRSResponseIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(entityReleasedGPRSResponseIndication);
                ((CAPServiceGprsListener) serLis).onEntityReleasedGPRSResponse(entityReleasedGPRSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing entityReleasedGPRSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void connectGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding connectGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding connectGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ConnectGPRSRequestImpl connectGPRSRequestIndication = new ConnectGPRSRequestImpl();
        connectGPRSRequestIndication.decodeData(ais, buf.length);

        connectGPRSRequestIndication.setInvokeId(invokeId);
        connectGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(connectGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onConnectGPRSRequest(connectGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing connectGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void continueGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding continueGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding continueGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ContinueGPRSRequestImpl continueGPRSRequestIndication = new ContinueGPRSRequestImpl();
        continueGPRSRequestIndication.decodeData(ais, buf.length);

        continueGPRSRequestIndication.setInvokeId(invokeId);
        continueGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(continueGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onContinueGPRSRequest(continueGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing continueGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void releaseGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding releaseGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding releaseGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ReleaseGPRSRequestImpl releaseGPRSRequestIndication = new ReleaseGPRSRequestImpl();
        releaseGPRSRequestIndication.decodeData(ais, buf.length);

        releaseGPRSRequestIndication.setInvokeId(invokeId);
        releaseGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(releaseGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onReleaseGPRSRequest(releaseGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing releaseGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void resetTimerGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding resetTimerGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding resetTimerGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ResetTimerGPRSRequestImpl resetTimerGPRSRequestIndication = new ResetTimerGPRSRequestImpl();
        resetTimerGPRSRequestIndication.decodeData(ais, buf.length);

        resetTimerGPRSRequestIndication.setInvokeId(invokeId);
        resetTimerGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(resetTimerGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onResetTimerGPRSRequest(resetTimerGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing resetTimerGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void furnishChargingInformationGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding furnishChargingInformationGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding furnishChargingInformationGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        FurnishChargingInformationGPRSRequestImpl furnishChargingInformationGPRSRequestIndication = new FurnishChargingInformationGPRSRequestImpl();
        furnishChargingInformationGPRSRequestIndication.decodeData(ais, buf.length);

        furnishChargingInformationGPRSRequestIndication.setInvokeId(invokeId);
        furnishChargingInformationGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(furnishChargingInformationGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onFurnishChargingInformationGPRSRequest(furnishChargingInformationGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing furnishChargingInformationGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void cancelGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding cancelGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding cancelGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        CancelGPRSRequestImpl cancelGPRSRequestIndication = new CancelGPRSRequestImpl();
        cancelGPRSRequestIndication.decodeData(ais, buf.length);

        cancelGPRSRequestIndication.setInvokeId(invokeId);
        cancelGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(cancelGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onCancelGPRSRequest(cancelGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing cancelGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void sendChargingInformationGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding sendChargingInformationGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding sendChargingInformationGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        SendChargingInformationGPRSRequestImpl sendChargingInformationGPRSRequestIndication = new SendChargingInformationGPRSRequestImpl();
        sendChargingInformationGPRSRequestIndication.decodeData(ais, buf.length);

        sendChargingInformationGPRSRequestIndication.setInvokeId(invokeId);
        sendChargingInformationGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(sendChargingInformationGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onSendChargingInformationGPRSRequest(sendChargingInformationGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing sendChargingInformationGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void applyChargingReportGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding applyChargingReportGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding applyChargingReportGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ApplyChargingReportGPRSRequestImpl applyChargingReportGPRSRequestIndication = new ApplyChargingReportGPRSRequestImpl();
        applyChargingReportGPRSRequestIndication.decodeData(ais, buf.length);

        applyChargingReportGPRSRequestIndication.setInvokeId(invokeId);
        applyChargingReportGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(applyChargingReportGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onApplyChargingReportGPRSRequest(applyChargingReportGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing applyChargingReportGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void applyChargingReportGPRSResponse(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        ApplyChargingReportGPRSResponseImpl applyChargingReportGPRSResponseIndication = new ApplyChargingReportGPRSResponseImpl();

        applyChargingReportGPRSResponseIndication.setInvokeId(invokeId);
        applyChargingReportGPRSResponseIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(applyChargingReportGPRSResponseIndication);
                ((CAPServiceGprsListener) serLis).onApplyChargingReportGPRSResponse(applyChargingReportGPRSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing applyChargingReportGPRSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void eventReportGPRSRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding eventReportGPRSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding eventReportGPRSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        EventReportGPRSRequestImpl eventReportGPRSRequestIndication = new EventReportGPRSRequestImpl();
        eventReportGPRSRequestIndication.decodeData(ais, buf.length);

        eventReportGPRSRequestIndication.setInvokeId(invokeId);
        eventReportGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(eventReportGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onEventReportGPRSRequest(eventReportGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing eventReportGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void eventReportGPRSResponse(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        EventReportGPRSResponseImpl eventReportGPRSResponseIndication = new EventReportGPRSResponseImpl();

        eventReportGPRSResponseIndication.setInvokeId(invokeId);
        eventReportGPRSResponseIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(eventReportGPRSResponseIndication);
                ((CAPServiceGprsListener) serLis).onEventReportGPRSResponse(eventReportGPRSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing eventReportGPRSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void activityTestRequest(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        ActivityTestGPRSRequestImpl activityTestGPRSRequestIndication = new ActivityTestGPRSRequestImpl();

        activityTestGPRSRequestIndication.setInvokeId(invokeId);
        activityTestGPRSRequestIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(activityTestGPRSRequestIndication);
                ((CAPServiceGprsListener) serLis).onActivityTestGPRSRequest(activityTestGPRSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing activityTestGPRSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void activityTestResponse(Parameter parameter, CAPDialogGprsImpl capDialogGprs, Long invokeId)
            throws CAPParsingComponentException {

        ActivityTestGPRSResponseImpl activityTestGPRSResponseIndication = new ActivityTestGPRSResponseImpl();

        activityTestGPRSResponseIndication.setInvokeId(invokeId);
        activityTestGPRSResponseIndication.setCAPDialog(capDialogGprs);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(activityTestGPRSResponseIndication);
                ((CAPServiceGprsListener) serLis).onActivityTestGPRSResponse(activityTestGPRSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing activityTestGPRSResponse: " + e.getMessage(), e);
            }
        }
    }

}
