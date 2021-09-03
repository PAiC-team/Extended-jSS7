package org.restcomm.protocols.ss7.sccp.parameter;

public interface Credit {

    int PARAMETER_CODE = 0x9;

    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    int getValue();

}
