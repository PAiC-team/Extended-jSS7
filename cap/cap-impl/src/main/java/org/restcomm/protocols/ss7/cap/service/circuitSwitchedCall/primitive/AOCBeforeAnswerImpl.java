package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCBeforeAnswer;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCSubsequent;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAI_GSM0224;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 *
 * @author sergey vetyutnev
 *
 */
public class AOCBeforeAnswerImpl implements AOCBeforeAnswer, CAPAsnPrimitive {

    public static final int _ID_cAI_GSM0224 = 0;
    public static final int _ID_aOCSubsequent = 1;

    public static final String _PrimitiveName = "AOCBeforeAnswer";

    private CAI_GSM0224 aocInitial;
    private AOCSubsequent aocSubsequent;

    public AOCBeforeAnswerImpl() {
    }

    public AOCBeforeAnswerImpl(CAI_GSM0224 aocInitial, AOCSubsequent aocSubsequent) {
        this.aocInitial = aocInitial;
        this.aocSubsequent = aocSubsequent;
    }

    @Override
    public CAI_GSM0224 getAOCInitial() {
        return aocInitial;
    }

    @Override
    public AOCSubsequent getAOCSubsequent() {
        return aocSubsequent;
    }

    @Override
    public int getTag() throws CAPException {
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
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.aocInitial = null;
        this.aocSubsequent = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_cAI_GSM0224:
                        this.aocInitial = new CAI_GSM0224Impl();
                        ((CAI_GSM0224Impl) this.aocInitial).decodeAll(ais);
                        break;
                    case _ID_aOCSubsequent:
                        this.aocSubsequent = new AOCSubsequentImpl();
                        ((AOCSubsequentImpl) this.aocSubsequent).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.aocInitial == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": aocInitial is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream aos) throws CAPException {

        if (this.aocInitial == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": aocInitial must not be null");

        ((CAI_GSM0224Impl) this.aocInitial).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, _ID_cAI_GSM0224);

        if (this.aocSubsequent != null)
            ((AOCSubsequentImpl) this.aocSubsequent).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, _ID_aOCSubsequent);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.aocInitial != null) {
            sb.append("aocInitial=");
            sb.append(aocInitial.toString());
        }
        if (this.aocSubsequent != null) {
            sb.append(", aocSubsequent=");
            sb.append(aocSubsequent.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
