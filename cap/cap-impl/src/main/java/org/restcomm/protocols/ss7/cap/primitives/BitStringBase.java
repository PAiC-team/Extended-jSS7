
package org.restcomm.protocols.ss7.cap.primitives;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;

/**
 *
 * Super class for implementing primitives that are BIT STRING (SIZE (x..y))
 *
 * @author sergey vetyutnev
 *
 */
public abstract class BitStringBase implements CAPAsnPrimitive {

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

    public int getTag() throws CAPException {
        return Tag.STRING_BIT;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

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
        if (!asnInputStream.isTagPrimitive())
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName + ": field must be primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        int minLen = (this.minLength - 1) / 8 + 2;
        int maxLen = (this.maxLength - 1) / 8 + 2;
        if (length < minLen || length > maxLen)
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName + ": the field must contain from "
                    + minLen + " to " + maxLen + " octets. Contains: " + length,
                    CAPParsingComponentExceptionReason.MistypedParameter);

        this.bitString = asnInputStream.readBitStringData(length);
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

        if (this.bitString == null)
            throw new CAPException("Error while encoding the " + _PrimitiveName + ": data is not defined");

        try {
            asnOutputStream.writeBitStringData(this.bitString);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
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
}
