package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.message.parameter.PivotReason;

/**
 * @author baranowb
 *
 */
public class PivotReasonImpl implements PivotReason {

    private byte pivotReason;
    //we need to know if that one was set.
    private Byte pivotPossibleAtPerformingExchange;
    public PivotReasonImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public byte getPivotReason() {
        return this.pivotReason;
    }

    @Override
    public void setPivotReason(byte b) {
        this.pivotReason = (byte) (b & 0x7F);
    }

    @Override
    public byte getPivotPossibleAtPerformingExchange() {
        if(this.pivotPossibleAtPerformingExchange == null)
            return 0;
        return this.pivotPossibleAtPerformingExchange;
    }

    @Override
    public void setPivotPossibleAtPerformingExchange(byte b) {
        this.pivotPossibleAtPerformingExchange = (byte) (b & 0x07);
    }

    public byte[] encode(){
        byte[] data = null;
        if(this.pivotPossibleAtPerformingExchange == null){
            data = new byte[1];
            data[0] = (byte) (0x80 | this.pivotReason);
        } else {
            data = new byte[2];
            data[0] = this.pivotReason;
            data[1] = this.pivotPossibleAtPerformingExchange;
        }
        return data;
    }

    /**
     * Remove pivotPossbileAtPerformingExchange.
     */
    void trim(){
        this.pivotPossibleAtPerformingExchange = null;
    }
}
