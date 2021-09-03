package org.restcomm.protocols.ss7.isup;

import java.util.EventObject;

import org.restcomm.protocols.ss7.isup.message.ISUPMessage;

/**
 * Class delivered to listeners. To be extended if requried.
 *
 * @author baranowb
 *
 */
public class ISUPEvent extends EventObject {

    protected final ISUPMessage message;
    private int dpc;

    /**
     * @param message
     * @param circuit
     */
    public ISUPEvent(Object source, ISUPMessage message, int dpc) {
        super(source);
        this.message = message;
        this.dpc = dpc;
    }

    public ISUPMessage getMessage() {
        return message;
    }

    public int getDpc() {
        return dpc;
    }

    @Override
    public String toString() {
        return "ISUPEvent [dpc=" + dpc + ", message=" + message.toString() + "]";
    }

}
