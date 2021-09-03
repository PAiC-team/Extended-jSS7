package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 * @author amit bhayani
 * @author kulikov
 */
public abstract class ParameterImpl implements Parameter {

    protected volatile short tag;
    protected volatile short length;

    public short getTag() {
        return tag;
    }

    protected abstract byte[] getValue();

    // public void encode(OutputStream out) throws IOException {
    // // obtain encoded value
    // byte[] value = getValue();
    //
    // // encode tag
    // out.write((byte) (tag >> 8));
    // out.write((byte) (tag));
    //
    // // encode length including value, tag and length field itself
    // length = (short) (value.length + 4);
    //
    // out.write((byte) (length >> 8));
    // out.write((byte) (length));
    //
    // // encode value
    // out.write(value);
    // }

    public void write(ByteBuf byteBuf) {
        // obtain encoded value
        byte[] value = getValue();

        // encode tag
        byteBuf.writeByte((byte) (tag >> 8));
        byteBuf.writeByte((byte) (tag));

        // encode length including value, tag and length field itself
        length = (short) (value.length + 4);

        byteBuf.writeByte((byte) (length >> 8));
        byteBuf.writeByte((byte) (length));

        // encode value
        byteBuf.writeBytes(value);

        /*
         * The total length of a parameter (including Tag, Parameter Length, and Value fields) MUST be a multiple of 4 octets.
         * If the length of the parameter is not a multiple of 4 octets, the sender pads the Parameter at the end (i.e., after
         * the Parameter Value field) with all zero octets. The length of the padding is NOT included in the parameter length
         * field. A sender MUST NOT pad with more than 3 octets. The receiver MUST ignore the padding octets.
         */
        int remainder = (4 - length % 4);
        if (remainder < 4) {
            while (remainder > 0) {
                byteBuf.writeByte((byte) 0x00);
                remainder--;
            }
        }
    }

}
