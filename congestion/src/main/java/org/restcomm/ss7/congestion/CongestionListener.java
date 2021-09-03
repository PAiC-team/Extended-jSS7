package org.restcomm.ss7.congestion;

/**
 * The listener interface for receiving congestion state. The class that is interested in listening for congestion state should
 * implement this interface and register itself to {@link CongestionMonitor}
 *
 * @author amit bhayani
 *
 */
public interface CongestionListener {

    /**
     * As soon as congestion starts or its level increases in the underlying source, it calls this method to notify about it.
     * Notification is only one-time till the congestion abates in which case
     * {@link CongestionListener#onCongestionFinish(String)} is called
     *
     * @param ticket Ticket content
     */
    void onCongestionStart(CongestionTicket ticket);

    /**
     * As soon as congestion abates or its level decreases in the underlying source, it calls this method to notify about it.
     * Notification is only one-time till the congestion starts agaain in which case
     * {@link CongestionListener#onCongestionStart(String)} is called
     *
     * @param ticket Ticket content
     */
    void onCongestionFinish(CongestionTicket ticket);

}
