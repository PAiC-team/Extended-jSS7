package org.restcomm.protocols.ss7.isup.message;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ChargeInformationMessage extends ISUPMessage {
    /**
     * Charge Information Message, Q.763 reference table (Note) <br>
     * {@link ChargeInformationMessage}
     */
    int MESSAGE_CODE = 0x31;
    //Q763 - > – "The format of this message is a national matter."

}
