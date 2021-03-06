package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LAC;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 *
 * @author sergey vetyutnev
 *
 */
public class LACImpl implements LAC, MAPAsnPrimitive {

    public static final String _PrimitiveName = "LAC";

    private byte[] data;

    public LACImpl() {
    }

    public LACImpl(byte[] data) {
        this.data = data;
    }

    public LACImpl(int lac) throws MAPException {
        this.data = new byte[2];

        data[0] = (byte) (lac / 256);
        data[1] = (byte) (lac % 256);
    }

    public byte[] getData() {
        return data;
    }

    public int getLac() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 2)
            throw new MAPException("Data length must be equal 5");

        int res = (data[0] & 0xFF) * 256 + (data[1] & 0xFF);
        return res;
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

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        try {
            this.data = asnInputStream.readOctetStringData(length);
            if (this.data.length != 2)
                throw new MAPParsingComponentException("Error decoding " + _PrimitiveName
                        + ": the field must contain from 2 to 2 octets. Contains: " + length,
                        MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding LAIFixedLength: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
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
        if (this.data.length != 2)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": field length must be equal 2");

        asnOutputStream.writeOctetStringData(this.data);
    }

    @Override
    public String toString() {

        int lac = 0;
        boolean goodData = false;

        try {
            lac = this.getLac();
            goodData = true;
        } catch (MAPException e) {
        }

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (goodData) {
            sb.append("Lac=");
            sb.append(lac);
        } else {
            sb.append("Data=");
            sb.append(this.printDataArr());
        }
        sb.append("]");

        return sb.toString();
    }

    private String printDataArr() {
        StringBuilder sb = new StringBuilder();
        if (this.data != null) {
            for (int b : this.data) {
                sb.append(b);
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
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
        LACImpl other = (LACImpl) obj;
        if (!Arrays.equals(data, other.data))
            return false;
        return true;
    }
}
