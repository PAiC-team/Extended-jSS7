
package org.restcomm.protocols.ss7.map.service.supplementary;

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
import org.restcomm.protocols.ss7.map.api.service.supplementary.GetPasswordRequest;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GuidanceInfo;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

/**
*
* @author sergey vetyutnev
*
*/
public class GetPasswordRequestImpl extends SupplementaryMessageImpl implements GetPasswordRequest {

    public static final String _PrimitiveName = "GetPasswordRequest";

    private GuidanceInfo guidanceInfo;

    private Long linkedId;
    private Invoke linkedInvoke;

    public GetPasswordRequestImpl() {
    }

    public GetPasswordRequestImpl(GuidanceInfo guidanceInfo) {
        this.guidanceInfo = guidanceInfo;
    }


    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.getPasswordRequest_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.getPassword;
    }

    @Override
    public GuidanceInfo getGuidanceInfo() {
        return guidanceInfo;
    }

    @Override
    public Long getLinkedId() {
        return linkedId;
    }

    @Override
    public void setLinkedId(Long val) {
        linkedId = val;
    }

    @Override
    public Invoke getLinkedInvoke() {
        return linkedInvoke;
    }

    @Override
    public void setLinkedInvoke(Invoke val) {
        linkedInvoke = val;
    }

    @Override
    public int getTag() throws MAPException {
        return Tag.ENUMERATED;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return true;
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
        int i1 = (int) asnInputStream.readIntegerData(length);
        this.guidanceInfo = GuidanceInfo.getInstance(i1);
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
        if (guidanceInfo == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": guidanceInfo parameter is not defined.");

        try {
            asnOutputStream.writeIntegerData(this.guidanceInfo.getCode());
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.guidanceInfo != null) {
            sb.append("guidanceInfo=");
            sb.append(this.guidanceInfo);
        }

        sb.append("]");

        return sb.toString();
    }

}
