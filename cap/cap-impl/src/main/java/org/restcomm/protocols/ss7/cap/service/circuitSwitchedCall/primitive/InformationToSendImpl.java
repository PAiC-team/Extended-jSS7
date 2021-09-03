
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InbandInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Tone;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class InformationToSendImpl implements InformationToSend, CAPAsnPrimitive {

    public static final int _ID_inbandInfo = 0;
    public static final int _ID_tone = 1;

    public static final String _PrimitiveName = "InformationToSend";

    private static final String INBAND_INFO = "inbandInfo";
    private static final String TONE = "tone";

    private InbandInfo inbandInfo;
    private Tone tone;

    public InformationToSendImpl() {
    }

    public InformationToSendImpl(InbandInfo inbandInfo) {
        this.inbandInfo = inbandInfo;
    }

    public InformationToSendImpl(Tone tone) {
        this.tone = tone;
    }

    @Override
    public InbandInfo getInbandInfo() {
        return inbandInfo;
    }

    @Override
    public Tone getTone() {
        return tone;
    }

    @Override
    public int getTag() throws CAPException {

        if (this.inbandInfo != null) {
            return _ID_inbandInfo;
        } else if (this.tone != null) {
            return _ID_tone;
        } else {
            throw new CAPException("Error while encoding " + _PrimitiveName + ": no of choices has been definite");
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

        this.inbandInfo = null;
        this.tone = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || asnInputStream.isTagPrimitive())
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tagClass or is primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _ID_inbandInfo:
                this.inbandInfo = new InbandInfoImpl();
                ((InbandInfoImpl) this.inbandInfo).decodeData(asnInputStream, length);
                break;
            case _ID_tone:
                this.tone = new ToneImpl();
                ((ToneImpl) this.tone).decodeData(asnInputStream, length);
                break;
            default:
                throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                        CAPParsingComponentExceptionReason.MistypedParameter);
        }
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
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        int choiceCnt = 0;
        if (this.inbandInfo != null)
            choiceCnt++;
        if (this.tone != null)
            choiceCnt++;

        if (choiceCnt != 1)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": only one choice must be definite, found: "
                    + choiceCnt);

        if (this.inbandInfo != null)
            ((InbandInfoImpl) this.inbandInfo).encodeData(asnOutputStream);
        if (this.tone != null)
            ((ToneImpl) this.tone).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.inbandInfo != null) {
            sb.append("inbandInfo=");
            sb.append(inbandInfo.toString());
        }
        if (this.tone != null) {
            sb.append(" tone=");
            sb.append(tone.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<InformationToSendImpl> INFORMATION_TO_SEND_XML = new XMLFormat<InformationToSendImpl>(
            InformationToSendImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, InformationToSendImpl informationToSend)
                throws XMLStreamException {
            informationToSend.inbandInfo = xml.get(INBAND_INFO, InbandInfoImpl.class);
            informationToSend.tone = xml.get(TONE, ToneImpl.class);

            int choiceCount = 0;
            if (informationToSend.inbandInfo != null)
                choiceCount++;
            if (informationToSend.tone != null)
                choiceCount++;

            if (choiceCount != 1)
                throw new XMLStreamException("MessageID decoding error: there must be one choice selected, found: "
                        + choiceCount);
        }

        @Override
        public void write(InformationToSendImpl informationToSend, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (informationToSend.inbandInfo != null)
                xml.add((InbandInfoImpl) informationToSend.inbandInfo, INBAND_INFO, InbandInfoImpl.class);
            if (informationToSend.tone != null)
                xml.add((ToneImpl) informationToSend.tone, TONE, ToneImpl.class);
        }
    };
}
