
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MSClassmark2Impl extends OctetStringBase implements MSClassmark2 {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public MSClassmark2Impl() {
        super(3, 3, "MSClassmark2");
    }

    public MSClassmark2Impl(byte[] data) {
        super(3, 3, "MSClassmark2", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MSClassmark2Impl> MS_CLASSMARK2_XML = new XMLFormat<MSClassmark2Impl>(MSClassmark2Impl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MSClassmark2Impl msClassmark2) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                msClassmark2.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(MSClassmark2Impl msClassmark2, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (msClassmark2.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(msClassmark2.data));
            }
        }
    };
}
