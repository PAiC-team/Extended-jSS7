
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GenericServiceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GuidanceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.Password;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.Return;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 *
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class MAPDialogSupplementaryImpl extends MAPDialogImpl implements MAPDialogSupplementary {

    protected MAPDialogSupplementaryImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog, MAPProviderImpl mapProviderImpl,
            MAPServiceSupplementary mapService, AddressString origReference, AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }

    public Long addProcessUnstructuredSSRequest(CBSDataCodingScheme ussdDataCodingScheme, USSDString ussdString,
            AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {
        return this.addProcessUnstructuredSSRequest(_Timer_Default, ussdDataCodingScheme, ussdString, alertingPatter, msisdn);
    }


    @Override
    public Long addRegisterSSRequest(SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber, ISDNAddressString forwardedToSubaddress,
            Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser, ISDNAddressString longFTNSupported) throws MAPException {
        return this.addRegisterSSRequest(_Timer_Default, ssCode, basicService, forwardedToNumber, forwardedToSubaddress, noReplyConditionTime, defaultPriority,
                nbrUser, longFTNSupported);
    }

    @Override
    public Long addRegisterSSRequest(int customInvokeTimeout, SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber,
            ISDNAddressString forwardedToSubaddress, Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser,
            ISDNAddressString longFTNSupported) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addRegisterSSRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.registerSS);
        invoke.setOperationCode(operationCode);

        RegisterSSRequestImpl registerSSRequest = new RegisterSSRequestImpl(ssCode, basicService, forwardedToNumber, forwardedToSubaddress, noReplyConditionTime,
                defaultPriority, nbrUser, longFTNSupported);
        AsnOutputStream aos = new AsnOutputStream();
        registerSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(registerSSRequest.getTagClass());
        parameter.setPrimitive(registerSSRequest.getIsPrimitive());
        parameter.setTag(registerSSRequest.getTag());
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
    public void addRegisterSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addRegisterSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.registerSS);
        resultLast.setOperationCode(operationCode);

        if (ssInfo != null) {
            RegisterSSResponseImpl registerSSResponse = new RegisterSSResponseImpl(ssInfo);
            AsnOutputStream aos = new AsnOutputStream();
            registerSSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(registerSSResponse.getTagClass());
            parameter.setPrimitive(registerSSResponse.getIsPrimitive());
            parameter.setTag(registerSSResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addEraseSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.addEraseSSRequest(_Timer_Default, ssForBSCode);
    }

    @Override
    public Long addEraseSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addEraseSSRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.eraseSS);
        invoke.setOperationCode(operationCode);

        EraseSSRequestImpl eraseSSRequest = new EraseSSRequestImpl(ssForBSCode);
        AsnOutputStream aos = new AsnOutputStream();
        eraseSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(eraseSSRequest.getTagClass());
        parameter.setPrimitive(eraseSSRequest.getIsPrimitive());
        parameter.setTag(eraseSSRequest.getTag());
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
    public void addEraseSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addEraseSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.eraseSS);
        resultLast.setOperationCode(operationCode);

        if (ssInfo != null) {
            EraseSSResponseImpl eraseSSResponse = new EraseSSResponseImpl(ssInfo);
            AsnOutputStream aos = new AsnOutputStream();
            eraseSSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(eraseSSResponse.getTagClass());
            parameter.setPrimitive(eraseSSResponse.getIsPrimitive());
            parameter.setTag(eraseSSResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addActivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.addActivateSSRequest(_Timer_Default, ssForBSCode);
    }

    @Override
    public Long addActivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addActivateSSRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.activateSS);
        invoke.setOperationCode(operationCode);

        ActivateSSRequestImpl activateSSRequest = new ActivateSSRequestImpl(ssForBSCode);
        AsnOutputStream aos = new AsnOutputStream();
        activateSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(activateSSRequest.getTagClass());
        parameter.setPrimitive(activateSSRequest.getIsPrimitive());
        parameter.setTag(activateSSRequest.getTag());
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
    public void addActivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addActivateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.activateSS);
        resultLast.setOperationCode(operationCode);

        if (ssInfo != null) {
            ActivateSSResponseImpl activateSSResponse = new ActivateSSResponseImpl(ssInfo);
            AsnOutputStream aos = new AsnOutputStream();
            activateSSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(activateSSResponse.getTagClass());
            parameter.setPrimitive(activateSSResponse.getIsPrimitive());
            parameter.setTag(activateSSResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addDeactivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.addDeactivateSSRequest(_Timer_Default, ssForBSCode);
    }

    @Override
    public Long addDeactivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addDeactivateSSRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.deactivateSS);
        invoke.setOperationCode(operationCode);

        DeactivateSSRequestImpl deactivateSSRequest = new DeactivateSSRequestImpl(ssForBSCode);
        AsnOutputStream aos = new AsnOutputStream();
        deactivateSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(deactivateSSRequest.getTagClass());
        parameter.setPrimitive(deactivateSSRequest.getIsPrimitive());
        parameter.setTag(deactivateSSRequest.getTag());
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
    public void addDeactivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addDeactivateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.deactivateSS);
        resultLast.setOperationCode(operationCode);

        if (ssInfo != null) {
            DeactivateSSResponseImpl deactivateSSResponse = new DeactivateSSResponseImpl(ssInfo);
            AsnOutputStream aos = new AsnOutputStream();
            deactivateSSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(deactivateSSResponse.getTagClass());
            parameter.setPrimitive(deactivateSSResponse.getIsPrimitive());
            parameter.setTag(deactivateSSResponse.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addInterrogateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.addInterrogateSSRequest(_Timer_Default, ssForBSCode);
    }

    @Override
    public Long addInterrogateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addInterrogateSSRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.interrogateSS);
        invoke.setOperationCode(operationCode);

        InterrogateSSRequestImpl interrogateSSRequest = new InterrogateSSRequestImpl(ssForBSCode);
        AsnOutputStream aos = new AsnOutputStream();
        interrogateSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(interrogateSSRequest.getTagClass());
        parameter.setPrimitive(interrogateSSRequest.getIsPrimitive());
        parameter.setTag(interrogateSSRequest.getTag());
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
    public void addInterrogateSSResponse_SSStatus(long invokeId, SSStatus ssStatus) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addInterrogateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.interrogateSS);
        resultLast.setOperationCode(operationCode);

        InterrogateSSResponseImpl interrogateSSResponse = new InterrogateSSResponseImpl(ssStatus);
        AsnOutputStream aos = new AsnOutputStream();
        interrogateSSResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(interrogateSSResponse.getTagClass());
        parameter.setPrimitive(interrogateSSResponse.getIsPrimitive());
        parameter.setTag(interrogateSSResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public void addInterrogateSSResponse_BasicServiceGroupList(long invokeId, ArrayList<BasicServiceCode> basicServiceGroupList) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addInterrogateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.interrogateSS);
        resultLast.setOperationCode(operationCode);

        InterrogateSSResponseImpl interrogateSSResponse = new InterrogateSSResponseImpl(basicServiceGroupList, false);
        AsnOutputStream aos = new AsnOutputStream();
        interrogateSSResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(interrogateSSResponse.getTagClass());
        parameter.setPrimitive(interrogateSSResponse.getIsPrimitive());
        parameter.setTag(interrogateSSResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public void addInterrogateSSResponse_ForwardingFeatureList(long invokeId, ArrayList<ForwardingFeature> forwardingFeatureList) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addInterrogateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.interrogateSS);
        resultLast.setOperationCode(operationCode);

        InterrogateSSResponseImpl interrogateSSResponse = new InterrogateSSResponseImpl(forwardingFeatureList);
        AsnOutputStream aos = new AsnOutputStream();
        interrogateSSResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(interrogateSSResponse.getTagClass());
        parameter.setPrimitive(interrogateSSResponse.getIsPrimitive());
        parameter.setTag(interrogateSSResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public void addInterrogateSSResponse_GenericServiceInfo(long invokeId, GenericServiceInfo genericServiceInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addInterrogateSSResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.interrogateSS);
        resultLast.setOperationCode(operationCode);

        InterrogateSSResponseImpl interrogateSSResponse = new InterrogateSSResponseImpl(genericServiceInfo);
        AsnOutputStream aos = new AsnOutputStream();
        interrogateSSResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(interrogateSSResponse.getTagClass());
        parameter.setPrimitive(interrogateSSResponse.getIsPrimitive());
        parameter.setTag(interrogateSSResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addGetPasswordRequest(Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        return this.addGetPasswordRequest(_Timer_Default, linkedId, guidanceInfo);
    }

    @Override
    public Long addGetPasswordRequest(int customInvokeTimeout, Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addGetPasswordRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.getPassword);
        invoke.setOperationCode(operationCode);

        GetPasswordRequestImpl getPasswordRequest = new GetPasswordRequestImpl(guidanceInfo);
        AsnOutputStream aos = new AsnOutputStream();
        getPasswordRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(getPasswordRequest.getTagClass());
        parameter.setPrimitive(getPasswordRequest.getIsPrimitive());
        parameter.setTag(getPasswordRequest.getTag());
        parameter.setData(aos.toByteArray());
        invoke.setParameter(parameter);

        Long invokeId;
        try {
            invokeId = this.tcapDialog.getNewInvokeId();
            invoke.setInvokeId(invokeId);
            invoke.setLinkedId(linkedId);
        } catch (TCAPException e) {
            throw new MAPException(e.getMessage(), e);
        }

        this.sendInvokeComponent(invoke);

        return invokeId;
    }

    @Override
    public void addGetPasswordResponse(long invokeId, Password password) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addGetPasswordResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.getPassword);
        resultLast.setOperationCode(operationCode);

        GetPasswordResponseImpl getPasswordResponse = new GetPasswordResponseImpl(password);
        AsnOutputStream aos = new AsnOutputStream();
        getPasswordResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(getPasswordResponse.getTagClass());
        parameter.setPrimitive(getPasswordResponse.getIsPrimitive());
        parameter.setTag(getPasswordResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addRegisterPasswordRequest(SSCode ssCode) throws MAPException {
        return this.addRegisterPasswordRequest(_Timer_Default, ssCode);
    }

    @Override
    public Long addRegisterPasswordRequest(int customInvokeTimeout, SSCode ssCode) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addRegisterPasswordRequest: must be networkFunctionalSsContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.registerPassword);
        invoke.setOperationCode(operationCode);

        RegisterPasswordRequestImpl registerPasswordRequest = new RegisterPasswordRequestImpl(ssCode);
        AsnOutputStream aos = new AsnOutputStream();
        registerPasswordRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(registerPasswordRequest.getTagClass());
        parameter.setPrimitive(registerPasswordRequest.getIsPrimitive());
        parameter.setTag(registerPasswordRequest.getTag());
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
    public void addRegisterPasswordResponse(long invokeId, Password password) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.networkFunctionalSsContext)
                || this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2)
            throw new MAPException("Bad application context name for addRegisterPasswordResponse: must be networkFunctionalSsContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.registerPassword);
        resultLast.setOperationCode(operationCode);

        RegisterPasswordResponseImpl registerPasswordResponse = new RegisterPasswordResponseImpl(password);
        AsnOutputStream aos = new AsnOutputStream();
        registerPasswordResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(registerPasswordResponse.getTagClass());
        parameter.setPrimitive(registerPasswordResponse.getIsPrimitive());
        parameter.setTag(registerPasswordResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }


    public Long addProcessUnstructuredSSRequest(int customInvokeTimeout, CBSDataCodingScheme ussdDataCodingScheme,
            USSDString ussdString, AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(600000); // 10 minutes
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.processUnstructuredSS_Request);
        invoke.setOperationCode(operationCode);

        ProcessUnstructuredSSRequestImpl processUnstructuredSSRequest = new ProcessUnstructuredSSRequestImpl(ussdDataCodingScheme, ussdString,
                alertingPatter, msisdn);
        AsnOutputStream aos = new AsnOutputStream();
        processUnstructuredSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(processUnstructuredSSRequest.getTagClass());
        parameter.setPrimitive(processUnstructuredSSRequest.getIsPrimitive());
        parameter.setTag(processUnstructuredSSRequest.getTag());
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

    public void addProcessUnstructuredSSResponse(long invokeId, CBSDataCodingScheme ussdDataCodingScheme, USSDString ussdString)
            throws MAPException {
        Return returnResult = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        returnResult.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.processUnstructuredSS_Request);
        returnResult.setOperationCode(operationCode);

        ProcessUnstructuredSSResponseImpl processUnstructuredSSResponse = new ProcessUnstructuredSSResponseImpl(ussdDataCodingScheme, ussdString);
        AsnOutputStream aos = new AsnOutputStream();
        processUnstructuredSSResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(processUnstructuredSSResponse.getTagClass());
        parameter.setPrimitive(processUnstructuredSSResponse.getIsPrimitive());
        parameter.setTag(processUnstructuredSSResponse.getTag());
        parameter.setData(aos.toByteArray());
        returnResult.setParameter(parameter);

        this.sendReturnResultLastComponent((ReturnResultLast) returnResult);
    }

    public Long addUnstructuredSSRequest(CBSDataCodingScheme ussdDataCodingScheme, USSDString ussdString,
            AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {
        return this.addUnstructuredSSRequest(_Timer_Default, ussdDataCodingScheme, ussdString, alertingPatter, msisdn);
    }

    public Long addUnstructuredSSRequest(int customInvokeTimeout, CBSDataCodingScheme ussdDataCodingScheme,
            USSDString ussdString, AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.unstructuredSS_Request);
        invoke.setOperationCode(operationCode);

        UnstructuredSSRequestImpl unstructuredSSRequest = new UnstructuredSSRequestImpl(ussdDataCodingScheme, ussdString, alertingPatter, msisdn);
        AsnOutputStream aos = new AsnOutputStream();
        unstructuredSSRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(unstructuredSSRequest.getTagClass());
        parameter.setPrimitive(unstructuredSSRequest.getIsPrimitive());
        parameter.setTag(unstructuredSSRequest.getTag());
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

    public Long addUnstructuredSSNotifyRequest(CBSDataCodingScheme ussdDataCodingScheme, USSDString ussdString,
            AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {
        return this.addUnstructuredSSNotifyRequest(_Timer_Default, ussdDataCodingScheme, ussdString, alertingPatter, msisdn);
    }

    public Long addUnstructuredSSNotifyRequest(int customInvokeTimeout, CBSDataCodingScheme ussdDataCodingScheme,
            USSDString ussdString, AlertingPattern alertingPatter, ISDNAddressString msisdn) throws MAPException {

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getLongTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.unstructuredSS_Notify);
        invoke.setOperationCode(operationCode);

        if (ussdString != null) {
            UnstructuredSSRequestImpl unstructuredSSRequest = new UnstructuredSSRequestImpl(ussdDataCodingScheme, ussdString, alertingPatter, msisdn);
            AsnOutputStream aos = new AsnOutputStream();
            unstructuredSSRequest.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(unstructuredSSRequest.getTagClass());
            parameter.setPrimitive(unstructuredSSRequest.getIsPrimitive());
            parameter.setTag(unstructuredSSRequest.getTag());
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

    public void addUnstructuredSSResponse(long invokeId, CBSDataCodingScheme ussdDataCodingScheme, USSDString ussdString)
            throws MAPException {

        Return returnResult = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        returnResult.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = TcapFactory.createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.unstructuredSS_Request);
        returnResult.setOperationCode(operationCode);

        if (ussdString != null) {
            UnstructuredSSResponseImpl unstructuredSSResponse = new UnstructuredSSResponseImpl(ussdDataCodingScheme, ussdString);
            AsnOutputStream aos = new AsnOutputStream();
            unstructuredSSResponse.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(unstructuredSSResponse.getTagClass());
            parameter.setPrimitive(unstructuredSSResponse.getIsPrimitive());
            parameter.setTag(unstructuredSSResponse.getTag());
            parameter.setData(aos.toByteArray());
            returnResult.setParameter(parameter);
        }

        this.sendReturnResultLastComponent((ReturnResultLast) returnResult);
    }

    public void addUnstructuredSSNotifyResponse(long invokeId) throws MAPException {

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // we need not Operation Code because no answer

        this.sendReturnResultLastComponent(resultLast);
    }

}
