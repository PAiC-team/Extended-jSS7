
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class LSAIdentityImpl extends OctetStringBase implements LSAIdentity {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public LSAIdentityImpl() {
        super(3, 3, "LSAIdentity");
    }

    public LSAIdentityImpl(byte[] data) {
        super(3, 3, "LSAIdentity", data);
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean isPlmnSignificantLSA() {
        return ((this.data[2] & 0x01) == 0x01);
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<LSAIdentityImpl> LSA_IDENTITY_XML = new XMLFormat<LSAIdentityImpl>(LSAIdentityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, LSAIdentityImpl lsaIdentity) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                lsaIdentity.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(LSAIdentityImpl lsaIdentity, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (lsaIdentity.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(lsaIdentity.data));
            }
        }
    };
}
