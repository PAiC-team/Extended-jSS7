
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.RouteSelectFailureSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class RouteSelectFailureSpecificInfoImpl extends SequenceBase implements RouteSelectFailureSpecificInfo {

    private static final String CAUSE_CAP = "causeCap";

    public static final int _ID_failureCause = 0;

    private CauseCap failureCause;

    public RouteSelectFailureSpecificInfoImpl() {
        super("RouteSelectFailureSpecificInfo");
    }

    public RouteSelectFailureSpecificInfoImpl(CauseCap failureCause) {
        super("RouteSelectFailureSpecificInfo");
        this.failureCause = failureCause;
    }

    @Override
    public CauseCap getFailureCause() {
        return failureCause;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.failureCause = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_failureCause:
                        this.failureCause = new CauseCapImpl();
                        ((CauseCapImpl) this.failureCause).decodeAll(ais);
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
        if (this.failureCause != null) {
            ((CauseCapImpl) this.failureCause).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_failureCause);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.failureCause != null) {
            sb.append("failureCause= {");
            sb.append(failureCause);
            sb.append("]");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<RouteSelectFailureSpecificInfoImpl> ROUTE_SELECT_FAILURE_SPECIFIC_INFO_XML = new XMLFormat<RouteSelectFailureSpecificInfoImpl>(
            RouteSelectFailureSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                RouteSelectFailureSpecificInfoImpl routeSelectFailureSpecificInfo) throws XMLStreamException {
            routeSelectFailureSpecificInfo.failureCause = xml.get(CAUSE_CAP, CauseCapImpl.class);
        }

        @Override
        public void write(RouteSelectFailureSpecificInfoImpl routeSelectFailureSpecificInfo,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            if (routeSelectFailureSpecificInfo.failureCause != null) {
                xml.add(((CauseCapImpl) routeSelectFailureSpecificInfo.failureCause), CAUSE_CAP, CauseCapImpl.class);
            }
        }
    };
}
