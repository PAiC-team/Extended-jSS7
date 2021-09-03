package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.ContinuityIndicators;

/**
 * Start time:18:28:42 2009-03-30<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class ContinuityIndicatorsImpl extends AbstractISUPParameter implements ContinuityIndicators {

    private static final int _TURN_ON = 1;
    private static final int _TURN_OFF = 0;

    private boolean continuityCheck = false;

    public ContinuityIndicatorsImpl(byte[] b) throws ParameterException {
        super();
        decode(b);
    }

    public ContinuityIndicatorsImpl() {
        super();

    }

    public ContinuityIndicatorsImpl(boolean continuityCheck) {
        super();
        this.continuityCheck = continuityCheck;
    }

    public int decode(byte[] b) throws ParameterException {
        if (b == null || b.length != 1) {
            throw new ParameterException("byte[] must not be null or have different size than 1");
        }
        this.continuityCheck = (b[0] & 0x01) == _TURN_ON;
        return 1;
    }

    public byte[] encode() throws ParameterException {
        return new byte[] { (byte) (this.continuityCheck ? _TURN_ON : _TURN_OFF) };
    }

    public boolean isContinuityCheck() {
        return continuityCheck;
    }

    public void setContinuityCheck(boolean continuityCheck) {
        this.continuityCheck = continuityCheck;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }
}
