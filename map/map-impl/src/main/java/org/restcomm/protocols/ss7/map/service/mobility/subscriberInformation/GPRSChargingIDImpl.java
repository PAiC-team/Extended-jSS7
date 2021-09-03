
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class GPRSChargingIDImpl extends OctetStringBase implements GPRSChargingID {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public GPRSChargingIDImpl() {
        super(4, 4, "GPRSChargingID");
    }

    public GPRSChargingIDImpl(byte[] data) {
        super(4, 4, "GPRSChargingID", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<GPRSChargingIDImpl> GPRS_CHARGING_ID_XML = new XMLFormat<GPRSChargingIDImpl>(GPRSChargingIDImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, GPRSChargingIDImpl charingId) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                charingId.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(GPRSChargingIDImpl charingId, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (charingId.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(charingId.data));
            }
        }
    };
}
