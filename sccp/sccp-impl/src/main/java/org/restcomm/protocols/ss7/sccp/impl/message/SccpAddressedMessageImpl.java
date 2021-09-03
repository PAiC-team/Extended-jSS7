package org.restcomm.protocols.ss7.sccp.impl.message;

import org.restcomm.protocols.ss7.sccp.impl.parameter.HopCounterImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpAddressedMessage;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

public abstract class SccpAddressedMessageImpl extends SccpMessageImpl implements SccpAddressedMessage {

    protected SccpAddress calledParty;
    protected SccpAddress callingParty;
    protected HopCounterImpl hopCounter;

    protected SccpAddressedMessageImpl(int maxDataLen, int type, int outgoingSls, int localSsn,
            SccpAddress calledParty, SccpAddress callingParty, HopCounter hopCounter) {
        super(maxDataLen, type, outgoingSls, localSsn);

        this.calledParty = calledParty;
        this.callingParty = callingParty;
        this.hopCounter = (HopCounterImpl) hopCounter;
    }

    protected SccpAddressedMessageImpl(int maxDataLen, int type, int incomingOpc, int incomingDpc, int incomingSls, int networkId) {
        super(maxDataLen,type, incomingOpc, incomingDpc, incomingSls, networkId);
    }

    public SccpAddress getCalledPartyAddress() {
        return this.calledParty;
    }

    public void setCalledPartyAddress(SccpAddress calledParty) {
        this.calledParty = calledParty;
    }

    public SccpAddress getCallingPartyAddress() {
        return this.callingParty;
    }

    public void setCallingPartyAddress(SccpAddress callingParty) {
        this.callingParty = callingParty;
    }

    public HopCounter getHopCounter() {
        return this.hopCounter;
    }

    public void setHopCounter(HopCounter hopCounter) {
        this.hopCounter = (HopCounterImpl) hopCounter;
    }

    public boolean reduceHopCounter() {
        if (this.hopCounter != null) {
            int val = this.hopCounter.getValue();
            if (--val <= 0) {
                val = 0;
            }
            this.hopCounter.setValue(val);
            if (val == 0)
                return false;
        }
        return true;
    }
}
