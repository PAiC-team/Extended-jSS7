
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MidCallEvents;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.OMidCallSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
*
* @author sergey vetyutnev
*
*/
public class OMidCallSpecificInfoImpl extends SequenceBase implements OMidCallSpecificInfo {

    public static final String MID_CALL_EVENTS = "midCallEvents";

    public static final int _ID_midCallEvents = 1;

    private MidCallEvents midCallEvents;

    public OMidCallSpecificInfoImpl() {
        super("OMidCallSpecificInfo");
    }

    public OMidCallSpecificInfoImpl(MidCallEvents midCallEvents) {
        super("OMidCallSpecificInfo");

        this.midCallEvents = midCallEvents;
    }

    @Override
    public MidCallEvents getMidCallEvents() {
        return midCallEvents;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.midCallEvents = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_midCallEvents:
                    AsnInputStream ais2 = ais.readSequenceStream();
                    ais2.readTag();
                    this.midCallEvents = new MidCallEventsImpl();
                    ((MidCallEventsImpl) this.midCallEvents).decodeAll(ais2);
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
            if (this.midCallEvents != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_midCallEvents);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((MidCallEventsImpl) this.midCallEvents).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.midCallEvents != null) {
            sb.append("midCallEvents=");
            sb.append(midCallEvents.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OMidCallSpecificInfoImpl> O_MID_CALL_SPECIFIC_INFO_XML = new XMLFormat<OMidCallSpecificInfoImpl>(
            OMidCallSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, OMidCallSpecificInfoImpl oMidCallSpecificInfo)
                throws XMLStreamException {
            oMidCallSpecificInfo.midCallEvents = xml.get(MID_CALL_EVENTS, MidCallEventsImpl.class);
        }

        @Override
        public void write(OMidCallSpecificInfoImpl oMidCallSpecificInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (oMidCallSpecificInfo.midCallEvents != null) {
                xml.add(((MidCallEventsImpl) oMidCallSpecificInfo.midCallEvents), MID_CALL_EVENTS, MidCallEventsImpl.class);
            }
        }
    };

}
