package org.restcomm.protocols.ss7.sccp.parameter;

public interface ReleaseCause {
    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    ReleaseCauseValue getValue();

    /**
     * Gets the digital value of this parameter.
     *
     * @return the digital value of this parameter.
     */
    int getDigitalValue();
}
