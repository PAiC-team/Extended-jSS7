
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ByteArrayContainer {

    private static final String VALUE = "value";

    private static final String DEFAULT_VALUE = null;

    private byte[] data;

    public ByteArrayContainer() {
    }

    public ByteArrayContainer(byte[] val) {
        this.data = val;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] val) {
        data = val;
    }

    /**
     * XML Serialization/Deserialization
     */
    public static final XMLFormat<ByteArrayContainer> ISUP_BYTE_ARRAY_XML = new XMLFormat<ByteArrayContainer>(
            ByteArrayContainer.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ByteArrayContainer arr) throws XMLStreamException {

            String s = xml.getAttribute(VALUE, DEFAULT_VALUE);
            arr.setData(DatatypeConverter.parseHexBinary(s));
        }

        @Override
        public void write(ByteArrayContainer arr, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            byte[] data = arr.getData();
            String s;
            if (data != null) {
                s = DatatypeConverter.printHexBinary(data);
            } else {
                s = "";
            }

            xml.setAttribute(VALUE, s);
        }
    };

}
