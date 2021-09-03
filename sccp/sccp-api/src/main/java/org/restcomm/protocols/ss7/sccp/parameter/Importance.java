package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Importance parameter
 *
 * @author baranowb
 * @author kulikov
 */
public interface Importance extends Parameter {

    int PARAMETER_CODE = 0x12;

    /**
     * Gets the value of this parameter.
     *
     * @return parameter value.
     */
    int getValue();
}
