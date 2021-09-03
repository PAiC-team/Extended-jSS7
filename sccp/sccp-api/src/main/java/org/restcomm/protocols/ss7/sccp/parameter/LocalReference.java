package org.restcomm.protocols.ss7.sccp.parameter;

public interface LocalReference {

    int NUMBER_FUTURE_USE = 0xFFFFFF;

    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    int getValue();
}
