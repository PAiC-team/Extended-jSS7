package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CreditImpl extends AbstractParameter implements Credit {
    private byte value;

    public CreditImpl() {
    }

    public CreditImpl(int value) {
        this.value = (byte) value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = (byte) value;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.value = (byte)in.read();
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.value);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        if (b.length < 1) {
            throw new ParseException();
        }
        this.value = b[0];
    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { this.value };
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditImpl that = (CreditImpl) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("Credit [").append("value=").append(value).append("]").toString();
    }
}
