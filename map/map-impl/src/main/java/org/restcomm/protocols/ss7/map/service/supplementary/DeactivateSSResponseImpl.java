
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.DeactivateSSResponse;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfo;

/**
*
* @author sergey vetyutnev
*
*/
public class DeactivateSSResponseImpl extends SupplementaryMessageImpl implements DeactivateSSResponse {

    public static final String _PrimitiveName = "DeactivateSSResponse";

    private SSInfo ssInfo;

    public DeactivateSSResponseImpl() {
    }

    public DeactivateSSResponseImpl(SSInfo ssInfo) {
        this.ssInfo = ssInfo;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.deactivateSS_Response;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.deactivateSS;
    }

    @Override
    public SSInfo getSsInfo() {
        return ssInfo;
    }

    @Override
    public int getTag() throws MAPException {
        if (ssInfo != null)
            return ((SSInfoImpl) ssInfo).getTag();

        throw new MAPException("ssInfo is not defined");
    }

    @Override
    public int getTagClass() {
        if (ssInfo != null)
            return ((SSInfoImpl) ssInfo).getTagClass();

        return 0;
    }

    @Override
    public boolean getIsPrimitive() {
        if (ssInfo != null)
            return ((SSInfoImpl) ssInfo).getIsPrimitive();

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
        this.ssInfo = null;
        this.ssInfo = new SSInfoImpl();
        ((SSInfoImpl) this.ssInfo).decodeData(asnInputStream, length);
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
        if (ssInfo == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": ssInfo parameter is not defined.");

        if (this.ssInfo != null)
            ((SSInfoImpl) this.ssInfo).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssInfo != null) {
            sb.append("ssInfo=");
            sb.append(this.ssInfo);
        }

        sb.append("]");

        return sb.toString();
    }

}
