package org.restcomm.ss7.management.transceiver;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Represents the Shell Command protocol data unit
 *
 * @author amit bhayani
 *
 */
public class Message {
    protected byte[] data = null;

    protected Message() {

    }

    protected int getLength(){
        if(data!=null){
            return data.length;
        }
        return 0;
    }

    protected byte[] getData(){
        return this.data;
    }

    protected Message(byte[] data) {
        this.data = data;
    }

    /**
     * Decodes the received byte[]
     *
     * @param data
     */
    protected void decode(byte[] data) {
        this.data = data;
    }

    /**
     * Encodes this message. The protocol is first 4 bytes are length of this command followed by the byte stream of command
     *
     * @param txBuffer
     */
    protected void encode(ByteBuffer txBuffer) {

        txBuffer.position(4); // Length int
        txBuffer.put(data);

        int length = txBuffer.position();

        txBuffer.rewind();

        txBuffer.putInt(length);

        txBuffer.position(length);
    }

    @Override
    public String toString() {
        return new String(data);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(data);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (!Arrays.equals(data, other.data))
            return false;
        return true;
    }

}
