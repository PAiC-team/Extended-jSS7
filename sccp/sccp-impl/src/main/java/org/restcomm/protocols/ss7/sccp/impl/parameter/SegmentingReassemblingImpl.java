package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SegmentingReassembling;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SegmentingReassemblingImpl extends AbstractParameter implements SegmentingReassembling {

    private byte value;

    public SegmentingReassemblingImpl() {
    }

    public SegmentingReassemblingImpl(boolean value) {
        this.value = (value) ? (byte)1 : 0;
    }

    public boolean isMoreData() {
        return this.value == 1;
    }

    public void setValue(boolean value) {
        this.value = (value) ? (byte)1 : 0;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }
            this.value = (byte)(in.read() & 0x01);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void encode(final OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.value & 0x01);
        } catch (IOException ioe) {
            throw new ParseException(ioe);
        }
    }

    @Override
    public void decode(final byte[] b, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        if (b.length < 1) {
            throw new ParseException();
        }
        this.value = (byte)(b[0] & 0x01);

    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte) (this.value & 0x01) };
    }
}
