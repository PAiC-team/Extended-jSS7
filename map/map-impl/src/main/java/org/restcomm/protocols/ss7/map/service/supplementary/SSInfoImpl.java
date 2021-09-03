
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CallBarringInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSData;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
*
* @author sergey vetyutnev
*
*/
public class SSInfoImpl implements SSInfo, MAPAsnPrimitive {

    public static final int _TAG_forwardingInfo = 0;
    public static final int _TAG_callBarringInfo = 1;
    public static final int _TAG_ssData = 3;

    public static final String _PrimitiveName = "SSInfo";

    private ForwardingInfo forwardingInfo;
    private CallBarringInfo callBarringInfo;
    private SSData ssData;

    public SSInfoImpl() {
    }

    public SSInfoImpl(ForwardingInfo forwardingInfo) {
        this.forwardingInfo = forwardingInfo;
    }

    public SSInfoImpl(CallBarringInfo callBarringInfo) {
        this.callBarringInfo = callBarringInfo;
    }

    public SSInfoImpl(SSData ssData) {
        this.ssData = ssData;
    }


    @Override
    public ForwardingInfo getForwardingInfo() {
        return forwardingInfo;
    }

    @Override
    public CallBarringInfo getCallBarringInfo() {
        return callBarringInfo;
    }

    @Override
    public SSData getSsData() {
        return ssData;
    }

    @Override
    public int getTag() throws MAPException {
        if (forwardingInfo != null) {
            return _TAG_forwardingInfo;
        } else if (callBarringInfo != null) {
            return _TAG_callBarringInfo;
        } else if (ssData != null) {
            return _TAG_ssData;
        } else {
            throw new MAPException("No of choices are supplied");
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
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
        this.forwardingInfo = null;
        this.callBarringInfo = null;
        this.ssData = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tag class or is primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
        case _TAG_forwardingInfo:
            this.forwardingInfo = new ForwardingInfoImpl();
            ((ForwardingInfoImpl) this.forwardingInfo).decodeData(asnInputStream, length);
            break;
        case _TAG_callBarringInfo:
            this.callBarringInfo = new CallBarringInfoImpl();
            ((CallBarringInfoImpl) this.callBarringInfo).decodeData(asnInputStream, length);
            break;
        case _TAG_ssData:
            this.ssData = new SSDataImpl();
            ((SSDataImpl) this.ssData).decodeData(asnInputStream, length);
            break;

        default:
            throw new MAPParsingComponentException("Error while " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
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
        int cnt = 0;
        if (forwardingInfo != null)
            cnt++;
        if (callBarringInfo != null)
            cnt++;
        if (ssData != null)
            cnt++;

        if (cnt == 0)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": no option is set.");
        if (cnt > 1)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": more than 1 option is set.");

        if (this.forwardingInfo != null)
            ((ForwardingInfoImpl) this.forwardingInfo).encodeData(asnOutputStream);
        if (this.callBarringInfo != null)
            ((CallBarringInfoImpl) this.callBarringInfo).encodeData(asnOutputStream);
        if (this.ssData != null)
            ((SSDataImpl) this.ssData).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.forwardingInfo != null) {
            sb.append("forwardingInfo=");
            sb.append(this.forwardingInfo.toString());
            sb.append(", ");
        }
        if (this.callBarringInfo != null) {
            sb.append("callBarringInfo=");
            sb.append(this.callBarringInfo.toString());
            sb.append(", ");
        }
        if (this.ssData != null) {
            sb.append("ssData=");
            sb.append(this.ssData.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
