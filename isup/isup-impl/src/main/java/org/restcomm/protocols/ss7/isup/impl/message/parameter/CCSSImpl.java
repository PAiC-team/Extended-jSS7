package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.CCSS;

/**
 * Start time:12:56:36 2009-07-17<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class CCSSImpl extends AbstractISUPParameter implements CCSS {

    private static final int _TURN_ON = 1;
    private static final int _TURN_OFF = 0;

    private boolean ccssCall = false;

    public CCSSImpl(boolean ccssCall) {
        super();
        this.ccssCall = ccssCall;
    }

    public CCSSImpl(byte[] b) throws ParameterException {
        super();
        decode(b);
    }

    public CCSSImpl() {
        super();

    }

    public int decode(byte[] b) throws ParameterException {
        if (b == null || b.length != 1) {
            throw new ParameterException("byte[] must not be null and length must be 1");
        }

        this.ccssCall = (b[0] & 0x01) == _TURN_ON;

        return 1;
    }

    public byte[] encode() throws ParameterException {
        return new byte[] { (byte) (this.ccssCall ? _TURN_ON : _TURN_OFF) };
    }

    public boolean isCcssCall() {
        return ccssCall;
    }

    public void setCcssCall(boolean ccssCall) {
        this.ccssCall = ccssCall;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }
}
