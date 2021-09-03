package org.restcomm.protocols.ss7.sccp.parameter;

public interface ReceiveSequenceNumber {
    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    int getValue();

    /**
     * Gets the value of this parameter as SequenceNumber.
     *
     * @return the value of this parameter as SequenceNumber.
     */
    SequenceNumber getNumber();
}
