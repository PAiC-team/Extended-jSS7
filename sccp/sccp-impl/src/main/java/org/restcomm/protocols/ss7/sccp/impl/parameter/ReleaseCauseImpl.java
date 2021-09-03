package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCauseValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReleaseCauseImpl extends AbstractParameter  implements ReleaseCause {

    private ReleaseCauseValue value;
    private int digValue;

    public ReleaseCauseImpl() {
        value = ReleaseCauseValue.UNQUALIFIED;
        this.digValue = value.getValue();
    }

    public ReleaseCauseImpl(ReleaseCauseValue releaseCauseValue) {
        this.value = releaseCauseValue;
        if (value != null)
            this.digValue = value.getValue();
    }

    public ReleaseCauseImpl(int digValue) {
        this.digValue = digValue;
        value = ReleaseCauseValue.getInstance(digValue);
    }

    public ReleaseCauseValue getValue() {
        return this.value;
    }

    public int getDigitalValue() {
        return this.digValue;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.digValue = in.read();
            this.value = ReleaseCauseValue.getInstance(digValue);
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
        this.value = ReleaseCauseValue.getInstance(digValue);
    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte)this.digValue };
    }

    public String toString() {
        if (this.value != null)
            return new StringBuffer().append("ReleaseCause [").append("value=").append(value).append("]").toString();
        else {
            return new StringBuffer().append("ReleaseCause [").append("digValue=").append(digValue).append("]").toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseCauseImpl that = (ReleaseCauseImpl) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return digValue;
    }
}
