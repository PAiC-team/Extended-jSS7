package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;

/**
 * Start time:14:49:25 2009-09-18<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class CircuitIdentificationCodeImpl extends AbstractISUPParameter implements CircuitIdentificationCode {

    private int cic;

    /**
     *
     */
    public CircuitIdentificationCodeImpl() {

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode#getCIC()
     */
    public int getCIC() {
        return this.cic;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode#setCIC(long)
     */
    public void setCIC(int cic) {
        this.cic = cic & 0x0FFF; // Q.763 1.2

    }

    public int getCode() {
        // Its not a real parameter.
        throw new UnsupportedOperationException();
    }

    public int decode(byte[] b) throws ParameterException {
        if (b == null || b.length != 2) {
            throw new ParameterException("byte[] must not be null or has size equal to 2.");
        }
        this.cic = (b[0] & 0xFF);
        this.cic |= ((b[1] & 0x0F) << 8);
        return b.length;
    }

    public byte[] encode() throws ParameterException {
        byte[] b = new byte[2];
        b[0] = (byte) this.cic;
        b[1] = (byte) ((this.cic >> 8) & 0x0F);
        return b;
    }

}
