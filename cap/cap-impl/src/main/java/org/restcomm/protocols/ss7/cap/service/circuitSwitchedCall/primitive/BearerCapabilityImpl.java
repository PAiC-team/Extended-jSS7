
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

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
import org.restcomm.protocols.ss7.cap.api.isup.BearerCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.BearerCapability;
import org.restcomm.protocols.ss7.cap.isup.BearerCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class BearerCapabilityImpl implements BearerCapability, CAPAsnPrimitive {

    public static final int _ID_bearerCap = 0;

    private static final String BEARER_CAP_XML = "bearerCap";

    public static final String _PrimitiveName = "BearerCap";

    private BearerCap bearerCap;

    public BearerCapabilityImpl() {
    }

    public BearerCapabilityImpl(BearerCap bearerCap) {
        this.bearerCap = bearerCap;
    }

    public void setBearerCap(BearerCap bearerCap) {
        this.bearerCap = bearerCap;
    }

    @Override
    public BearerCap getBearerCap() {
        return bearerCap;
    }

    @Override
    public int getTag() throws CAPException {
        return _ID_bearerCap;
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

        this.bearerCap = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_bearerCap:
                    this.bearerCap = new BearerCapImpl();
                    ((BearerCapImpl) this.bearerCap).decodeData(asnInputStream, length);
                    break;

                default:
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            CAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
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

        if (this.bearerCap == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": bearerCap must not be null");

        ((BearerCapImpl) this.bearerCap).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.bearerCap != null) {
            sb.append("bearerCap=");
            sb.append(bearerCap.toString());
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BearerCapabilityImpl> BEARER_CAPABILITY_XML = new XMLFormat<BearerCapabilityImpl>(
            BearerCapabilityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BearerCapabilityImpl bearerCap) throws XMLStreamException {
            bearerCap.setBearerCap(xml.get(BEARER_CAP_XML, BearerCapImpl.class));
        }

        @Override
        public void write(BearerCapabilityImpl bearerCap, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (bearerCap.getBearerCap() != null)
                xml.add(((BearerCapImpl) bearerCap.getBearerCap()), BEARER_CAP_XML, BearerCapImpl.class);
        }
    };
}
