package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.SubsequentNumber;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface SubsequentDirectoryNumberMessage extends ISUPMessage {
    /**
     * Subsequent Directory Number Message, Q.763 reference table 53 <br>
     * {@link SubsequentDirectoryNumberMessage}
     */
    int MESSAGE_CODE = 0x43;

    SubsequentNumber getSubsequentNumber();

    void setSubsequentNumber(SubsequentNumber sn);

    MessageCompatibilityInformation getMessageCompatibilityInformation();

    void setMessageCompatibilityInformation(MessageCompatibilityInformation mci);
}
