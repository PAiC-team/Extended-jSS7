
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSNetworkCapability;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MSNetworkCapabilityImpl extends OctetStringBase implements MSNetworkCapability {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public MSNetworkCapabilityImpl() {
        super(1, 8, "MSNetworkCapability");
    }

    public MSNetworkCapabilityImpl(byte[] data) {
        super(1, 8, "MSNetworkCapability", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MSNetworkCapabilityImpl> MS_NETWORK_CAPABILITY_XML = new XMLFormat<MSNetworkCapabilityImpl>(MSNetworkCapabilityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MSNetworkCapabilityImpl mSNetworkCapability) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                mSNetworkCapability.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(MSNetworkCapabilityImpl mSNetworkCapability, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (mSNetworkCapability.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(mSNetworkCapability.data));
            }
        }
    };
}
