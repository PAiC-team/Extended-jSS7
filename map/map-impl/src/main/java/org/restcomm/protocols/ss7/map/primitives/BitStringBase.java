
package org.restcomm.protocols.ss7.map.primitives;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;

/**
 *
 * Super class for implementing primitives that are BIT STRING (SIZE (x..y))
 *
 * @author sergey vetyutnev
 *
 */
public abstract class BitStringBase implements MAPAsnPrimitive {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    protected BitSetStrictLength bitString;

    protected int minLength;
    protected int maxLength;
    protected int curLength;
    protected String _PrimitiveName;

    public BitStringBase(int minLength, int maxLength, int curLength, String _PrimitiveName) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.curLength = curLength;
        this._PrimitiveName = _PrimitiveName;

        this.bitString = new BitSetStrictLength(curLength);
    }

    public BitStringBase(int minLength, int maxLength, int curLength, String _PrimitiveName, BitSetStrictLength data) {
        this(minLength, maxLength, curLength, _PrimitiveName);
        this.bitString = data;
    }

    public int getTag() throws MAPException {
        return Tag.STRING_BIT;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return true;
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

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        if (!asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName + ": field must be primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        int minLen = (this.minLength - 1) / 8 + 2;
        int maxLen = (this.maxLength - 1) / 8 + 2;
        if (length < minLen || length > maxLen)
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName + ": the field must contain from "
                    + minLen + " to " + maxLen + " octets. Contains: " + length,
                    MAPParsingComponentExceptionReason.MistypedParameter);

        this.bitString = asnInputStream.readBitStringData(length);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.bitString == null)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": data is not defined");

        try {
            asnOutputStream.writeBitStringData(this.bitString);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bitString == null) ? 0 : bitString.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitStringBase other = (BitStringBase) obj;
        if (bitString == null) {
            if (other.bitString != null)
                return false;
        } else if (!bitString.equals(other.bitString))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [Data=");
        if (this.bitString != null) {
            for (int i = 0; i < this.bitString.getStrictLength(); i++) {
                if (i % 8 == 0) {
                    sb.append(" ");
                }
                if (this.bitString.get(i))
                    sb.append("1");
                else
                    sb.append("0");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BitStringBase> BIT_STRING_BASE_XML = new XMLFormat<BitStringBase>(BitStringBase.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BitStringBase bitStringBase) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                int i1 = 0;
                bitStringBase.bitString = new BitSetStrictLength(bitStringBase.curLength);
                for (char ch : s.toCharArray()) {
                    if (ch == '1')
                        bitStringBase.bitString.set(i1);
                    i1++;
                }
            }
        }

        @Override
        public void write(BitStringBase bitStringBase, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (bitStringBase.bitString != null) {
                StringBuilder sb = new StringBuilder();
                for (int i1 = 0; i1 < bitStringBase.bitString.getStrictLength(); i1++) {
                    if (bitStringBase.bitString.get(i1))
                        sb.append("1");
                    else
                        sb.append("0");
                }
                xml.setAttribute(DATA, sb.toString());
            }
        }
    };
}
