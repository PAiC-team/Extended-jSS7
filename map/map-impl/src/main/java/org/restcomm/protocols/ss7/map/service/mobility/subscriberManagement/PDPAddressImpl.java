
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDPAddress;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PDPAddressImpl extends OctetStringBase implements PDPAddress {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public PDPAddressImpl() {
        super(1, 16, "PDPAddress");
    }

    public PDPAddressImpl(byte[] data) {
        super(1, 16, "PDPAddress", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<PDPAddressImpl> PDP_ADDRESS_XML = new XMLFormat<PDPAddressImpl>(PDPAddressImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, PDPAddressImpl pdpAddress) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                pdpAddress.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(PDPAddressImpl pdpAddress, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (pdpAddress.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(pdpAddress.data));
            }
        }
    };

}
