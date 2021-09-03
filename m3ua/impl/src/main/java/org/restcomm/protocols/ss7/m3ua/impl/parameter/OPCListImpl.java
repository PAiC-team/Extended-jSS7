package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import java.util.Arrays;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.m3ua.parameter.OPCList;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class OPCListImpl extends ParameterImpl implements OPCList, XMLSerializable {

    private static final String OPC = "opc";
    private static final String MASK = "mask";
    private static final String ARRAY_SIZE = "size";

    private byte[] value;
    private int[] pointCodes;
    private short[] masks;

    public OPCListImpl() {
        this.tag = Parameter.Originating_Point_Code_List;
    }

    protected OPCListImpl(byte[] value) {
        this.tag = Parameter.Originating_Point_Code_List;

        int count = 0;
        int arrSize = 0;
        pointCodes = new int[(value.length / 4)];
        masks = new short[(value.length / 4)];

        while (count < value.length) {
            masks[arrSize] = value[count++];

            pointCodes[arrSize] = 0;
            pointCodes[arrSize] |= value[count++] & 0xFF;
            pointCodes[arrSize] <<= 8;
            pointCodes[arrSize] |= value[count++] & 0xFF;
            pointCodes[arrSize] <<= 8;
            pointCodes[arrSize++] |= value[count++] & 0xFF;
        }
        this.value = value;
    }

    protected OPCListImpl(int[] pointCodes, short[] masks) {
        this.tag = Parameter.Originating_Point_Code_List;
        this.pointCodes = pointCodes;
        this.masks = masks;
        encode();
    }

    private void encode() {
        // create byte array taking into account data, point codes and indicators;

        this.value = new byte[(pointCodes.length * 4)];

        int count = 0;
        int arrSize = 0;
        // encode routing context
        while (count < value.length) {
            value[count++] = (byte) (masks[arrSize]);

            value[count++] = (byte) (pointCodes[arrSize] >>> 16);
            value[count++] = (byte) (pointCodes[arrSize] >>> 8);
            value[count++] = (byte) (pointCodes[arrSize++]);
        }
    }

    @Override
    protected byte[] getValue() {
        return this.value;
    }

    public short[] getMasks() {
        return this.masks;
    }

    public int[] getPointCodes() {
        return this.pointCodes;
    }

    @Override
    public String toString() {
        return String.format("OPCList pointCode=%s mask=%s", Arrays.toString(this.pointCodes), Arrays.toString(this.masks));
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OPCListImpl> RC_XML = new XMLFormat<OPCListImpl>(OPCListImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, OPCListImpl opc) throws XMLStreamException {
            int size = xml.getAttribute(ARRAY_SIZE).toInt();
            opc.pointCodes = new int[size];
            opc.masks = new short[size];

            for (int i = 0; i < opc.pointCodes.length; i++) {
                opc.pointCodes[i] = xml.get(OPC);
            }

            for (int i = 0; i < opc.masks.length; i++) {
                opc.masks[i] = xml.get(MASK);
            }

            opc.encode();

        }

        @Override
        public void write(OPCListImpl opc, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(ARRAY_SIZE, opc.pointCodes.length);
            for (int i : opc.pointCodes) {
                xml.add(i, OPC);
            }

            for (short s : opc.masks) {
                xml.add(s, MASK);
            }
        }
    };
}
