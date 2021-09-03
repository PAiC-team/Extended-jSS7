
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.LowLayerCompatibility;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class LowLayerCompatibilityImpl extends OctetStringBase implements LowLayerCompatibility {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public LowLayerCompatibilityImpl() {
        super(1, 16, "LowLayerCompatibility");
    }

    public LowLayerCompatibilityImpl(byte[] data) {
        super(1, 16, "LowLayerCompatibility", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<LowLayerCompatibilityImpl> LOW_LAYER_COMPATIBILITY_XML = new XMLFormat<LowLayerCompatibilityImpl>(LowLayerCompatibilityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, LowLayerCompatibilityImpl lowLayerCompatibility) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                lowLayerCompatibility.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(LowLayerCompatibilityImpl lowLayerCompatibility, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (lowLayerCompatibility.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(lowLayerCompatibility.data));
            }
        }
    };

}
