
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSQoSExtension;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Ext2QoSSubscribed;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.Ext2QoSSubscribedImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSQoSExtensionImpl extends SequenceBase implements GPRSQoSExtension {

    public static final int _ID_supplementToLongQoSFormat = 0;

    private Ext2QoSSubscribed supplementToLongQoSFormat;

    public GPRSQoSExtensionImpl() {
        super("GPRSQoSExtension");
    }

    public GPRSQoSExtensionImpl(Ext2QoSSubscribed supplementToLongQoSFormat) {
        super("GPRSQoSExtension");
        this.supplementToLongQoSFormat = supplementToLongQoSFormat;
    }

    public void setSupplementToLongQoSFormat(Ext2QoSSubscribed supplementToLongQoSFormat) {
        this.supplementToLongQoSFormat = supplementToLongQoSFormat;
    }

    @Override
    public Ext2QoSSubscribed getSupplementToLongQoSFormat() {
        return this.supplementToLongQoSFormat;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.supplementToLongQoSFormat = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_supplementToLongQoSFormat:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".supplementToLongQoSFormat: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.supplementToLongQoSFormat = new Ext2QoSSubscribedImpl();
                        ((Ext2QoSSubscribedImpl) this.supplementToLongQoSFormat).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.supplementToLongQoSFormat == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": supplementToLongQoSFormat is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
            if (this.supplementToLongQoSFormat == null)
                throw new CAPException("Error while encoding " + _PrimitiveName
                        + ": supplementToLongQoSFormat must not be null");

            ((Ext2QoSSubscribedImpl) this.supplementToLongQoSFormat).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _ID_supplementToLongQoSFormat);

        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.supplementToLongQoSFormat != null) {
            sb.append("supplementToLongQoSFormat=");
            sb.append(this.supplementToLongQoSFormat.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
