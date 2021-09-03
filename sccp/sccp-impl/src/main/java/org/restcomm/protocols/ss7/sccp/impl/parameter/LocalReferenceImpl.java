package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LocalReferenceImpl extends AbstractParameter implements LocalReference {

    private int value;

    public LocalReferenceImpl() {
    }

    public LocalReferenceImpl(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.value = 0;
        try {
            if (in.read() != 3) {
                throw new ParseException();
            }
            this.value = in.read() << 16;
            this.value |= in.read() << 8;
            this.value |= in.read();
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(3);
            os.write(this.value >> 16 & 0xFF);
            os.write(this.value >> 8 & 0xFF);
            os.write(this.value      & 0xFF);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        if (b.length < 3) {
            throw new ParseException();
        }
        this.value =  (int)b[0] << 16 & 0xFFFFFF;
        this.value |= (int)b[1] << 8 & 0xFFFF;
        this.value |= (int)b[2] & 0xFF;

    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] {
                (byte) (this.value >> 16 & 0xFF),
                (byte) (this.value >> 8 & 0xFF),
                (byte) (this.value      & 0xFF)
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalReferenceImpl that = (LocalReferenceImpl) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("LocalReference [").append("value=").append(value).append("]").toString();
    }
}
