package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.m3ua.parameter.DestinationPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class DestinationPointCodeImpl extends ParameterImpl implements DestinationPointCode, XMLSerializable {

    private static final String DPC = "dpc";
    private static final String MASK = "mask";

    private int destPC = 0;
    private short mask = 0;
    private byte[] value;

    public DestinationPointCodeImpl() {
        this.tag = Parameter.Destination_Point_Code;
    }

    protected DestinationPointCodeImpl(byte[] value) {
        this.tag = Parameter.Destination_Point_Code;
        this.value = value;
        this.mask = value[0];

        destPC = 0;
        destPC |= value[1] & 0xFF;
        destPC <<= 8;
        destPC |= value[2] & 0xFF;
        destPC <<= 8;
        destPC |= value[3] & 0xFF;
    }

    protected DestinationPointCodeImpl(int pc, short mask) {
        this.tag = Parameter.Destination_Point_Code;
        this.destPC = pc;
        this.mask = mask;
        encode();
    }

    private void encode() {
        // create byte array taking into account data, point codes and
        // indicators;
        this.value = new byte[4];
        // encode point code with mask
        value[0] = (byte) this.mask;// Mask

        value[1] = (byte) (destPC >> 16);
        value[2] = (byte) (destPC >> 8);
        value[3] = (byte) (destPC);
    }

    public int getPointCode() {
        return destPC;
    }

    @Override
    protected byte[] getValue() {
        return value;
    }

    public short getMask() {
        return this.mask;
    }

    @Override
    public String toString() {
        return String.format("DestinationPointCode dpc=%d mask=%d", destPC, mask);
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DestinationPointCodeImpl> RC_XML = new XMLFormat<DestinationPointCodeImpl>(
            DestinationPointCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, DestinationPointCodeImpl dpc) throws XMLStreamException {
            dpc.destPC = xml.getAttribute(DPC).toInt();
            dpc.mask = (short) xml.getAttribute(MASK).toInt();
            dpc.encode();
        }

        @Override
        public void write(DestinationPointCodeImpl dpc, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(DPC, dpc.destPC);
            xml.setAttribute(MASK, dpc.mask);
        }
    };

}
