
package org.restcomm.protocols.ss7.cap.service.sms;

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
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSmsListener;
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
public class CAPServiceSmsImpl extends CAPServiceBaseImpl implements CAPServiceSms {

    protected Logger loger = Logger.getLogger(CAPServiceSmsImpl.class);

    public CAPServiceSmsImpl(CAPProviderImpl capProviderImpl) {
        super(capProviderImpl);
    }

    @Override
    public CAPDialogSms createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress,
            SccpAddress sccpCalledPartyAddress) throws CAPException {
        return this.createNewDialog(capApplicationContext, sccpCallingPartyAddress, sccpCalledPartyAddress, null);
    }

    @Override
    public CAPDialogSms createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress,
            SccpAddress sccpCalledPartyAddress, Long localTrId) throws CAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new CAPException("Cannot create CAPDialogSms because CAPServiceSms is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        CAPDialogSmsImpl dialog = new CAPDialogSmsImpl(capApplicationContext, tcapDialog, this.capProviderImpl, this);

        this.putCAPDialogIntoCollection(dialog);

        return dialog;
    }

    @Override
    public void addCAPServiceListener(CAPServiceSmsListener capServiceListener) {
        super.addCAPServiceListener(capServiceListener);
    }

    @Override
    public void removeCAPServiceListener(CAPServiceSmsListener capServiceListener) {
        super.removeCAPServiceListener(capServiceListener);
    }

    @Override
    protected CAPDialogImpl createNewDialogIncoming(CAPApplicationContext appCntx, Dialog tcapDialog) {
        return new CAPDialogSmsImpl(appCntx, tcapDialog, this.capProviderImpl, this);
    }

    @Override
    public ServingCheckData isServingService(CAPApplicationContext dialogApplicationContext) {
        switch (dialogApplicationContext) {
        case CapV3_cap3_sms:
        case CapV4_cap4_sms:
            return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
        }

        return new ServingCheckDataImpl(ServingCheckResult.AC_NotServing);
    }

    @Override
    public void processComponent(ComponentType componentType, OperationCode operationCode, Parameter parameter, CAPDialog capDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws CAPParsingComponentException {

        CAPDialogSmsImpl capDialogSmsImpl = (CAPDialogSmsImpl) capDialog;

        Long ocValue = operationCode.getLocalOperationCode();
        if (ocValue == null)
            new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        CAPApplicationContext acn = capDialog.getApplicationContext();
        int ocValueInt = (int) (long) ocValue;

        switch (ocValueInt) {
        case CAPOperationCode.connectSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.connectSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.eventReportSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.eventReportSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.furnishChargingInformationSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.furnishChargingInformationSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.initialDPSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.initialDPSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.releaseSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.releaseSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.requestReportSMSEvent:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.requestReportSMSEventRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.resetTimerSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.resetTimerSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        case CAPOperationCode.continueSMS:
            if (acn == CAPApplicationContext.CapV3_cap3_sms || acn == CAPApplicationContext.CapV4_cap4_sms) {
                if (componentType == ComponentType.Invoke) {
                    this.continueSMSRequest(parameter, capDialogSmsImpl, invokeId);
                }
            }
            break;
        default:
            throw new CAPParsingComponentException("", CAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void connectSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException(
                    "Error while decoding connectSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding connectSMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ConnectSMSRequestImpl connectSMSRequestIndication = new ConnectSMSRequestImpl();
        connectSMSRequestIndication.decodeData(ais, buf.length);

        connectSMSRequestIndication.setInvokeId(invokeId);
        connectSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(connectSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onConnectSMSRequest(connectSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing connectSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void eventReportSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding EventReportSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding EventReportSMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        EventReportSMSRequestImpl eventReportSMSRequestIndication = new EventReportSMSRequestImpl();
        eventReportSMSRequestIndication.decodeData(ais, buf.length);

        eventReportSMSRequestIndication.setInvokeId(invokeId);
        eventReportSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(eventReportSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onEventReportSMSRequest(eventReportSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing EventReportSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void furnishChargingInformationSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding FurnishChargingInformationSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding FurnishChargingInformationSMSRequest: Bad tag or tagClass or parameter is not a primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        FurnishChargingInformationSMSRequestImpl furnishChargingInformationSMSRequestIndication = new FurnishChargingInformationSMSRequestImpl();
        furnishChargingInformationSMSRequestIndication.decodeData(ais, buf.length);

        furnishChargingInformationSMSRequestIndication.setInvokeId(invokeId);
        furnishChargingInformationSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(furnishChargingInformationSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onFurnishChargingInformationSMSRequest(furnishChargingInformationSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing FurnishChargingInformationSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void initialDPSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding InitialDPSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding InitialDPSMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        InitialDPSMSRequestImpl initialDPSMSRequestIndication = new InitialDPSMSRequestImpl();
        initialDPSMSRequestIndication.decodeData(ais, buf.length);

        initialDPSMSRequestIndication.setInvokeId(invokeId);
        initialDPSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(initialDPSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onInitialDPSMSRequest(initialDPSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing InitialDPSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void releaseSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding ReleaseSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || !parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding ReleaseSMSRequest: Bad tag or tagClass or parameter is not primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ReleaseSMSRequestImpl releaseSMSRequestIndication = new ReleaseSMSRequestImpl();
        releaseSMSRequestIndication.decodeData(ais, buf.length);

        releaseSMSRequestIndication.setInvokeId(invokeId);
        releaseSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(releaseSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onReleaseSMSRequest(releaseSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing ReleaseSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void requestReportSMSEventRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding RequestReportSMSEventRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding RequestReportSMSEventRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        RequestReportSMSEventRequestImpl requestReportSMSEventRequestIndication = new RequestReportSMSEventRequestImpl();
        requestReportSMSEventRequestIndication.decodeData(ais, buf.length);

        requestReportSMSEventRequestIndication.setInvokeId(invokeId);
        requestReportSMSEventRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(requestReportSMSEventRequestIndication);
                ((CAPServiceSmsListener) serLis).onRequestReportSMSEventRequest(requestReportSMSEventRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing RequestReportSMSEventRequest: " + e.getMessage(), e);
            }
        }
    }

    private void resetTimerSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId) throws CAPParsingComponentException {

        if (parameter == null)
            throw new CAPParsingComponentException("Error while decoding ResetTimerSMSRequest: Parameter is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL
                || parameter.isPrimitive())
            throw new CAPParsingComponentException("Error while decoding ResetTimerSMSRequest: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), CAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf);
        ResetTimerSMSRequestImpl resetTimerSMSRequestIndication = new ResetTimerSMSRequestImpl();
        resetTimerSMSRequestIndication.decodeData(ais, buf.length);

        resetTimerSMSRequestIndication.setInvokeId(invokeId);
        resetTimerSMSRequestIndication.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(resetTimerSMSRequestIndication);
                ((CAPServiceSmsListener) serLis).onResetTimerSMSRequest(resetTimerSMSRequestIndication);
            } catch (Exception e) {
                loger.error("Error processing ResetTimerSMSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void continueSMSRequest(Parameter parameter, CAPDialogSmsImpl capDialogSms, Long invokeId)
            throws CAPParsingComponentException {

        ContinueSMSRequestImpl ind = new ContinueSMSRequestImpl();

        ind.setInvokeId(invokeId);
        ind.setCAPDialog(capDialogSms);

        for (CAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onCAPMessage(ind);
                ((CAPServiceSmsListener) serLis).onContinueSMSRequest(ind);
            } catch (Exception e) {
                loger.error("Error processing continueSMSRequest: " + e.getMessage(), e);
            }
        }
    }

}
