
package org.restcomm.protocols.ss7.map.primitives;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class IMSIImpl extends TbcdString implements IMSI {

    private static final String NUMBER = "number";

    public IMSIImpl() {
        super(3, 8, "IMSI");
    }

    public IMSIImpl(String data) {
        super(3, 8, "IMSI", data);
    }

    public String getData() {
        return this.data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<IMSIImpl> IMSI_XML = new XMLFormat<IMSIImpl>(IMSIImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, IMSIImpl imsi) throws XMLStreamException {
            imsi.data = xml.getAttribute(NUMBER, "");
        }

        @Override
        public void write(IMSIImpl imsi, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(NUMBER, imsi.data);
        }
    };
}
