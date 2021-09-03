
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSRadioAccessCapability;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MSRadioAccessCapabilityImpl extends OctetStringBase implements MSRadioAccessCapability {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public MSRadioAccessCapabilityImpl() {
        super(1, 50, "MSRadioAccessCapability");
    }

    public MSRadioAccessCapabilityImpl(byte[] data) {
        super(1, 50, "MSRadioAccessCapability", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MSRadioAccessCapabilityImpl> MS_RADIO_ACCESS_CAPABILITY_XML = new XMLFormat<MSRadioAccessCapabilityImpl>(MSRadioAccessCapabilityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MSRadioAccessCapabilityImpl mSRadioAccessCapability) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                mSRadioAccessCapability.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(MSRadioAccessCapabilityImpl mSRadioAccessCapability, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (mSRadioAccessCapability.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(mSRadioAccessCapability.data));
            }
        }
    };
}
