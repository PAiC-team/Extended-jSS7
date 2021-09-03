package org.restcomm.protocols.ss7.isup.message;

import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.ISUPParameter;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageType;

/**
 * Start time:08:55:07 2009-08-30<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ISUPMessage extends Serializable{

    /**
     * Sets sls to be used when this message is send. If message is received, it contians sls of link on which it has been
     * received.
     *
     * @param sls
     */
    void setSls(int sls);

    int getSls();

    /**
     * Get mandatory field, CIC.
     *
     * @return
     */
    CircuitIdentificationCode getCircuitIdentificationCode();

    /**
     * Set mandatory field, CIC.
     *
     * @return
     */
    void setCircuitIdentificationCode(CircuitIdentificationCode cic);

    /**
     * Returns message code. See Q.763 Table 4. It simply return value of static constant, where value of parameter is value of
     * MESSAGE_CODE
     *
     * @return
     */
    MessageType getMessageType();

    /**
     * Adds parameter to this message.
     *
     * @param param
     * @throws ParameterException - thrown if parameter is not part of message.
     */
    void addParameter(ISUPParameter param) throws ParameterException;

    /**
     * Returns parameter with passed code.
     *
     * @param parameterCode
     * @return
     * @throws ParameterException - thrown if code does not match any parameter.
     */
    ISUPParameter getParameter(int parameterCode) throws ParameterException;

    /**
     * Removes parameter from this message.
     *
     * @param parameterCode
     * @throws ParameterException
     */
    void removeParameter(int parameterCode) throws ParameterException;

    /**
     * @return <ul>
     *         <li><b>true</b> - if all requried parameters are set</li>
     *         <li><b>false</b> - otherwise</li>
     *         </ul>
     */
    boolean hasAllMandatoryParameters();

}
