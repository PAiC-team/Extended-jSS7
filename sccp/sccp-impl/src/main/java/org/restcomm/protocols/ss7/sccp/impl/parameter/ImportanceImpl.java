package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 * @author baranowb
 *
 */
public class ImportanceImpl extends AbstractParameter implements Importance {

    // default is lowest priority :)
    private byte importance = 0;

    public ImportanceImpl() {
        // TODO Auto-generated constructor stub
    }

    public ImportanceImpl(byte importance) {
        super();
        this.importance = (byte) (importance & 0x07);
    }

    public int getValue() {
        return importance;
    }

    @Override
    public void decode(byte[] buffer, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.importance = (byte) (buffer[0] & 0x07);

    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        // TODO Auto-generated method stub
        return new byte[] { (byte) (importance & 0x07) };
    }

    @Override
    public void decode(InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }

            this.importance = (byte) (in.read() & 0x07);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void encode(OutputStream os, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            os.write(1);
            os.write(this.importance & 0x07);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + importance;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImportanceImpl other = (ImportanceImpl) obj;
        if (importance != other.importance)
            return false;
        return true;
    }

}
