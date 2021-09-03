
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.OAbandonSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class OAbandonSpecificInfoImpl extends SequenceBase implements OAbandonSpecificInfo {

    private static final String ROUTE_NOT_PERMITTED = "routeNotPermitted";

    public static final int _ID_routeNotPermitted = 50;

    private boolean routeNotPermitted;

    public OAbandonSpecificInfoImpl() {
        super("OAbandonSpecificInfo");
    }

    public OAbandonSpecificInfoImpl(boolean routeNotPermitted) {
        super("OAbandonSpecificInfo");
        this.routeNotPermitted = routeNotPermitted;
    }

    @Override
    public boolean getRouteNotPermitted() {
        return routeNotPermitted;
    }

    public void setRouteNotPermitted(boolean newValue) {
        routeNotPermitted = newValue;
    }

    protected void _decode(AsnInputStream ansIS, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.routeNotPermitted = false;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_routeNotPermitted:
                        ais.readNull();
                        this.routeNotPermitted = true;
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
    public void encodeData(AsnOutputStream asnOs) throws CAPException {

        try {
            if (this.routeNotPermitted)
                asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_routeNotPermitted);
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.routeNotPermitted) {
            sb.append("routeNotPermitted");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OAbandonSpecificInfoImpl> T_BUSY_SPECIFIC_INFO = new XMLFormat<OAbandonSpecificInfoImpl>(
            OAbandonSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, OAbandonSpecificInfoImpl oAbandonSpecificInfo)
                throws XMLStreamException {
            Boolean bval = xml.get(ROUTE_NOT_PERMITTED, Boolean.class);
            if (bval != null)
                oAbandonSpecificInfo.routeNotPermitted = bval;
        }

        @Override
        public void write(OAbandonSpecificInfoImpl oAbandonSpecificInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (oAbandonSpecificInfo.routeNotPermitted)
                xml.add(oAbandonSpecificInfo.routeNotPermitted, ROUTE_NOT_PERMITTED, Boolean.class);
        }
    };
}
