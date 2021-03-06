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
import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ScfIDImpl implements ScfID, CAPAsnPrimitive {

    public static final String _DATA = "data";

    public static final String _PrimitiveName = "ScfID";

    private byte[] data;

    public ScfIDImpl() {
    }

    public ScfIDImpl(byte[] data) {
        this.data = data;
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.STRING_OCTET;
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

        this.data = asnInputStream.readOctetStringData(length);
        if (this.data.length < 2 || this.data.length > 10)
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": length must be from 2 to 10, real length = " + length,
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

        if (this.data == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": data field must not be null");
        if (this.data.length < 2 || this.data.length > 10)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": data field length must be from 2 to 10");

        asnOutputStream.writeOctetStringData(data);
    }

    protected static final XMLFormat<ScfIDImpl> SCF_ID_XML = new XMLFormat<ScfIDImpl>(ScfIDImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ScfIDImpl scfID) throws XMLStreamException {
            scfID.data = OctetStringBase.hexToBytes(xml.get(_DATA, String.class));
        }

        @Override
        public void write(ScfIDImpl scfID, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(OctetStringBase.bytesToHex(scfID.data), _DATA, String.class);
        }
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.data != null) {
            sb.append("data=[");
            sb.append(printDataArr(this.data));
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int b : arr) {
            sb.append(b);
            sb.append(", ");
        }

        return sb.toString();
    }
}
