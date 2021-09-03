package org.restcomm.protocols.ss7.m3ua.message;

/**
 * Defines the list of valid message classes.
 *
 * @author kulikov
 */
public interface MessageClass {

    int MANAGEMENT = 0;
    int TRANSFER_MESSAGES = 1;
    int SIGNALING_NETWORK_MANAGEMENT = 2;
    int ASP_STATE_MAINTENANCE = 3;
    int ASP_TRAFFIC_MAINTENANCE = 4;
    int ROUTING_KEY_MANAGEMENT = 9;

}
