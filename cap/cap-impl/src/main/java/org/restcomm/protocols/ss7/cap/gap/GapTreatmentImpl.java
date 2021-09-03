
package org.restcomm.protocols.ss7.cap.gap;

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
import org.restcomm.protocols.ss7.cap.api.gap.GapTreatment;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.InformationToSendImpl;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 */
public class GapTreatmentImpl implements GapTreatment, CAPAsnPrimitive {

    public static final int _ID_InformationToSend = 0;
    public static final int _ID_CauseCap = 1;

    private static final String INFORMATION_TO_SEND = "informationToSend";
    private static final String CAUSE_CAP = "causeCap";

    public static final String _PrimitiveName = "GapTreatment";

    private InformationToSend informationToSend;
    private CauseCap releaseCause;

    public GapTreatmentImpl() {
    }

    public GapTreatmentImpl(InformationToSend informationToSend) {
        this.informationToSend = informationToSend;
    }

    public GapTreatmentImpl(CauseCap releaseCause) {
        this.releaseCause = releaseCause;
    }

    public InformationToSend getInformationToSend() {
        return informationToSend;
    }

    public CauseCap getCause() {
        return releaseCause;
    }

    public int getTag() throws CAPException {
        if (informationToSend != null) {
            return _ID_InformationToSend;
        } else if (releaseCause != null) {
            return _ID_CauseCap;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        if (informationToSend != null) {
            return false;
        } else if (releaseCause != null) {
            return ((CauseCapImpl) releaseCause).getIsPrimitive();
        }
        return false;
    }

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

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.informationToSend = null;
        this.releaseCause = null;

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

            switch (asnInputStream.getTag()) {
                case _ID_InformationToSend: {
                    AsnInputStream ais1 = asnInputStream.readSequenceStreamData(length);
                    ais1.readTag();
                    this.informationToSend = new InformationToSendImpl();
                    ((InformationToSendImpl) this.informationToSend).decodeAll(ais1);
                    break;
                }
                case _ID_CauseCap: {
                    this.releaseCause = new CauseCapImpl();
                    ((CauseCapImpl) this.releaseCause).decodeData(asnInputStream, length);
                    break;
                }
                default: {
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            CAPParsingComponentExceptionReason.MistypedParameter);
                }
            }

        } else {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }

    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if ((this.informationToSend == null && this.releaseCause == null) || (this.informationToSend != null && this.releaseCause != null)) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        try {
            if (informationToSend != null) {
                ((InformationToSendImpl) informationToSend).encodeAll(asnOutputStream);
            } else if (releaseCause != null) {
                ((CauseCapImpl) releaseCause).encodeData(asnOutputStream);
            }
        } catch (CAPException e) {
            throw new CAPException("CAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    protected static final XMLFormat<GapTreatmentImpl> A_CH_CHARGING_ADDRESS_XML = new XMLFormat<GapTreatmentImpl>(GapTreatmentImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, GapTreatmentImpl gapTreatment) throws XMLStreamException {
            gapTreatment.informationToSend = xml.get(INFORMATION_TO_SEND, InformationToSendImpl.class);
            gapTreatment.releaseCause = xml.get(CAUSE_CAP, CauseCapImpl.class);
        }

        @Override
        public void write(GapTreatmentImpl gapTreatment, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (gapTreatment.informationToSend != null) {
                xml.add((InformationToSendImpl) gapTreatment.informationToSend, INFORMATION_TO_SEND, InformationToSendImpl.class);
            }
            if (gapTreatment.releaseCause != null) {
                xml.add((CauseCapImpl) gapTreatment.releaseCause, CAUSE_CAP, CauseCapImpl.class);
            }
        }
    };

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (informationToSend != null) {
            sb.append("informationToSend=");
            sb.append(informationToSend);
        } else if (releaseCause != null) {
            sb.append(", releaseCause=");
            sb.append(releaseCause);
        }

        sb.append("]");

        return sb.toString();
    }

}
