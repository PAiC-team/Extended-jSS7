
package org.restcomm.protocols.ss7.map.service.oam;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPDialogImpl;
import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPServiceOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MAPDialogOamImpl extends MAPDialogImpl implements MAPDialogOam {

    protected MAPDialogOamImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog, MAPProviderImpl mapProviderImpl,
            MAPServiceOam mapService, AddressString origReference, AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }


    @Override
    public Long addActivateTraceModeRequest(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {
        return this.addActivateTraceModeRequest(_Timer_Default, imsi, traceReference, traceType, omcId, extensionContainer, traceReference2, traceDepthList,
                traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
    }

    @Override
    public Long addActivateTraceModeRequest(int customInvokeTimeout, IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {

        boolean isTracingContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.tracingContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isTracingContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;

        if (!isTracingContext && !isNetworkLocUpContext && !isGprsLocationUpdateContext)
            throw new MAPException(
                    "Bad application context name for activateTraceModeRequest: must be tracingContext_V1, V2 or V3, networkLocUpContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.activateTraceMode);
        invoke.setOperationCode(operationCode);

        ActivateTraceModeRequestImpl_Oam activateTraceModeRequestImplOam = new ActivateTraceModeRequestImpl_Oam(imsi, traceReference, traceType, omcId, extensionContainer, traceReference2,
                traceDepthList, traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
        AsnOutputStream aos = new AsnOutputStream();
        activateTraceModeRequestImplOam.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(activateTraceModeRequestImplOam.getTagClass());
        parameter.setPrimitive(activateTraceModeRequestImplOam.getIsPrimitive());
        parameter.setTag(activateTraceModeRequestImplOam.getTag());
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
    public void addActivateTraceModeResponse(long invokeId, MAPExtensionContainer extensionContainer, boolean traceSupportIndicator) throws MAPException {
        boolean isTracingContext = false;
        boolean isNetworkLocUpContext = false;
        boolean isGprsLocationUpdateContext = false;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.tracingContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isTracingContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.networkLocUpContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version1
                        || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2 || this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isNetworkLocUpContext = true;
        if ((this.mapApplicationContext.getApplicationContextName() == MAPApplicationContextName.gprsLocationUpdateContext)
                && (this.mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version3))
            isGprsLocationUpdateContext = true;

        if (!isTracingContext && !isNetworkLocUpContext && !isGprsLocationUpdateContext)
            throw new MAPException(
                    "Bad application context name for activateTraceModeResponse: must be tracingContext_V1, V2 or V3, networkLocUpContext_V1, V2 or V3 or gprsLocationUpdateContext_V3");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        if ((traceSupportIndicator || extensionContainer != null) && this.mapApplicationContext.getApplicationContextVersion().getVersion() >= 3) {
            // Operation Code
            OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
            operationCode.setLocalOperationCode((long) MAPOperationCode.activateTraceMode);
            resultLast.setOperationCode(operationCode);

            ActivateTraceModeResponseImpl_Oam activateTraceModeResponseImplOam = new ActivateTraceModeResponseImpl_Oam(extensionContainer, traceSupportIndicator);
            AsnOutputStream aos = new AsnOutputStream();
            activateTraceModeResponseImplOam.encodeData(aos);

            Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
            parameter.setTagClass(activateTraceModeResponseImplOam.getTagClass());
            parameter.setPrimitive(activateTraceModeResponseImplOam.getIsPrimitive());
            parameter.setTag(activateTraceModeResponseImplOam.getTag());
            parameter.setData(aos.toByteArray());
            resultLast.setParameter(parameter);
        }

        this.sendReturnResultLastComponent(resultLast);
    }

    @Override
    public Long addSendImsiRequest(ISDNAddressString msisdn) throws MAPException {
        return this.addSendImsiRequest(_Timer_Default, msisdn);
    }

    @Override
    public Long addSendImsiRequest(int customInvokeTimeout, ISDNAddressString msisdn) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.imsiRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for sendImsiRequest: must be imsiRetrievalContext_V2");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendIMSI);
        invoke.setOperationCode(operationCode);

        SendImsiRequestImpl sendImsiRequest = new SendImsiRequestImpl(msisdn);
        AsnOutputStream aos = new AsnOutputStream();
        sendImsiRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendImsiRequest.getTagClass());
        parameter.setPrimitive(sendImsiRequest.getIsPrimitive());
        parameter.setTag(sendImsiRequest.getTag());
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
    public void addSendImsiResponse(long invokeId, IMSI imsi) throws MAPException {
        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.imsiRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version2))
            throw new MAPException("Bad application context name for addSendImsiResponse: must be imsiRetrievalContext_V2");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendIMSI);
        resultLast.setOperationCode(operationCode);

        SendImsiResponseImpl sendImsiResponse = new SendImsiResponseImpl(imsi);
        AsnOutputStream aos = new AsnOutputStream();
        sendImsiResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendImsiResponse.getTagClass());
        parameter.setPrimitive(sendImsiResponse.getIsPrimitive());
        parameter.setTag(sendImsiResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

}
