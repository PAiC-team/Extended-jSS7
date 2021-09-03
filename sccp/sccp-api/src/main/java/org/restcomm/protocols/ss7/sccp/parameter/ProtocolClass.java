package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Protocol class (contains class data (0-3) and "return message on error" option for connectionless classes)
 *
 * The "protocol class" parameter field is a one-octet parameter and is structured as follows: Bits 1-4 indicating protocol
 * class are coded as follows: 4321 0000 class 0 0001 class 1 0010 class 2 0011 class 3
 *
 * @author baranowb
 * @author kulikov
 */
public interface ProtocolClass extends Parameter {

    int PARAMETER_CODE = 0x05;

    int HANDLING_RET_ERR = 0x08;

    /**
     * The value of protocol class.
     *
     * @return protocol class code
     */
    int getProtocolClass();

    /**
     * Gets a "return message on error" flag
     *
     * @return
     */
    boolean getReturnMessageOnError();

    /**
     * Clear a flag "ReturnMessageOnError"
     */
    void clearReturnMessageOnError();
}
