package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;

/**
 * @author Oleg Kulikov
 */
public class ProtocolClassImpl extends AbstractParameter implements ProtocolClass {

    private int pClass;
    private int msgHandling;

    /** Creates a new instance of UnitDataMandatoryFixedPart */
    public ProtocolClassImpl() {
    }

    public ProtocolClassImpl(int pClass) {
        if (pClass != 2 && pClass != 3) {
            throw new IllegalStateException("This constructor is only for protocol class 2 or 3");
        }
        this.pClass = pClass;
    }

    public ProtocolClassImpl(int pClass, boolean returnMessageOnError) {
        this.pClass = pClass;
        if (pClass == 0 || pClass == 1)
            this.msgHandling = (returnMessageOnError ? HANDLING_RET_ERR : 0);
        else
            this.msgHandling = 0;
    }

    public int getProtocolClass() {
        return this.pClass;
    }

    public boolean getReturnMessageOnError() {
        if (pClass != 0 && pClass != 1) {
            throw new IllegalArgumentException("Protocol class 0 or 1 is required");
        }
        return (this.msgHandling & HANDLING_RET_ERR) != 0 ? true : false;
    }

    public void clearReturnMessageOnError() {
        if (pClass != 0 && pClass != 1) {
            throw new IllegalArgumentException("Protocol class 0 or 1 is required");
        }
        int mask = HANDLING_RET_ERR ^ (-1);
        this.msgHandling = this.msgHandling & mask;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (in.read() != 1) {
                throw new ParseException();
            }

            int b = in.read() & 0xff;

            pClass = b & 0x0f;
            msgHandling = (b & 0xf0) >> 4;
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void encode(OutputStream out, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            byte b = (byte) (pClass | (msgHandling << 4));
            out.write(1);
            out.write(b);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void decode(byte[] bb, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        int b = bb[0] & 0xff;

        pClass = b & 0x0f;
        msgHandling = (b & 0xf0) >> 4;
    }

    @Override
    public byte[] encode(final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return new byte[] { (byte) (pClass | (msgHandling << 4)) };
    }

    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + msgHandling;
        result = prime * result + pClass;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (getClass() != obj.getClass())
            return false;
        ProtocolClassImpl other = (ProtocolClassImpl) obj;
        if (msgHandling != other.msgHandling)
            return false;
        if (pClass != other.pClass)
            return false;
        return true;
    }

    public String toString() {
        return "ProtocolClass [msgHandling=" + msgHandling + ", pClass=" + pClass + "]";
    }
}