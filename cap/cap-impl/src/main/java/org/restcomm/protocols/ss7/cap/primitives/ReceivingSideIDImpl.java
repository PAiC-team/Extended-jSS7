package org.restcomm.protocols.ss7.cap.primitives;

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
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.inap.api.primitives.LegType;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ReceivingSideIDImpl implements ReceivingSideID, CAPAsnPrimitive {

    private static final String RECEIVING_SIDE_ID = "receivingSideID";

    public static final int _ID_receivingSideID = 1;

    public static final String _PrimitiveName = "ReceivingSideID";

    private LegType receivingSideID;

    public ReceivingSideIDImpl() {
    }

    public ReceivingSideIDImpl(LegType receivingSideID) {
        this.receivingSideID = receivingSideID;
    }

    @Override
    public LegType getReceivingSideID() {
        return receivingSideID;
    }

    @Override
    public int getTag() throws CAPException {
        return _ID_receivingSideID;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        return true;
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
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
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
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.receivingSideID = null;

        if (asnInputStream.getTag() != _ID_receivingSideID || asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC
                || !asnInputStream.isTagPrimitive())
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": choice receivingSideID has bad tag or tagClass or is not primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        int i1 = (int) asnInputStream.readIntegerData(length);
        this.receivingSideID = LegType.getInstance(i1);
        if (this.receivingSideID == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad LegType parameter",
                    CAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.receivingSideID == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": sendingSideID field must not be null");

        try {
            asnOutputStream.writeIntegerData(this.receivingSideID.getCode());
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.receivingSideID != null) {
            sb.append("receivingSideID=");
            sb.append(receivingSideID.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ReceivingSideIDImpl> RECEIVING_SIDE_ID_XML = new XMLFormat<ReceivingSideIDImpl>(
            ReceivingSideIDImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ReceivingSideIDImpl receivingSideID)
                throws XMLStreamException {
            receivingSideID.receivingSideID = LegType.getInstance(xml.get(RECEIVING_SIDE_ID, Integer.class));
        }

        @Override
        public void write(ReceivingSideIDImpl receivingSideID, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            xml.add(receivingSideID.receivingSideID.getCode(), RECEIVING_SIDE_ID, Integer.class);
        }
    };
}
