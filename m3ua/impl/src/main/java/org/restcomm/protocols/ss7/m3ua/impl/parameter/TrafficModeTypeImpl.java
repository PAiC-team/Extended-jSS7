package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

/**
 *
 * @author amit bhayani
 *
 */
public class TrafficModeTypeImpl extends ParameterImpl implements TrafficModeType, XMLSerializable {

    private static final String MODE = "mode";

    private int mode = 0;
    private byte[] value;

    public TrafficModeTypeImpl() {
        this.tag = Parameter.Traffic_Mode_Type;
    }

    protected TrafficModeTypeImpl(byte[] data) {
        this.tag = Parameter.Traffic_Mode_Type;
        this.value = data;
        this.mode = 0;
        this.mode |= data[0] & 0xFF;
        this.mode <<= 8;
        this.mode |= data[1] & 0xFF;
        this.mode <<= 8;
        this.mode |= data[2] & 0xFF;
        this.mode <<= 8;
        this.mode |= data[3] & 0xFF;
    }

    public TrafficModeTypeImpl(int traffmode) {
        this.tag = Parameter.Traffic_Mode_Type;
        mode = traffmode;
        encode();
    }

    private void encode() {
        // create byte array taking into account data, point codes and indicators;
        this.value = new byte[4];
        // encode routing context
        value[0] = (byte) (mode >> 24);
        value[1] = (byte) (mode >> 16);
        value[2] = (byte) (mode >> 8);
        value[3] = (byte) (mode);
    }

    public int getMode() {
        return mode;
    }

    @Override
    protected byte[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("TrafficModeType mode=%d value=%s", mode, this.getStringRepresentation());
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TrafficModeTypeImpl> RC_XML = new XMLFormat<TrafficModeTypeImpl>(TrafficModeTypeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TrafficModeTypeImpl trafficMode) throws XMLStreamException {
            trafficMode.mode = xml.getAttribute(MODE).toInt();
            trafficMode.encode();
        }

        @Override
        public void write(TrafficModeTypeImpl trafficMode, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            xml.setAttribute(MODE, trafficMode.mode);
        }
    };

    public String getStringRepresentation() {
        switch (mode) {
            case 1:
                return "Override";
            case 2:
                return "Loadshare";
            case 3:
                return "Broadcast";
            default:
                return "";
        }
    }

}
