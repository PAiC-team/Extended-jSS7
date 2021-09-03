package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Hop counter parameter
 *
 * @author baranowb
 * @author kulikov
 */
public interface HopCounter extends Parameter {

    int PARAMETER_CODE = 0x11;

    int COUNT_NOT_SET = -1;
    int COUNT_LOW = 0;
    int COUNT_HIGH = 15;

    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    int getValue();

}
