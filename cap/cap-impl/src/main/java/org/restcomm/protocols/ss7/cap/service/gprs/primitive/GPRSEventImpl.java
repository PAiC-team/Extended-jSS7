
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.primitives.MonitorMode;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSEventImpl extends SequenceBase implements GPRSEvent {

    public static final int _ID_gprsEventType = 0;
    public static final int _ID_monitorMode = 1;

    private GPRSEventType gprsEventType;
    private MonitorMode monitorMode;

    public GPRSEventImpl() {
        super("GPRSEvent");
    }

    public GPRSEventImpl(GPRSEventType gprsEventType, MonitorMode monitorMode) {
        super("GPRSEvent");
        this.gprsEventType = gprsEventType;
        this.monitorMode = monitorMode;
    }

    @Override
    public GPRSEventType getGPRSEventType() {
        return this.gprsEventType;
    }

    @Override
    public MonitorMode getMonitorMode() {
        return this.monitorMode;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.gprsEventType = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_gprsEventType:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".gprsEventType: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        int i1 = (int) ais.readInteger();
                        this.gprsEventType = GPRSEventType.getInstance(i1);
                        break;
                    case _ID_monitorMode:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".monitorMode: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        int i2 = (int) ais.readInteger();
                        this.monitorMode = MonitorMode.getInstance(i2);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.gprsEventType == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": parameter gprsEventType is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        if (this.monitorMode == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": parameter monitorMode is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        try {
            if (this.gprsEventType == null)
                throw new CAPException("Error while encoding " + _PrimitiveName + ": gprsEventType must not be null");

            if (this.monitorMode == null)
                throw new CAPException("Error while encoding " + _PrimitiveName + ": monitorMode must not be null");

            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_gprsEventType, this.gprsEventType.getCode());

            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_monitorMode, this.monitorMode.getCode());

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.gprsEventType != null) {
            sb.append("gprsEventType=");
            sb.append(this.gprsEventType.toString());
            sb.append(", ");
        }

        if (this.monitorMode != null) {
            sb.append("monitorMode=");
            sb.append(this.monitorMode.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
