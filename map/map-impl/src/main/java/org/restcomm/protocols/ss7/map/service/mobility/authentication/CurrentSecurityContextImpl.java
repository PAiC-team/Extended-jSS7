
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CurrentSecurityContext;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.GSMSecurityContextData;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.UMTSSecurityContextData;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CurrentSecurityContextImpl implements CurrentSecurityContext, MAPAsnPrimitive {

    public static final int _TAG_gsmSecurityContextData = 0;
    public static final int _TAG_umtsSecurityContextData = 1;

    public static final String _PrimitiveName = "CurrentSecurityContext";

    private GSMSecurityContextData gsmSecurityContextData;
    private UMTSSecurityContextData umtsSecurityContextData;

    public CurrentSecurityContextImpl() {
        super();
        this.gsmSecurityContextData = null;
        this.umtsSecurityContextData = null;
    }

    public CurrentSecurityContextImpl(GSMSecurityContextData gsmSecurityContextData) {
        super();
        this.gsmSecurityContextData = gsmSecurityContextData;
    }

    public CurrentSecurityContextImpl(UMTSSecurityContextData umtsSecurityContextData) {
        super();
        this.umtsSecurityContextData = umtsSecurityContextData;
    }

    @Override
    public GSMSecurityContextData getGSMSecurityContextData() {
        return this.gsmSecurityContextData;
    }

    @Override
    public UMTSSecurityContextData getUMTSSecurityContextData() {
        return this.umtsSecurityContextData;
    }

    @Override
    public int getTag() throws MAPException {
        if (this.gsmSecurityContextData != null)
            return _TAG_gsmSecurityContextData;
        else
            return _TAG_umtsSecurityContextData;
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
        this.gsmSecurityContextData = null;
        this.umtsSecurityContextData = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Primitive has bad tag class or is primitive", MAPParsingComponentExceptionReason.MistypedParameter);

        switch (tag) {
            case _TAG_gsmSecurityContextData:
                this.gsmSecurityContextData = new GSMSecurityContextDataImpl();
                ((GSMSecurityContextDataImpl) this.gsmSecurityContextData).decodeData(asnInputStream, length);
                break;
            case _TAG_umtsSecurityContextData:
                this.umtsSecurityContextData = new UMTSSecurityContextDataImpl();
                ((UMTSSecurityContextDataImpl) this.umtsSecurityContextData).decodeData(asnInputStream, length);
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
        if (this.gsmSecurityContextData == null && this.umtsSecurityContextData == null || this.gsmSecurityContextData != null
                && this.umtsSecurityContextData != null) {
            throw new MAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.gsmSecurityContextData != null) {
            ((GSMSecurityContextDataImpl) this.gsmSecurityContextData).encodeData(asnOutputStream);
        } else {
            ((UMTSSecurityContextDataImpl) this.umtsSecurityContextData).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.gsmSecurityContextData != null) {
            sb.append(this.gsmSecurityContextData.toString());
            sb.append(", ");
        }

        if (this.umtsSecurityContextData != null) {
            sb.append(this.umtsSecurityContextData.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }
}
