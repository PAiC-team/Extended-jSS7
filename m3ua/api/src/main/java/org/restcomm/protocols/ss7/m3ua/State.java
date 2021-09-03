
package org.restcomm.protocols.ss7.m3ua;

/**
 *
 * @author amit bhayani
 *
 */
public interface State {

    String STATE_DOWN = "DOWN";
    String STATE_INACTIVE = "INACTIVE";
    String STATE_ACTIVE = "ACTIVE";
    String STATE_PENDING = "PENDING";

    String getName();
}
