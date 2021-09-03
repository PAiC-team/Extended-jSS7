package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 * @author amit bhayani
 * @author kulikov
 */
public class NetworkAppearanceImpl extends ParameterImpl implements NetworkAppearance, XMLSerializable {

    private static final String VALUE = "value";

    private static final long UNSIGNED_INT_MAX_VALUE = 0xFFFFFFFF;
    private long value;

    public NetworkAppearanceImpl() {
        this.tag = Parameter.Network_Appearance;
    }

    protected NetworkAppearanceImpl(long value) {
        this.value = value;
        this.tag = Parameter.Network_Appearance;
    }

    protected NetworkAppearanceImpl(byte[] data) {
        this.value = 0;
        this.value |= data[0] & 0xFF;
        this.value <<= 8;
        this.value |= data[1] & 0xFF;
        this.value <<= 8;
        this.value |= data[2] & 0xFF;
        this.value <<= 8;
        this.value |= data[3] & 0xFF;
        this.tag = Parameter.Network_Appearance;
    }

    public long getNetApp() {
        return value;
    }

    @Override
    protected byte[] getValue() {
        byte[] data = new byte[4];
        data[0] = (byte) (value >>> 24);
        data[1] = (byte) (value >>> 16);
        data[2] = (byte) (value >>> 8);
        data[3] = (byte) (value);

        return data;
    }

    @Override
    public String toString() {
        return String.format("NetworkAppearance value=%d", value);
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<NetworkAppearanceImpl> RC_XML = new XMLFormat<NetworkAppearanceImpl>(
            NetworkAppearanceImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, NetworkAppearanceImpl localRkId) throws XMLStreamException {
            localRkId.value = xml.getAttribute(VALUE).toLong();
        }

        @Override
        public void write(NetworkAppearanceImpl localRkId, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            xml.setAttribute(VALUE, localRkId.value);
        }
    };

}
