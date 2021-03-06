
package org.restcomm.protocols.ss7.map.primitives;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;

/**
 *
 * Super class for implementing primitives that are OCTET STRING (SIZE (x..y))
 *
 * @author sergey vetyutnev
 * @author alerant appngin
 *
 */
public abstract class OctetStringBase implements MAPAsnPrimitive {

    private static final char[] digits = "0123456789ABCDEF".toCharArray();

    protected byte[] data;

    protected int minLength;
    protected int maxLength;
    protected String _PrimitiveName;

    public OctetStringBase(int minLength, int maxLength, String _PrimitiveName) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this._PrimitiveName = _PrimitiveName;
    }

    public OctetStringBase(int minLength, int maxLength, String _PrimitiveName, byte[] data) {
        this(minLength, maxLength, _PrimitiveName);

        this.data = data;
    }

    public int getTag() throws MAPException {
        return Tag.STRING_OCTET;
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
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException {
        if (!asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName + ": field must be primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (length < this.minLength || length > this.maxLength)
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName + ": the field must contain from "
                    + this.minLength + " to " + this.maxLength + " octets. Contains: " + length,
                    MAPParsingComponentExceptionReason.MistypedParameter);

        data = new byte[length];
        asnInputStream.read(data);
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
        if (this.data == null)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": data is not defined");

        if (this.data.length < this.minLength || this.data.length > this.maxLength)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": data field length must be from "
                    + this.minLength + " to " + this.maxLength + " octets");

        asnOutputStream.write(this.data);
    }

    @Override
    public int hashCode() {
        if (data == null)
            return 0;

        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(data);
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
        OctetStringBase other = (OctetStringBase) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!Arrays.equals(data, other.data))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return _PrimitiveName + " [Data= " + this.printDataArr() + "]";
    }

    protected String printDataArr() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        if (this.data != null) {
            for (byte b : this.data) {
                if (first)
                    first = false;
                else
                    sb.append(", ");
                sb.append(b & 0xFF);
            }
        }

        return sb.toString();
    }

    protected static String bytesToHex(byte[] data) {
        if (data == null)
            return null;
        char[] c = new char[data.length * 2];
        for (int i = 0, j = i; i < data.length; i++, j += 2) {
            c[j] = digits[data[i] >> 4 & 0x0F];
            c[j + 1] = digits[data[i] & 0x0F];
        }
        return new String(c);
    }

    private static byte byteFromHexChar(char c) {
        if ('0' <= c && c <= '9')
            return (byte) (c - '0');
        c = Character.toUpperCase(c);
        if ('A' <= c && c <= 'F')
            return (byte) (c - 'A' + 10);
        else
            throw new IllegalArgumentException("Invalid hex character: " + c);
    }

    protected static byte[] hexToBytes(String hex) {
        if (hex == null)
            return null;
        char[] c = hex.toCharArray();
        if ((c.length & 1) > 0)
            throw new IllegalArgumentException("Hex string must be 2n characters long!");
        byte[] b = new byte[c.length / 2];
        for (int i = 0, j = i; i < b.length; i++, j += 2) {
            b[i] = (byte) ((byteFromHexChar(c[j]) << 4) + byteFromHexChar(c[j + 1]));
        }
        return b;
    }
}
