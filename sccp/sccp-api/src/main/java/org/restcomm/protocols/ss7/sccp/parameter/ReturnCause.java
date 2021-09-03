
package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Return cause values parameter for connectionless message
 *
 * @author baranowb
 * @author sergey vetyutnev
 */
public interface ReturnCause extends Parameter {

    int PARAMETER_CODE = 0xB;

    /**
     * Gets the value of this parameter.
     *
     * @return the value of this parameter.
     */
    ReturnCauseValue getValue();

    /**
     * @return the digital value of a parameter
     */
    int getDigitalValue();

}
