
package org.restcomm.protocols.ss7.map.service.pdpContextActivation;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.SendRoutingInfoForGprsResponse;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class SendRoutingInfoForGprsResponseImpl extends PdpContextActivationMessageImpl implements SendRoutingInfoForGprsResponse {

    protected static final int _TAG_sgsnAddress = 0;
    protected static final int _TAG_ggsnAddress = 1;
    protected static final int _TAG_mobileNotReachableReason = 2;
    protected static final int _TAG_extensionContainer = 3;

    public static final String _PrimitiveName = "SendRoutingInfoForGprsResponse";

    private GSNAddress sgsnAddress;
    private GSNAddress ggsnAddress;
    private Integer mobileNotReachableReason;
    private MAPExtensionContainer extensionContainer;

    public SendRoutingInfoForGprsResponseImpl() {
    }

    public SendRoutingInfoForGprsResponseImpl(GSNAddress sgsnAddress, GSNAddress ggsnAddress, Integer mobileNotReachableReason,
            MAPExtensionContainer extensionContainer) {
        this.sgsnAddress = sgsnAddress;
        this.ggsnAddress = ggsnAddress;
        this.mobileNotReachableReason = mobileNotReachableReason;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.sendRoutingInfoForGprs_Response;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.sendRoutingInfoForGprs;
    }

    @Override
    public GSNAddress getSgsnAddress() {
        return sgsnAddress;
    }

    @Override
    public GSNAddress getGgsnAddress() {
        return ggsnAddress;
    }

    @Override
    public Integer getMobileNotReachableReason() {
        return mobileNotReachableReason;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.sgsnAddress = null;
        this.ggsnAddress = null;
        this.mobileNotReachableReason = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _TAG_sgsnAddress:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".sgsnAddress: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sgsnAddress = new GSNAddressImpl();
                    ((GSNAddressImpl) this.sgsnAddress).decodeAll(ais);
                    break;
                case _TAG_ggsnAddress:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".ggsnAddress: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.ggsnAddress = new GSNAddressImpl();
                    ((GSNAddressImpl) this.ggsnAddress).decodeAll(ais);
                    break;
                case _TAG_mobileNotReachableReason:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".mobileNotReachableReason: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.mobileNotReachableReason = (int) ais.readInteger();
                    break;
                case _TAG_extensionContainer:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".extensionContainer: Parameter extensionContainer is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.extensionContainer = new MAPExtensionContainerImpl();
                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }

            num++;
        }

        if (this.sgsnAddress == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Parameter sgsnAddress is mandator but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.sgsnAddress == null)
                throw new MAPException("sgsnAddress parameter must not be null");

            ((GSNAddressImpl) this.sgsnAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_sgsnAddress);
            if (this.ggsnAddress != null)
                ((GSNAddressImpl) this.ggsnAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_ggsnAddress);

            if (this.mobileNotReachableReason != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_mobileNotReachableReason, this.mobileNotReachableReason);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_extensionContainer);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.sgsnAddress != null) {
            sb.append("sgsnAddress=");
            sb.append(sgsnAddress);
            sb.append(", ");
        }
        if (this.ggsnAddress != null) {
            sb.append("ggsnAddress=");
            sb.append(ggsnAddress);
            sb.append(", ");
        }
        if (this.mobileNotReachableReason != null) {
            sb.append("mobileNotReachableReason=");
            sb.append(mobileNotReachableReason);
            sb.append(", ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer);
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
