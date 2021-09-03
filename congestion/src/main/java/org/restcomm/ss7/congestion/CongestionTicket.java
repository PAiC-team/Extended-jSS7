package org.restcomm.ss7.congestion;

/**
 * The Congestion Ticket interface contains info for the source of congestion and the congestion level
 *
 * @author sergey vetyutnev
 *
 */
public interface CongestionTicket {

    /**
     * @return the name of the source of the congestion
     */
    String getSource();

    /**
     * @return the level of the congestion. 0 means no congestion, 3 means max congestion level. Level 1 is warning level
     *         without of message processing affect, Level 2 will prevent from new dialog creating, Level 3 will prevent from
     *         any message processing.
     */
    int getLevel();

    /**
     * @return the attribute for the ticket (like networkID, dpc values, etc)
     */
    Object getAttribute(String key);

}
