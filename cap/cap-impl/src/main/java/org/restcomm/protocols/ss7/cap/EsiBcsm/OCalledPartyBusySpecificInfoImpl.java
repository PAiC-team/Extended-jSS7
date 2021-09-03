
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.OCalledPartyBusySpecificInfo;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class OCalledPartyBusySpecificInfoImpl extends SequenceBase implements OCalledPartyBusySpecificInfo {

    private static final String BUSY_CAUSE = "busyCause";

    public static final int _ID_busyCause = 0;

    private CauseCap busyCause;

    public OCalledPartyBusySpecificInfoImpl() {
        super("OCalledPartyBusySpecificInfo");
    }

    public OCalledPartyBusySpecificInfoImpl(CauseCap busyCause) {
        super("OCalledPartyBusySpecificInfo");
        this.busyCause = busyCause;
    }

    @Override
    public CauseCap getBusyCause() {
        return busyCause;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.busyCause = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_busyCause:
                        this.busyCause = new CauseCapImpl();
                        ((CauseCapImpl) this.busyCause).decodeAll(ais);
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
        if (this.busyCause != null) {
            ((CauseCapImpl) this.busyCause).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_busyCause);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.busyCause != null) {
            sb.append("busyCause= [");
            sb.append(busyCause);
            sb.append("]");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OCalledPartyBusySpecificInfoImpl> ROUTE_SELECT_FAILURE_SPECIFIC_INFO_XML = new XMLFormat<OCalledPartyBusySpecificInfoImpl>(
            OCalledPartyBusySpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                OCalledPartyBusySpecificInfoImpl oCalledPartyBusySpecificInfo) throws XMLStreamException {
            oCalledPartyBusySpecificInfo.busyCause = xml.get(BUSY_CAUSE, CauseCapImpl.class);
        }

        @Override
        public void write(OCalledPartyBusySpecificInfoImpl oCalledPartyBusySpecificInfo,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            if (oCalledPartyBusySpecificInfo.busyCause != null) {
                xml.add(((CauseCapImpl) oCalledPartyBusySpecificInfo.busyCause), BUSY_CAUSE, CauseCapImpl.class);
            }
        }
    };
}
