package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 * @author kulikov
 */
public class HopCounterImpl extends AbstractParameter implements HopCounter {

    private int value;

    public HopCounterImpl(int value) {
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
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.value = in.read() & 0x0F; // ?
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.value & 0x0F);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.value = b[0] & 0x0F; // ?

    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte) (this.value & 0x0F) };
    }
}
