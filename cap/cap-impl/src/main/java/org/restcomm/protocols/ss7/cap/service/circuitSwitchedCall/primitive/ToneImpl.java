
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
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Tone;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ToneImpl extends SequenceBase implements Tone {

    public static final int _ID_toneID = 0;
    public static final int _ID_duration = 1;

    private static final String TONE_ID = "toneID";
    private static final String DURATION = "duration";

    private int toneID;
    private Integer duration;

    public ToneImpl() {
        super("Tone");
    }

    public ToneImpl(int toneID, Integer duration) {
        super("Tone");

        this.toneID = toneID;
        this.duration = duration;
    }

    @Override
    public int getToneID() {
        return toneID;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.toneID = 0;
        this.duration = null;
        boolean toneIDReceived = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_toneID:
                        this.toneID = (int) ais.readInteger();
                        toneIDReceived = true;
                        break;
                    case _ID_duration:
                        this.duration = (int) ais.readInteger();
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (toneIDReceived == false)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": toneID is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        try {
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_toneID, this.toneID);
            if (this.duration != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_duration, this.duration);

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("toneID=");
        sb.append(this.toneID);
        if (this.duration != null) {
            sb.append(", duration=");
            sb.append(this.duration);
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ToneImpl> TONE_XML = new XMLFormat<ToneImpl>(ToneImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ToneImpl tone) throws XMLStreamException {
            tone.toneID = xml.getAttribute(TONE_ID, 0);
            int vali = xml.getAttribute(DURATION, -1);
            if (vali != -1)
                tone.duration = vali;
        }

        @Override
        public void write(ToneImpl tone, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(TONE_ID, tone.toneID);
            if (tone.duration != null)
                xml.setAttribute(DURATION, (int) tone.duration);
        }
    };
}
