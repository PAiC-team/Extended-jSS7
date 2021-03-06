
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSQoS;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtQoSSubscribedImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.QoSSubscribedImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSQoSImpl implements GPRSQoS, CAPAsnPrimitive {

    public static final String _PrimitiveName = "GPRSQoS";

    public static final int _ID_shortQoSFormat = 0;
    public static final int _ID_longQoSFormat = 1;

    private QoSSubscribed shortQoSFormat;
    private ExtQoSSubscribed longQoSFormat;

    public GPRSQoSImpl() {

    }

    public GPRSQoSImpl(QoSSubscribed shortQoSFormat) {
        this.shortQoSFormat = shortQoSFormat;
    }

    public GPRSQoSImpl(ExtQoSSubscribed longQoSFormat) {
        this.longQoSFormat = longQoSFormat;
    }

    @Override
    public QoSSubscribed getShortQoSFormat() {
        return this.shortQoSFormat;
    }

    @Override
    public ExtQoSSubscribed getLongQoSFormat() {
        return this.longQoSFormat;
    }

    @Override
    public int getTag() throws CAPException {
        if (shortQoSFormat != null) {
            return _ID_shortQoSFormat;
        } else {
            return _ID_longQoSFormat;
        }

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

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.shortQoSFormat = null;
        this.longQoSFormat = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_shortQoSFormat:
                    if (!asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".shortQoSFormat: Parameter is not primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.shortQoSFormat = new QoSSubscribedImpl();
                    ((QoSSubscribedImpl) this.shortQoSFormat).decodeData(asnInputStream, length);
                    break;
                case _ID_longQoSFormat:
                    if (!asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".longQoSFormat: Parameter is not primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.longQoSFormat = new ExtQoSSubscribedImpl();
                    ((ExtQoSSubscribedImpl) this.longQoSFormat).decodeData(asnInputStream, length);
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
        if (this.shortQoSFormat == null && this.longQoSFormat == null || this.shortQoSFormat != null
                && this.longQoSFormat != null) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        try {
            if (this.shortQoSFormat != null) {
                ((QoSSubscribedImpl) this.shortQoSFormat).encodeData(asnOutputStream);
            } else {
                ((ExtQoSSubscribedImpl) this.longQoSFormat).encodeData(asnOutputStream);
            }
        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.shortQoSFormat != null) {
            sb.append("shortQoSFormat=");
            sb.append(this.shortQoSFormat.toString());
        }

        if (this.longQoSFormat != null) {
            sb.append("longQoSFormat=");
            sb.append(this.longQoSFormat.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
