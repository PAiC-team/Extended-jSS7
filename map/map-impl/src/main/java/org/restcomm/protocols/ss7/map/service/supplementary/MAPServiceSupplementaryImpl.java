
package org.restcomm.protocols.ss7.map.service.supplementary;

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
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementaryListener;
import org.restcomm.protocols.ss7.map.dialog.ServingCheckDataImpl;
import org.restcomm.protocols.ss7.map.service.sms.MAPServiceSmsImpl;
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
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class MAPServiceSupplementaryImpl extends MAPServiceBaseImpl implements MAPServiceSupplementary {

    private static final Logger logger = Logger.getLogger(MAPServiceSmsImpl.class);

    public MAPServiceSupplementaryImpl(MAPProviderImpl mapProviderImpl) {
        super(mapProviderImpl);
    }

    /**
     * Creating a new outgoing MAP Supplementary dialog and adding it to the MAPProvider.dialog collection
     *
     */
    public MAPDialogSupplementary createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference) throws MAPException {
        return this.createNewDialog(mapApplicationContext, sccpCallingPartyAddress, origReference, sccpCalledPartyAddress, destReference, null);
    }

    public MAPDialogSupplementary createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        // We cannot create a dialog if the service is not activated
        if (!this.isActivated())
            throw new MAPException("Cannot create MAPDialogSupplementary because MAPServiceSupplementary is not activated");

        Dialog tcapDialog = this.createNewTCAPDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTrId);
        MAPDialogSupplementaryImpl dialog = new MAPDialogSupplementaryImpl(mapApplicationContext, tcapDialog, this.mapProviderImpl, this,
                origReference, destReference);

        this.putMAPDialogIntoCollection(dialog);

        return dialog;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.MAPServiceBaseImpl#createNewDialogIncoming
     * (org.restcomm.protocols.ss7.map.api.MAPApplicationContext, org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    protected MAPDialogImpl createNewDialogIncoming(MAPApplicationContext appCntx, Dialog tcapDialog) {
        return new MAPDialogSupplementaryImpl(appCntx, tcapDialog, this.mapProviderImpl, this, null, null);
    }

    public void addMAPServiceListener(MAPServiceSupplementaryListener mapServiceSupplementaryListener) {
        super.addMAPServiceListener(mapServiceSupplementaryListener);
    }

    public void removeMAPServiceListener(MAPServiceSupplementaryListener mapServiceSupplementaryListener) {
        super.removeMAPServiceListener(mapServiceSupplementaryListener);
    }

    public ServingCheckData isServingService(MAPApplicationContext mapDialogApplicationContext) {
        MAPApplicationContextName mapApplicationContextName = mapDialogApplicationContext.getApplicationContextName();
        int mapDialogApplicationContextVersion = mapDialogApplicationContext.getApplicationContextVersion().getVersion();

        switch (mapApplicationContextName) {
        case networkUnstructuredSsContext:
            if (mapDialogApplicationContextVersion == 2) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 2) {
                long[] altOid = mapDialogApplicationContext.getOID();
                altOid[7] = 2;
                ApplicationContextName alt = TcapFactory.createApplicationContextName(altOid);
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect, alt);
            } else {
                return new ServingCheckDataImpl(ServingCheckResult.AC_VersionIncorrect);
            }
        case networkFunctionalSsContext:
            if (mapDialogApplicationContextVersion == 2) {
                return new ServingCheckDataImpl(ServingCheckResult.AC_Serving);
            } else if (mapDialogApplicationContextVersion > 2) {
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

    public long[] getLinkedOperationList(long operCode) {
        if (operCode == MAPOperationCode.registerPassword) {
            return new long[] { MAPOperationCode.getPassword };
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
//            // we reject all supplementary services when congestion
//            return;
//        }

        MAPDialogSupplementaryImpl mapDialogSupplementaryImpl = (MAPDialogSupplementaryImpl) mapDialog;

        Long operationCodeValue = operationCode.getLocalOperationCode();
        if (operationCodeValue == null)
            new MAPParsingComponentException("", MAPParsingComponentExceptionReason.UnrecognizedOperation);

        long operationCodeValueInt = operationCodeValue;
        int operationCodeValueInt2 = (int) operationCodeValueInt;
        MAPApplicationContextName mapApplicationContextName = mapDialog.getApplicationContext().getApplicationContextName();
        switch (operationCodeValueInt2) {
            case MAPOperationCode.registerSS:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.registerSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.registerSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.eraseSS:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.eraseSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.eraseSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.activateSS:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.activateSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.activateSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.deactivateSS:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.deactivateSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.deactivateSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.interrogateSS:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.interrogateSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.interrogateSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.registerPassword:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.registerPasswordRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.registerPasswordResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.getPassword:
                if (mapApplicationContextName == MAPApplicationContextName.networkFunctionalSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.getPasswordRequest(parameter, mapDialogSupplementaryImpl, invokeId, linkedId, linkedInvoke);
                    else
                        this.getPasswordResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;

            case MAPOperationCode.processUnstructuredSS_Request:
                if (mapApplicationContextName == MAPApplicationContextName.networkUnstructuredSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.processUnstructuredSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.processUnstructuredSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.unstructuredSS_Request:
                if (mapApplicationContextName == MAPApplicationContextName.networkUnstructuredSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.unstructuredSSRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.unstructuredSSResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            case MAPOperationCode.unstructuredSS_Notify:
                if (mapApplicationContextName == MAPApplicationContextName.networkUnstructuredSsContext) {
                    if (componentType == ComponentType.Invoke)
                        this.unstructuredSSNotifyRequest(parameter, mapDialogSupplementaryImpl, invokeId);
                    else
                        this.unstructuredSSNotifyResponse(parameter, mapDialogSupplementaryImpl, invokeId,
                                componentType == ComponentType.ReturnResult);
                }
                break;
            default:
                throw new MAPParsingComponentException("MAPServiceSupplementary: unknown incoming operation code: "
                        + operationCodeValueInt2, MAPParsingComponentExceptionReason.UnrecognizedOperation);
        }
    }

    private void registerSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding registerSSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding registerSSRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        RegisterSSRequestImpl registerSSRequestIndication = new RegisterSSRequestImpl();
        registerSSRequestIndication.decodeData(ais, buf.length);
        registerSSRequestIndication.setInvokeId(invokeId);
        registerSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(registerSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onRegisterSSRequest(registerSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing registerSSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void registerSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        RegisterSSResponseImpl registerSSResponseIndication = new RegisterSSResponseImpl();

        if (parameter != null) {
            // we do not check tag / tagClass because it is a choice

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());
            registerSSResponseIndication.decodeData(ais, buf.length);
        }

        registerSSResponseIndication.setInvokeId(invokeId);
        registerSSResponseIndication.setMAPDialog(mapDialogImpl);
        registerSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(registerSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onRegisterSSResponse(registerSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onRegisterSSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void eraseSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding eraseSSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding eraseSSRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        EraseSSRequestImpl eraseSSRequestIndication = new EraseSSRequestImpl();
        eraseSSRequestIndication.decodeData(ais, buf.length);
        eraseSSRequestIndication.setInvokeId(invokeId);
        eraseSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(eraseSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onEraseSSRequest(eraseSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onEraseSSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void eraseSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        EraseSSResponseImpl eraseSSResponseIndication = new EraseSSResponseImpl();

        if (parameter != null) {
            // we do not check tag / tagClass because it is a choice

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

            eraseSSResponseIndication.decodeData(ais, buf.length);
        }

        eraseSSResponseIndication.setInvokeId(invokeId);
        eraseSSResponseIndication.setMAPDialog(mapDialogImpl);
        eraseSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(eraseSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onEraseSSResponse(eraseSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onEraseSSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void activateSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding activateSSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding activateSSRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        ActivateSSRequestImpl activateSSRequestIndication = new ActivateSSRequestImpl();
        activateSSRequestIndication.decodeData(ais, buf.length);
        activateSSRequestIndication.setInvokeId(invokeId);
        activateSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(activateSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onActivateSSRequest(activateSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onActivateSSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void activateSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        ActivateSSResponseImpl activateSSResponseIndication = new ActivateSSResponseImpl();

        if (parameter != null) {
            // we do not check tag / tagClass because it is a choice

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

            activateSSResponseIndication.decodeData(ais, buf.length);
        }

        activateSSResponseIndication.setInvokeId(invokeId);
        activateSSResponseIndication.setMAPDialog(mapDialogImpl);
        activateSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(activateSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onActivateSSResponse(activateSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onActivateSSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void deactivateSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding deactivateSSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding deactivateSSRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        DeactivateSSRequestImpl deactivateSSRequestIndication = new DeactivateSSRequestImpl();
        deactivateSSRequestIndication.decodeData(ais, buf.length);
        deactivateSSRequestIndication.setInvokeId(invokeId);
        deactivateSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(deactivateSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onDeactivateSSRequest(deactivateSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onDeactivateSSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void deactivateSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        DeactivateSSResponseImpl deactivateSSResponseIndication = new DeactivateSSResponseImpl();

        if (parameter != null) {
            // we do not check tag / tagClass because it is a choice

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

            deactivateSSResponseIndication.decodeData(ais, buf.length);
        }

        deactivateSSResponseIndication.setInvokeId(invokeId);
        deactivateSSResponseIndication.setMAPDialog(mapDialogImpl);
        deactivateSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(deactivateSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onDeactivateSSResponse(deactivateSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onDeactivateSSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void interrogateSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding interrogateSSRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding interrogateSSRequest: Bad tag or tagClass or parameter is primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        InterrogateSSRequestImpl ind = new InterrogateSSRequestImpl();
        ind.decodeData(ais, buf.length);
        ind.setInvokeId(invokeId);
        ind.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(ind);
                ((MAPServiceSupplementaryListener) serLis).onInterrogateSSRequest(ind);
            } catch (Exception e) {
                logger.error("Error processing onInterrogateSSRequest: " + e.getMessage(), e);
            }
        }
    }

    private void interrogateSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        InterrogateSSResponseImpl interrogateSSResponseIndication = new InterrogateSSResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding interrogateSSResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        // we do not check tag / tagClass because it is a choice

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        interrogateSSResponseIndication.decodeData(ais, buf.length);

        interrogateSSResponseIndication.setInvokeId(invokeId);
        interrogateSSResponseIndication.setMAPDialog(mapDialogImpl);
        interrogateSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(interrogateSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onInterrogateSSResponse(interrogateSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onInterrogateSSResponse: " + e.getMessage(), e);
            }
        }
    }

    private void getPasswordRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId, Long linkedId, Invoke linkedInvoke)
            throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding getPasswordRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.ENUMERATED || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding getPasswordRequest: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        GetPasswordRequestImpl getPasswordRequestIndication = new GetPasswordRequestImpl();
        getPasswordRequestIndication.decodeData(ais, buf.length);
        getPasswordRequestIndication.setInvokeId(invokeId);
        getPasswordRequestIndication.setLinkedId(linkedId);
        getPasswordRequestIndication.setLinkedInvoke(linkedInvoke);
        getPasswordRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(getPasswordRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onGetPasswordRequest(getPasswordRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onGetPasswordRequest: " + e.getMessage(), e);
            }
        }
    }

    private void getPasswordResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        GetPasswordResponseImpl getPasswordResponseIndication = new GetPasswordResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException(
                    "Error while decoding getPasswordResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_NUMERIC || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding getPasswordResponse: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        getPasswordResponseIndication.decodeData(ais, buf.length);

        getPasswordResponseIndication.setInvokeId(invokeId);
        getPasswordResponseIndication.setMAPDialog(mapDialogImpl);
        getPasswordResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(getPasswordResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onGetPasswordResponse(getPasswordResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onGetPasswordResponse: " + e.getMessage(), e);
            }
        }
    }

    private void registerPasswordRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId) throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding registerPasswordRequest: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_OCTET || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding registerPasswordRequest: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        RegisterPasswordRequestImpl registerPasswordRequestIndication = new RegisterPasswordRequestImpl();
        registerPasswordRequestIndication.decodeData(ais, buf.length);
        registerPasswordRequestIndication.setInvokeId(invokeId);
        registerPasswordRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(registerPasswordRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onRegisterPasswordRequest(registerPasswordRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing onRegisterPasswordRequest: " + e.getMessage(), e);
            }
        }
    }

    private void registerPasswordResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {
        RegisterPasswordResponseImpl registerPasswordResponseIndication = new RegisterPasswordResponseImpl();

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding registerPasswordResponse: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.STRING_NUMERIC || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || !parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding registerPasswordResponse: Bad tag or tagClass or parameter is not primitive, received tag=" + parameter.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        registerPasswordResponseIndication.decodeData(ais, buf.length);

        registerPasswordResponseIndication.setInvokeId(invokeId);
        registerPasswordResponseIndication.setMAPDialog(mapDialogImpl);
        registerPasswordResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(registerPasswordResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onRegisterPasswordResponse(registerPasswordResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing onRegisterPasswordResponse: " + e.getMessage(), e);
            }
        }
    }


    private void unstructuredSSNotifyRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding unstructuredSSNotifyIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding unstructuredSSNotifyIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        UnstructuredSSNotifyRequestImpl unstructuredSSNotifyRequestIndication = new UnstructuredSSNotifyRequestImpl();
        unstructuredSSNotifyRequestIndication.decodeData(ais, buf.length);
        unstructuredSSNotifyRequestIndication.setInvokeId(invokeId);
        unstructuredSSNotifyRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(unstructuredSSNotifyRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onUnstructuredSSNotifyRequest(unstructuredSSNotifyRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing unstructuredSSNotifyIndication: " + e.getMessage(), e);
            }
        }
    }

    private void unstructuredSSNotifyResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        UnstructuredSSNotifyResponseImpl unstructuredSSNotifyResponseIndication = new UnstructuredSSNotifyResponseImpl();
        unstructuredSSNotifyResponseIndication.setInvokeId(invokeId);
        unstructuredSSNotifyResponseIndication.setMAPDialog(mapDialogImpl);
        unstructuredSSNotifyResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(unstructuredSSNotifyResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onUnstructuredSSNotifyResponse(unstructuredSSNotifyResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing unstructuredSSNotifyIndication: " + e.getMessage(), e);
            }
        }
    }

    private void unstructuredSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {
        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding UnstructuredSSRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding UnstructuredSSRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        UnstructuredSSRequestImpl unstructuredSSRequestIndication = new UnstructuredSSRequestImpl();
        unstructuredSSRequestIndication.decodeData(ais, buf.length);
        unstructuredSSRequestIndication.setInvokeId(invokeId);
        unstructuredSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(unstructuredSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onUnstructuredSSRequest(unstructuredSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing UnstructuredSSRequestIndication: " + e.getMessage(), e);
            }
        }
    }

    private void unstructuredSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        UnstructuredSSResponseImpl unstructuredSSResponseIndication = new UnstructuredSSResponseImpl();

        if (parameter != null) {
            if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
                throw new MAPParsingComponentException("Error while decoding UnstructuredSSResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                                + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

            byte[] buf = parameter.getData();
            AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

            unstructuredSSResponseIndication.decodeData(ais, buf.length);
        }

        unstructuredSSResponseIndication.setInvokeId(invokeId);
        unstructuredSSResponseIndication.setMAPDialog(mapDialogImpl);
        unstructuredSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(unstructuredSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onUnstructuredSSResponse(unstructuredSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing UnstructuredSSResponseIndication: " + e.getMessage(), e);
            }
        }

    }

    private void processUnstructuredSSRequest(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId)
            throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding ProcessUnstructuredSSRequestIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding ProcessUnstructuredSSRequestIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        ProcessUnstructuredSSRequestImpl processUnstructuredSSRequestIndication = new ProcessUnstructuredSSRequestImpl();
        processUnstructuredSSRequestIndication.decodeData(ais, buf.length);
        processUnstructuredSSRequestIndication.setInvokeId(invokeId);
        processUnstructuredSSRequestIndication.setMAPDialog(mapDialogImpl);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(processUnstructuredSSRequestIndication);
                ((MAPServiceSupplementaryListener) serLis).onProcessUnstructuredSSRequest(processUnstructuredSSRequestIndication);
            } catch (Exception e) {
                logger.error("Error processing ProcessUnstructuredSSRequestIndication: " + e.getMessage(), e);
            }
        }

    }

    private void processUnstructuredSSResponse(Parameter parameter, MAPDialogSupplementaryImpl mapDialogImpl, Long invokeId,
            boolean returnResultNotLast) throws MAPParsingComponentException {

        if (parameter == null)
            throw new MAPParsingComponentException("Error while decoding ProcessUnstructuredSSResponseIndication: Parameter is mandatory but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (parameter.getTag() != Tag.SEQUENCE || parameter.getTagClass() != Tag.CLASS_UNIVERSAL || parameter.isPrimitive())
            throw new MAPParsingComponentException("Error while decoding ProcessUnstructuredSSResponseIndication: Bad tag or tagClass or parameter is primitive, received tag="
                            + parameter.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);

        byte[] buf = parameter.getData();
        AsnInputStream ais = new AsnInputStream(buf, parameter.getTagClass(), parameter.isPrimitive(), parameter.getTag());

        ProcessUnstructuredSSResponseImpl processUnstructuredSSResponseIndication = new ProcessUnstructuredSSResponseImpl();
        processUnstructuredSSResponseIndication.decodeData(ais, buf.length);
        processUnstructuredSSResponseIndication.setInvokeId(invokeId);
        processUnstructuredSSResponseIndication.setMAPDialog(mapDialogImpl);
        processUnstructuredSSResponseIndication.setReturnResultNotLast(returnResultNotLast);

        for (MAPServiceListener serLis : this.serviceListeners) {
            try {
                serLis.onMAPMessage(processUnstructuredSSResponseIndication);
                ((MAPServiceSupplementaryListener) serLis).onProcessUnstructuredSSResponse(processUnstructuredSSResponseIndication);
            } catch (Exception e) {
                logger.error("Error processing ProcessUnstructuredSSResponseIndication: " + e.getMessage(), e);
            }
        }

    }
}
