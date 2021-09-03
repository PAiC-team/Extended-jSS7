
package org.restcomm.protocols.ss7.map.service.pdpContextActivation;

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
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivation;
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
public class MAPDialogPdpContextActivationImpl extends MAPDialogImpl implements MAPDialogPdpContextActivation {

    protected MAPDialogPdpContextActivationImpl(MAPApplicationContext mapApplicationContext, Dialog tcapDialog,
            MAPProviderImpl mapProviderImpl, MAPServicePdpContextActivation mapService, AddressString origReference,
            AddressString destReference) {
        super(mapApplicationContext, tcapDialog, mapProviderImpl, mapService, origReference, destReference);
    }


    @Override
    public Long addSendRoutingInfoForGprsRequest(IMSI imsi, GSNAddress ggsnAddress, ISDNAddressString ggsnNumber, MAPExtensionContainer extensionContainer)
            throws MAPException {
        return addSendRoutingInfoForGprsRequest(_Timer_Default, imsi, ggsnAddress, ggsnNumber, extensionContainer);
    }

    @Override
    public Long addSendRoutingInfoForGprsRequest(int customInvokeTimeout, IMSI imsi, GSNAddress ggsnAddress, ISDNAddressString ggsnNumber,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.gprsLocationInfoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3 && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version4))
            throw new MAPException("Bad application context name for addSendRoutingInfoForGprsRequest: must be gprsLocationInfoRetrievalContext_V3 or V4");

        Invoke invoke = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createTCInvokeRequest();
        if (customInvokeTimeout == _Timer_Default)
            invoke.setTimeout(getMediumTimer());
        else
            invoke.setTimeout(customInvokeTimeout);

        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForGprs);
        invoke.setOperationCode(operationCode);

        SendRoutingInfoForGprsRequestImpl sendRoutingInfoForGprsRequest = new SendRoutingInfoForGprsRequestImpl(imsi, ggsnAddress, ggsnNumber, extensionContainer);
        AsnOutputStream aos = new AsnOutputStream();
        sendRoutingInfoForGprsRequest.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendRoutingInfoForGprsRequest.getTagClass());
        parameter.setPrimitive(sendRoutingInfoForGprsRequest.getIsPrimitive());
        parameter.setTag(sendRoutingInfoForGprsRequest.getTag());
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
    public void addSendRoutingInfoForGprsResponse(long invokeId, GSNAddress sgsnAddress, GSNAddress ggsnAddress, Integer mobileNotReachableReason,
            MAPExtensionContainer extensionContainer) throws MAPException {

        if ((this.mapApplicationContext.getApplicationContextName() != MAPApplicationContextName.gprsLocationInfoRetrievalContext)
                || (this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version3 && this.mapApplicationContext.getApplicationContextVersion() != MAPApplicationContextVersion.version4))
            throw new MAPException("Bad application context name for addSendRoutingInfoForGprsResponse: must be gprsLocationInfoRetrievalContext_V3 or V4");

        ReturnResultLast resultLast = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory()
                .createTCResultLastRequest();

        resultLast.setInvokeId(invokeId);

        // Operation Code
        OperationCode operationCode = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createOperationCode();
        operationCode.setLocalOperationCode((long) MAPOperationCode.sendRoutingInfoForGprs);
        resultLast.setOperationCode(operationCode);

        SendRoutingInfoForGprsResponseImpl sendRoutingInfoForGprsResponse = new SendRoutingInfoForGprsResponseImpl(sgsnAddress, ggsnAddress, mobileNotReachableReason, extensionContainer);
        AsnOutputStream aos = new AsnOutputStream();
        sendRoutingInfoForGprsResponse.encodeData(aos);

        Parameter parameter = this.mapProviderImpl.getTCAPProvider().getComponentPrimitiveFactory().createParameter();
        parameter.setTagClass(sendRoutingInfoForGprsResponse.getTagClass());
        parameter.setPrimitive(sendRoutingInfoForGprsResponse.getIsPrimitive());
        parameter.setTag(sendRoutingInfoForGprsResponse.getTag());
        parameter.setData(aos.toByteArray());
        resultLast.setParameter(parameter);

        this.sendReturnResultLastComponent(resultLast);
    }

}
