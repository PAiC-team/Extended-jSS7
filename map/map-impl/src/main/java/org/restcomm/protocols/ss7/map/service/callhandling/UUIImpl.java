
package org.restcomm.protocols.ss7.map.service.callhandling;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.callhandling.UUI;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class UUIImpl extends OctetStringBase implements UUI {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public UUIImpl() {
        super(1, 131, "UUI");
    }

    public UUIImpl(byte[] data) {
        super(1, 131, "UUI", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<UUIImpl> UUI_XML = new XMLFormat<UUIImpl>(UUIImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, UUIImpl uui) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                uui.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(UUIImpl uui, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (uui.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(uui.data));
            }
        }
    };

}
