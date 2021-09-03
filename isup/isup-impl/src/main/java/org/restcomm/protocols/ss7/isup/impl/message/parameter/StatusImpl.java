package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.message.parameter.Status;

/**
 * @author baranowb
 *
 */
public class StatusImpl implements Status {

    private byte status;
    public StatusImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public byte getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(byte b) {
        this.status = (byte) (b & 0x03);
    }

}
