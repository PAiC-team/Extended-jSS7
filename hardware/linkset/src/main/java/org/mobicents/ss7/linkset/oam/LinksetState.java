package org.mobicents.ss7.linkset.oam;

/**
 *
 * @author amit bhayani
 *
 */
public interface LinksetState {
    /**
     * Indicates the linkset does not have any “available” links and cannot transport traffic
     */
    int UNAVAILABLE = 1;

    /**
     * Indicates the linkset has been shutdown in the configuration
     */
    int SHUTDOWN = 2;

    /**
     * Indicates the linkset has at least one available link and can carry traffic
     */
    int AVAILABLE = 3;
}
