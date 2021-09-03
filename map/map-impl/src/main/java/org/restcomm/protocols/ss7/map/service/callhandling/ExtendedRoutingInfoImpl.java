
package org.restcomm.protocols.ss7.map.service.callhandling;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CamelRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.RoutingInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/*
 *
 * @author cristian veliscu
 * @author sergey vetyutnev
 *
 */
public class ExtendedRoutingInfoImpl implements ExtendedRoutingInfo, MAPAsnPrimitive {

    private RoutingInfo routingInfo = null;
    private CamelRoutingInfo camelRoutingInfo = null;

    public static final int TAG_camel = 8;
    private static final String _PrimitiveName = "ExtendedRoutingInfo";

    public ExtendedRoutingInfoImpl() {
    }

    public ExtendedRoutingInfoImpl(RoutingInfo routingInfo) {
        this.routingInfo = routingInfo;
    }

    public ExtendedRoutingInfoImpl(CamelRoutingInfo camelRoutingInfo) {
        this.camelRoutingInfo = camelRoutingInfo;
    }

    @Override
    public RoutingInfo getRoutingInfo() {
        return this.routingInfo;
    }

    @Override
    public CamelRoutingInfo getCamelRoutingInfo() {
        return this.camelRoutingInfo;
    }

    @Override
    public int getTag() throws MAPException {
        if (routingInfo != null)
            return ((RoutingInfoImpl) routingInfo).getTag();
        return TAG_camel;
    }

    @Override
    public int getTagClass() {
        if (routingInfo != null)
            return ((RoutingInfoImpl) routingInfo).getTagClass();
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (routingInfo != null)
            return ((RoutingInfoImpl) routingInfo).getIsPrimitive();
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
        this.routingInfo = null;
        this.camelRoutingInfo = null;

        int tag = asnInputStream.getTag();
        if (asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
            switch (tag) {
                case Tag.SEQUENCE:
                case Tag.STRING_OCTET:
                    this.routingInfo = new RoutingInfoImpl();
                    ((RoutingInfoImpl) this.routingInfo).decodeData(asnInputStream, length);
                    break;
                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagNumber",
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case TAG_camel:
                    this.camelRoutingInfo = new CamelRoutingInfoImpl();
                    ((CamelRoutingInfoImpl) this.camelRoutingInfo).decodeData(asnInputStream, length);
                    break;
                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagNumber",
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
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
        if (this.routingInfo == null && this.camelRoutingInfo == null)
            throw new MAPException("Both routingInfo and camelRoutingInfo must not be null");
        if (this.routingInfo != null && this.camelRoutingInfo != null)
            throw new MAPException("Both routingInfo and camelRoutingInfo must not be not null");

        if (this.routingInfo != null) {
            ((RoutingInfoImpl) this.routingInfo).encodeData(asnOutputStream);
        } else {
            ((CamelRoutingInfoImpl) this.camelRoutingInfo).encodeData(asnOutputStream);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.routingInfo != null) {
            sb.append(this.routingInfo.toString());
        } else if (this.camelRoutingInfo != null) {
            sb.append(this.camelRoutingInfo.toString());
        }

        sb.append("]");
        return sb.toString();
    }
}