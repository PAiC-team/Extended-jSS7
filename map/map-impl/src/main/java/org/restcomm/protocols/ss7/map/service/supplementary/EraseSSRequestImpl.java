
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
import org.restcomm.protocols.ss7.map.api.service.supplementary.EraseSSRequest;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;

/**
*
* @author sergey vetyutnev
*
*/
public class EraseSSRequestImpl extends SupplementaryMessageImpl implements EraseSSRequest {

    public static final String _PrimitiveName = "EraseSSRequest";

    private SSForBSCode ssForBSCode;

    public EraseSSRequestImpl() {
    }

    public EraseSSRequestImpl(SSForBSCode ssForBSCode) {
        this.ssForBSCode = ssForBSCode;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.eraseSS_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.eraseSS;
    }

    @Override
    public SSForBSCode getSsForBSCode() {
        return ssForBSCode;
    }

    @Override
    public int getTag() throws MAPException {
        if (ssForBSCode != null)
            return ((SSForBSCodeImpl) ssForBSCode).getTag();

        throw new MAPException("ssForBSCode is not defined");
    }

    @Override
    public int getTagClass() {
        if (ssForBSCode != null)
            return ((SSForBSCodeImpl) ssForBSCode).getTagClass();

        return 0;
    }

    @Override
    public boolean getIsPrimitive() {
        if (ssForBSCode != null)
            return ((SSForBSCodeImpl) ssForBSCode).getIsPrimitive();

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
        this.ssForBSCode = new SSForBSCodeImpl();
        ((SSForBSCodeImpl) this.ssForBSCode).decodeData(asnInputStream, length);
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
        if (ssForBSCode == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": ssForBSCode parameter is not defined.");

        if (this.ssForBSCode != null)
            ((SSForBSCodeImpl) this.ssForBSCode).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssForBSCode != null) {
            sb.append("ssForBSCode=");
            sb.append(this.ssForBSCode);
        }

        sb.append("]");

        return sb.toString();
    }

}
