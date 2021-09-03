package org.restcomm.ss7.congestion;

/**
 * The user who is interested in monitoring the underlying source should implement this interface.
 *
 * @author amit bhayani
 *
 */
public interface CongestionMonitor {

    /**
     * The user should call this method periodically to monitor underlying resource
     */
    void monitor();

    /**
     * Add {@link CongestionListener}
     *
     * @param listener
     */
    void addCongestionListener(CongestionListener listener);

    /**
     * Remove {@link CongestionListener}
     *
     * @param listener
     */
    void removeCongestionListener(CongestionListener listener);

    /**
     * Get the name of underlying source
     *
     * @return
     */
    String getSource();

    /**
     * Return the list of the actual congestion tickets or null if no
     *
     * @return
     */
    CongestionTicket[] getCongestionTicketsList();

}
