
package org.restcomm.protocols.ss7.map.primitives;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;

/**
 * @author abhayani
 * @author sergey vetyutnev
 *
 */
public class DiameterIdentityImpl extends OctetStringBase implements DiameterIdentity {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public DiameterIdentityImpl() {
        super(9, 55, "DiameterIdentity");
    }

    public DiameterIdentityImpl(byte[] data) {
        super(9, 55, "DiameterIdentity", data);
    }

    public byte[] getData() {
        return data;
    }

    // TODO: add implementing of internal structure (?)

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DiameterIdentityImpl> DIAMETER_IDENTITY_XML = new XMLFormat<DiameterIdentityImpl>(
            DiameterIdentityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, DiameterIdentityImpl diameterIdentity)
                throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                diameterIdentity.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(DiameterIdentityImpl diameterIdentity, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (diameterIdentity.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(diameterIdentity.data));
            }
        }
    };
}
