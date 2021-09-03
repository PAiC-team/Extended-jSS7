
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class CarrierImpl extends OctetStringBase implements Carrier {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public CarrierImpl() {
        super(4, 4, "Carrier");
    }

    public CarrierImpl(byte[] data) {
        super(4, 4, "Carrier", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CarrierImpl> CARRIER_XML = new XMLFormat<CarrierImpl>(CarrierImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CarrierImpl carrier) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                carrier.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(CarrierImpl carrier, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (carrier.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(carrier.data));
            }
        }
    };

}
