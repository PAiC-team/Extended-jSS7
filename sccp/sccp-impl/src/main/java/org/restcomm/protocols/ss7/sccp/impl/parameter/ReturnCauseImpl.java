
package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;

/**
 *
 * @author baranowb
 * @author sergey vetyutnev
 */
public class ReturnCauseImpl extends AbstractParameter implements ReturnCause {

    private ReturnCauseValue value;
    private int digValue;

    public ReturnCauseImpl() {
        value = ReturnCauseValue.UNQALIFIED;
    }

    public ReturnCauseImpl(ReturnCauseValue returnCauseValue) {
        this.value = returnCauseValue;
        if (value != null)
            this.digValue = value.getValue();
    }

    public ReturnCauseImpl(int digValue) {
        this.digValue = digValue;
        value = ReturnCauseValue.getInstance(digValue);
    }

    public ReturnCauseValue getValue() {
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

            this.digValue = in.read() & 0xFF;
            this.value = ReturnCauseValue.getInstance(this.digValue);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void encode(OutputStream out, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            out.write(1);
            out.write(this.digValue);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void decode(byte[] bb, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.digValue = bb[0] & 0xff;

        this.value = ReturnCauseValue.getInstance(this.digValue);
    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte) this.digValue };
    }

    public String toString() {
        if (this.value != null)
            return new StringBuffer().append("ReturnCause [").append("value=").append(value).append("]").toString();
        else {
            return new StringBuffer().append("ReturnCause [").append("digValue=").append(digValue).append("]").toString();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + digValue;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReturnCauseImpl other = (ReturnCauseImpl) obj;
        if (digValue != other.digValue)
            return false;
        return true;
    }
}
