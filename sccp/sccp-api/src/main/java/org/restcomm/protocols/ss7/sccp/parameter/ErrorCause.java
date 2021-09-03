package org.restcomm.protocols.ss7.sccp.parameter;

public interface ErrorCause {
    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    ErrorCauseValue getValue();

    /**
     * Gets the digital value of this parameter.
     *
     * @return the digital value of this parameter.
     */
    int getDigitalValue();
}
