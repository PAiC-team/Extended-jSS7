package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationType;
import org.restcomm.protocols.ss7.isup.message.parameter.ReturnToInvokingExchangeDuration;

/**
 * @author baranowb
 *
 */
public class ReturnToInvokingExchangeDurationImpl extends AbstractInformationImpl implements ReturnToInvokingExchangeDuration {

    private int duration;

    public ReturnToInvokingExchangeDurationImpl() {
        super(InformationType.ReturnToInvokingExchangeDuration);
        super.tag = 0x01;
    }

    @Override
    public void setDuration(int seconds) {
        this.duration = seconds & 0XFFFF;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    byte[] encode() throws ParameterException {
        byte[] data;
        if(this.duration > 0xFF){
            data = new byte[2];
            data[1] = (byte) ((this.duration >> 8) & 0xFF);
        } else {
            data = new byte[1];
        }

        data[0] = (byte) this.duration;
        return data;
    }

    @Override
    void decode(byte[] b) throws ParameterException {
        if(b.length != 1 && b.length!=2){
            throw new ParameterException("Wrong numbder of bytes: "+b.length);
        }
        this.duration = (b[0] & 0xFF);
        if(b.length == 2){
             this.duration |= ((b[1] & 0xFF) << 8);
        }
    }
}
