
package org.restcomm.protocols.ss7.m3ua.impl;

import org.restcomm.protocols.ss7.m3ua.State;

/**
 *
 * @author amit bhayani
 *
 */
public enum AspState implements State {
    DOWN(STATE_DOWN), DOWN_SENT("DOWN_SENT"), UP_SENT("UP_SENT"), INACTIVE_SENT("INACTIVE_SENT"), INACTIVE(STATE_INACTIVE),
    ACTIVE_SENT("ACTIVE_SENT"), ACTIVE(STATE_ACTIVE);

    private String name;

    private AspState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static AspState getState(String name) {
        if (name.compareTo(DOWN.getName()) == 0) {
            return DOWN;
        } else if (name.equals(INACTIVE.getName())) {
            return INACTIVE;
        } else if (name.equals(ACTIVE.getName())) {
            return ACTIVE;
        } else if (name.equals(UP_SENT.getName())) {
            return UP_SENT;
        } else if (name.equals(DOWN_SENT.getName())) {
            return DOWN_SENT;
        } else if (name.equals(INACTIVE_SENT.getName())) {
            return INACTIVE_SENT;
        } else if (name.equals(ACTIVE_SENT.getName())) {
            return ACTIVE_SENT;
        }

        return null;
    }
}
