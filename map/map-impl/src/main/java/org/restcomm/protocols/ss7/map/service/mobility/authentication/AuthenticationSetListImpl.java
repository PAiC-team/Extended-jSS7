
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.QuintupletList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.TripletList;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AuthenticationSetListImpl implements AuthenticationSetList, MAPAsnPrimitive {

    public static final int _TAG_tripletList = 0;
    public static final int _TAG_quintupletList = 1;

    public static final String _PrimitiveName = "AuthenticationSetList";

    private TripletList tripletList;
    private QuintupletList quintupletList;
    private long mapProtocolVersion;

    public AuthenticationSetListImpl() {
    }

    public AuthenticationSetListImpl(TripletList tripletList) {
        this.tripletList = tripletList;
    }

    public AuthenticationSetListImpl(QuintupletList quintupletList) {
        this.quintupletList = quintupletList;
        this.mapProtocolVersion = 3;
    }

    public TripletList getTripletList() {
        return tripletList;
    }

    public QuintupletList getQuintupletList() {
        return quintupletList;
    }

    public long getMapProtocolVersion() {
        return mapProtocolVersion;
    }

    public void setMapProtocolVersion(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion >= 3) {
            if (tripletList != null)
                return _TAG_tripletList;
            else
                return _TAG_quintupletList;
        } else {
            return Tag.SEQUENCE;
        }
    }

    public int getTagClass() {
        if (this.mapProtocolVersion >= 3)
            return Tag.CLASS_CONTEXT_SPECIFIC;
        else
            return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
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
        this.tripletList = null;
        this.quintupletList = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) { // MAP V3
            switch (tag) {
                case _TAG_tripletList:
                    this.tripletList = new TripletListImpl();
                    ((TripletListImpl) this.tripletList).decodeData(asnInputStream, length);
                    break;
                case _TAG_quintupletList:
                    this.quintupletList = new QuintupletListImpl();
                    ((QuintupletListImpl) this.quintupletList).decodeData(asnInputStream, length);
                    break;

                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
            mapProtocolVersion = 3;
        } else if (asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) { // MAP V2
            switch (tag) {
                case Tag.SEQUENCE:
                case 0: // this special case when tag & tagClass are not set
                    this.tripletList = new TripletListImpl();
                    ((TripletListImpl) this.tripletList).decodeData(asnInputStream, length);
                    break;
                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tag for MAP V2",
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
            mapProtocolVersion = 2;
        } else {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
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
        if (this.tripletList == null && this.quintupletList == null || this.tripletList != null && this.quintupletList != null) {
            throw new MAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.tripletList != null) {
            ((TripletListImpl) this.tripletList).encodeData(asnOutputStream);
        } else {
            ((QuintupletListImpl) this.quintupletList).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AuthenticationSetList [");

        if (this.tripletList != null) {
            sb.append(this.tripletList.toString());
            sb.append(", ");
        }

        if (this.quintupletList != null) {
            sb.append(this.quintupletList.toString());
            sb.append(", ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(this.mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }
}
