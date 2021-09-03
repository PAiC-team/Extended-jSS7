
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
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedDigits;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedInfo;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CollectedInfoImpl implements CollectedInfo, CAPAsnPrimitive {

    public static final int _ID_collectedDigits = 0;

    public static final String _PrimitiveName = "CollectedInfo";

    private static final String COLLECTED_DIGITS = "collectedDigits";

    private CollectedDigits collectedDigits;

    public CollectedInfoImpl() {
    }

    public CollectedInfoImpl(CollectedDigits collectedDigits) {
        this.collectedDigits = collectedDigits;
    }

    @Override
    public CollectedDigits getCollectedDigits() {
        return collectedDigits;
    }

    @Override
    public int getTag() throws CAPException {

        if (this.collectedDigits != null) {
            return _ID_collectedDigits;
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

        this.collectedDigits = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || asnInputStream.isTagPrimitive())
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tagClass or is primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _ID_collectedDigits:
                this.collectedDigits = new CollectedDigitsImpl();
                ((CollectedDigitsImpl) this.collectedDigits).decodeData(asnInputStream, length);
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
        if (this.collectedDigits != null)
            choiceCnt++;
        if (choiceCnt != 1)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": only one choice must be definite, found: "
                    + choiceCnt);

        if (this.collectedDigits != null)
            ((CollectedDigitsImpl) this.collectedDigits).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.collectedDigits != null) {
            sb.append("collectedDigits=");
            sb.append(collectedDigits.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CollectedInfoImpl> COLLECTED_INFO_XML = new XMLFormat<CollectedInfoImpl>(
            CollectedInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CollectedInfoImpl collectedInfo) throws XMLStreamException {
            collectedInfo.collectedDigits = xml.get(COLLECTED_DIGITS, CollectedDigitsImpl.class);

            int choiceCount = 0;
            if (collectedInfo.collectedDigits != null)
                choiceCount++;

            if (choiceCount != 1)
                throw new XMLStreamException("CollectedInfo decoding error: there must be one choice selected, found: "
                        + choiceCount);
        }

        @Override
        public void write(CollectedInfoImpl collectedInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (collectedInfo.collectedDigits != null)
                xml.add((CollectedDigitsImpl) collectedInfo.collectedDigits, COLLECTED_DIGITS, CollectedDigitsImpl.class);
        }
    };
}
