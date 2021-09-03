
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.CallAcceptedSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class CallAcceptedSpecificInfoImpl extends SequenceBase implements CallAcceptedSpecificInfo {

    private static final String LOCATION_INFORMATION = "locationInformation";

    public static final int _ID_locationInformation = 50;

    private LocationInformation locationInformation;

    public CallAcceptedSpecificInfoImpl() {
        super("CallAcceptedSpecificInfo");
    }

    public CallAcceptedSpecificInfoImpl(LocationInformation locationInformation) {
        super("CallAcceptedSpecificInfo");
        this.locationInformation = locationInformation;
    }

    @Override
    public LocationInformation getLocationInformation() {
        return locationInformation;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.locationInformation = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_locationInformation:
                        this.locationInformation = new LocationInformationImpl();
                        ((LocationInformationImpl) this.locationInformation).decodeAll(ais);
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
            if (this.locationInformation != null) {
                ((LocationInformationImpl) this.locationInformation).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_locationInformation);
            }
        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.locationInformation != null) {
            sb.append("locationInformation=");
            sb.append(locationInformation);
            sb.append("]");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CallAcceptedSpecificInfoImpl> CALL_ACCEPTED_SPECIFIC_INFO_XML = new XMLFormat<CallAcceptedSpecificInfoImpl>(
            CallAcceptedSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CallAcceptedSpecificInfoImpl callAcceptedSpecificInfo)
                throws XMLStreamException {
            callAcceptedSpecificInfo.locationInformation = xml.get(LOCATION_INFORMATION, LocationInformationImpl.class);
        }

        @Override
        public void write(CallAcceptedSpecificInfoImpl callAcceptedSpecificInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            if (callAcceptedSpecificInfo.locationInformation != null) {
                xml.add((LocationInformationImpl) callAcceptedSpecificInfo.locationInformation, LOCATION_INFORMATION, LocationInformationImpl.class);
            }
        }
    };

}
