package org.restcomm.protocols.ss7.m3ua.impl.parameter;

/**
 *
 * @author kulikov
 */
public class UnknownParameterImpl extends ParameterImpl {

    private byte[] value;

    protected UnknownParameterImpl(int tag, int length, byte[] value) {
        this.tag = (short) tag;
        this.length = (short) length;
        this.value = value;
    }

    @Override
    protected byte[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Unknown parameter: tag=%d, length=%d", tag, length);
    }

}
