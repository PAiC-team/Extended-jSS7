package org.restcomm.protocols.ss7.mtp;

public interface MtpUser {

    /**
     * Callback method from lower layers MTP3-. This is called once MTP3 determines that link is stable and is able to
     * send/receive messages properly. This method should be called only once. Every linkup event.
     */
    void linkUp();

    /**
     * Callback method from MTP3 layer, informs upper layers that link is not operable.
     */
    void linkDown();

    /**
     * Callback from Layer4+. It expects properly encoded MTP3 message. It forwards data to MTP3
     *
     * @param msgBuff
     */
    void receive(byte[] msgBuff);

    void receive(String msg);

    void setMtp3(Mtp3 mtp);

}
