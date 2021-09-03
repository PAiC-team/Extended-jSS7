
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class NAOliInfoImpl extends OctetStringLength1Base implements NAOliInfo {

    private static final String VALUE = "value";

    public NAOliInfoImpl() {
        super("NAOliInfo");
    }

    public NAOliInfoImpl(int data) {
        super("NAOliInfo", data);
    }

    @Override
    public int getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<NAOliInfoImpl> NA_OLI_INFO_XML = new XMLFormat<NAOliInfoImpl>(NAOliInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, NAOliInfoImpl naOliInfo) throws XMLStreamException {
            naOliInfo.data = xml.getAttribute(VALUE, 0);
        }

        @Override
        public void write(NAOliInfoImpl naOliInfo, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(VALUE, naOliInfo.data);
        }
    };
}
