
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LAC;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LocationArea;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class LocationAreaImpl implements LocationArea, MAPAsnPrimitive {

    public static final int _TAG_laiFixedLength = 0;
    public static final int _TAG_lac = 1;

    public static final String _PrimitiveName = "LocationArea";

    private LAIFixedLength laiFixedLength;
    private LAC lac;

    public LocationAreaImpl() {
    }

    public LocationAreaImpl(LAIFixedLength laiFixedLength) {
        this.laiFixedLength = laiFixedLength;
    }

    public LocationAreaImpl(LAC lac) {
        this.lac = lac;
    }

    public LAIFixedLength getLAIFixedLength() {
        return laiFixedLength;
    }

    public LAC getLAC() {
        return lac;
    }

    public int getTag() throws MAPException {
        if (this.laiFixedLength != null)
            return _TAG_laiFixedLength;
        else
            return _TAG_lac;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
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
        this.laiFixedLength = null;
        this.lac = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Primitive has bad tag class or is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);

        switch (tag) {
            case _TAG_laiFixedLength:
                this.laiFixedLength = new LAIFixedLengthImpl();
                ((LAIFixedLengthImpl) this.laiFixedLength).decodeData(asnInputStream, length);
                break;
            case _TAG_lac:
                this.lac = new LACImpl();
                ((LACImpl) this.lac).decodeData(asnInputStream, length);
                break;

            default:
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
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
        if (this.laiFixedLength == null && this.lac == null || this.laiFixedLength != null && this.lac != null) {
            throw new MAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.laiFixedLength != null) {
            ((LAIFixedLengthImpl) this.laiFixedLength).encodeData(asnOutputStream);
        } else {
            ((LACImpl) this.lac).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.laiFixedLength != null) {
            sb.append(this.laiFixedLength.toString());
            sb.append(", ");
        }

        if (this.lac != null) {
            sb.append(this.lac.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }
}
