package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ReceiveSequenceNumber;
import org.restcomm.protocols.ss7.sccp.parameter.SequenceNumber;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReceiveSequenceNumberImpl extends AbstractParameter implements ReceiveSequenceNumber {

    private SequenceNumber value = new SequenceNumberImpl(0);

    public ReceiveSequenceNumberImpl() {
    }

    public ReceiveSequenceNumberImpl(SequenceNumber value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value.getValue();
    }

    public SequenceNumber getNumber() {
        return value;
    }

    public void setNumber(SequenceNumber value) {
        this.value = value;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.value = new SequenceNumberImpl(0);
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.value = new SequenceNumberImpl((byte)(in.read() >> 1 & 0x7F));
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.value.getValue() << 1 & 0xFE);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        if (b.length < 1) {
            throw new ParseException();
        }
        this.value = new SequenceNumberImpl((byte)(b[0] >> 1 & 0x7F));

    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] {
                (byte) (this.value.getValue() << 1 & 0xFE)
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiveSequenceNumberImpl that = (ReceiveSequenceNumberImpl) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value.getValue();
    }

    @Override
    public String toString() {
        return new StringBuffer().append("ReceiveSequenceNumber [").append("value=").append(value.getValue()).append("]").toString();
    }
}
