package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.message.parameter.RedirectReason;

/**
 * @author baranowb
 *
 */
public class RedirectReasonImpl implements RedirectReason {

    private byte redirectReason;
    //we need to know if that one was set.
    private Byte redirectPossibleAtPerformingExchange;
    public RedirectReasonImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public byte getRedirectReason() {
        return this.redirectReason;
    }

    @Override
    public void setRedirectReason(byte b) {
        this.redirectReason = (byte) (b & 0x7F);
    }

    @Override
    public byte getRedirectPossibleAtPerformingExchange() {
        if(this.redirectPossibleAtPerformingExchange == null)
            return 0;
        return this.redirectPossibleAtPerformingExchange;
    }

    @Override
    public void setRedirectPossibleAtPerformingExchange(byte b) {
        this.redirectPossibleAtPerformingExchange = (byte) (b & 0x07);
    }

    public byte[] encode(){
        byte[] data = null;
        if(this.redirectPossibleAtPerformingExchange == null){
            data = new byte[1];
            data[0] = (byte) (0x80 | this.redirectReason);
        } else {
            data = new byte[2];
            data[0] = this.redirectReason;
            data[1] = this.redirectPossibleAtPerformingExchange;
        }
        return data;
    }

    /**
     * Remove redirectPossbileAtPerformingExchange.
     */
    void trim(){
        this.redirectPossibleAtPerformingExchange = null;
    }

}
