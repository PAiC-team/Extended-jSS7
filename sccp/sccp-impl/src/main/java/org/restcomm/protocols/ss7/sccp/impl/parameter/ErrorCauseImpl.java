package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ErrorCause;
import org.restcomm.protocols.ss7.sccp.parameter.ErrorCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ErrorCauseImpl extends AbstractParameter  implements ErrorCause {

    private ErrorCauseValue value;
    private int digValue;

    public ErrorCauseImpl() {
        value = ErrorCauseValue.UNQUALIFIED;
        this.digValue = value.getValue();
    }

    public ErrorCauseImpl(ErrorCauseValue value) {
        this.value = value;
        if (value != null)
            this.digValue = value.getValue();
    }

    public ErrorCauseImpl(int digValue) {
        this.digValue = digValue;
        value = ErrorCauseValue.getInstance(digValue);
    }

    public ErrorCauseValue getValue() {
        return value;
    }

    public int getDigitalValue() {
        return digValue;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.digValue = in.read();
            this.value = ErrorCauseValue.getInstance(digValue);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.digValue);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        if (b.length < 1) {
            throw new ParseException();
        }
        this.digValue = b[0];
        this.value = ErrorCauseValue.getInstance(digValue);
    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte)this.digValue };
    }

    public String toString() {
        if (this.value != null)
            return new StringBuffer().append("ErrorCause [").append("value=").append(value).append("]").toString();
        else {
            return new StringBuffer().append("ErrorCause [").append("digValue=").append(digValue).append("]").toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorCauseImpl that = (ErrorCauseImpl) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return digValue;
    }
}
