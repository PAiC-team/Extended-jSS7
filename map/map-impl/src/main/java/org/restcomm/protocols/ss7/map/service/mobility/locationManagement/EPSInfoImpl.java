
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.EPSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ISRInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PDNGWUpdate;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class EPSInfoImpl implements EPSInfo, MAPAsnPrimitive {

    public static final int _TAG_pndGwUpdate = 0;
    public static final int _TAG_isrInformation = 1;

    public static final String _PrimitiveName = "EPSInfo";

    private PDNGWUpdate pndGwUpdate;
    private ISRInformation isrInformation;

    public EPSInfoImpl() {
        super();
    }

    public EPSInfoImpl(PDNGWUpdate pndGwUpdate) {
        super();
        this.pndGwUpdate = pndGwUpdate;
        this.isrInformation = null;
    }

    public EPSInfoImpl(ISRInformation isrInformation) {
        super();
        this.pndGwUpdate = null;
        this.isrInformation = isrInformation;
    }

    @Override
    public PDNGWUpdate getPndGwUpdate() {
        return this.pndGwUpdate;
    }

    @Override
    public ISRInformation getIsrInformation() {
        return this.isrInformation;
    }

    @Override
    public int getTag() throws MAPException {
        if (this.pndGwUpdate != null)
            return _TAG_pndGwUpdate;
        else
            return _TAG_isrInformation;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (this.pndGwUpdate != null)
            return false;
        else
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

    @Override
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
        this.pndGwUpdate = null;
        this.isrInformation = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Primitive has bad tag class",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (tag) {
            case _TAG_pndGwUpdate:
                if (asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                this.pndGwUpdate = new PDNGWUpdateImpl();
                ((PDNGWUpdateImpl) this.pndGwUpdate).decodeData(asnInputStream, length);
                break;
            case _TAG_isrInformation:
                if (!asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                this.isrInformation = new ISRInformationImpl();
                ((ISRInformationImpl) this.isrInformation).decodeData(asnInputStream, length);
                break;

            default:
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.pndGwUpdate == null && this.isrInformation == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": all choices must not be null");
        if (this.pndGwUpdate != null && this.isrInformation != null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": all choices must not be not null");

        if (this.pndGwUpdate != null) {
            ((PDNGWUpdateImpl) this.pndGwUpdate).encodeData(asnOutputStream);
        } else {
            ((ISRInformationImpl) this.isrInformation).encodeData(asnOutputStream);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.pndGwUpdate != null) {
            sb.append("pndGwUpdate=");
            sb.append(this.pndGwUpdate.toString());
        }

        if (this.isrInformation != null) {
            sb.append("isrInformation=");
            sb.append(this.isrInformation.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
