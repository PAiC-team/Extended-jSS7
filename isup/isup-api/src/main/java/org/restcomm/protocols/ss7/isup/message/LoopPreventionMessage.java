package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.CallTransferReference;
import org.restcomm.protocols.ss7.isup.message.parameter.LoopPreventionIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.ParameterCompatibilityInformation;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface LoopPreventionMessage extends ISUPMessage {
    /**
     * Loopback Prevention Message, Q.763 reference table 50 <br>
     * {@link LoopPreventionMessage}
     */
    int MESSAGE_CODE = 0x40;

    MessageCompatibilityInformation getMessageCompatibilityInformation();

    void setMessageCompatibilityInformation(MessageCompatibilityInformation mci);

    ParameterCompatibilityInformation getParameterCompatibilityInformation();

    void setParameterCompatibilityInformation(ParameterCompatibilityInformation pci);

    CallTransferReference getCallTransferReference();

    void setCallTransferReference(CallTransferReference ctr);

    LoopPreventionIndicators getLoopPreventionIndicators();

    void setLoopPreventionIndicators(LoopPreventionIndicators lpi);
}
