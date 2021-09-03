
package org.restcomm.protocols.ss7.m3ua.impl;

import org.restcomm.protocols.ss7.m3ua.State;

/**
 *
 * @author amit bhayani
 *
 */
public enum AsState implements State {
    DOWN("DOWN"), INACTIVE("INACTIVE"), ACTIVE("ACTIVE"), PENDING("PENDING");

    private String name;

    private AsState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static AsState getState(String name) {
        if (name.equals(DOWN.getName())) {
            return DOWN;
        } else if (name.equals(INACTIVE.getName())) {
            return INACTIVE;
        } else if (name.equals(ACTIVE.getName())) {
            return ACTIVE;
        } else if (name.equals(PENDING.getName())) {
            return PENDING;
        }

        return null;
    }

}
