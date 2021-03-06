package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.NatureOfConnectionIndicators;

/**
 * Start time:09:12:26 2009-04-02<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class NatureOfConnectionIndicatorsImpl extends AbstractISUPParameter implements NatureOfConnectionIndicators {

    private static final int _TURN_ON = 1;
    private static final int _TURN_OFF = 0;

    private int satelliteIndicator = 0;
    private int continuityCheckIndicator = 0;
    private boolean echoControlDeviceIndicator = false;

    public NatureOfConnectionIndicatorsImpl(byte[] b) throws ParameterException {
        super();
        decode(b);
    }

    public NatureOfConnectionIndicatorsImpl() {
        super();

    }

    public NatureOfConnectionIndicatorsImpl(byte satelliteIndicator, byte continuityCheckIndicator,
            boolean echoControlDeviceIndicator) {
        super();
        this.satelliteIndicator = satelliteIndicator;
        this.continuityCheckIndicator = continuityCheckIndicator;
        this.echoControlDeviceIndicator = echoControlDeviceIndicator;
    }

    public int decode(byte[] b) throws ParameterException {
        if (b == null || b.length != 1) {
            throw new ParameterException("byte[] must not be null and must have length of 1");
        }
        this.satelliteIndicator = (byte) (b[0] & 0x03);
        this.continuityCheckIndicator = (byte) ((b[0] >> 2) & 0x03);
        this.echoControlDeviceIndicator = ((b[0] >> 4) == _TURN_ON);

        return 1;
    }

    public byte[] encode() throws ParameterException {

        int b0 = 0;
        b0 = this.satelliteIndicator & 0x03;
        b0 |= (this.continuityCheckIndicator & 0x03) << 2;
        b0 |= (this.echoControlDeviceIndicator ? _TURN_ON : _TURN_OFF) << 4;
        return new byte[] { (byte) b0 };
    }

    public int encode(ByteArrayOutputStream bos) throws ParameterException {
        byte[] b = this.encode();
        try {
            bos.write(b);
        } catch (IOException e) {
            throw new ParameterException(e);
        }
        return b.length;

    }

    public int getSatelliteIndicator() {
        return satelliteIndicator;
    }

    public void setSatelliteIndicator(int satelliteIndicator) {
        this.satelliteIndicator = satelliteIndicator & 0x03;
    }

    public int getContinuityCheckIndicator() {
        return continuityCheckIndicator;
    }

    public void setContinuityCheckIndicator(int continuityCheckIndicator) {
        this.continuityCheckIndicator = continuityCheckIndicator & 0x03;
    }

    public boolean isEchoControlDeviceIndicator() {
        return echoControlDeviceIndicator;
    }

    public void setEchoControlDeviceIndicator(boolean echoControlDeviceIndicator) {
        this.echoControlDeviceIndicator = echoControlDeviceIndicator;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("NatureOfConnectionIndicators [");

        sb.append("satelliteIndicator=");
        sb.append(satelliteIndicator);
        sb.append(", ");
        sb.append("continuityCheckIndicator=");
        sb.append(continuityCheckIndicator);
        sb.append(", ");
        sb.append("echoControlDeviceIndicator=");
        sb.append(echoControlDeviceIndicator);

        sb.append("]");
        return sb.toString();
    }
}
