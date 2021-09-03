package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.ApplicationTransport;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.ParameterCompatibilityInformation;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ApplicationTransportMessage extends ISUPMessage {

    /**
     * Application Transport Message, Q.763 reference table 51 <br>
     * {@link ApplicationTransportMessage}
     */
    int MESSAGE_CODE = 0x41;

    void setMessageCompatibilityInformation(MessageCompatibilityInformation mci);
    MessageCompatibilityInformation getMessageCompatibilityInformation();

    void setParameterCompatibilityInformation(ParameterCompatibilityInformation pci);
    ParameterCompatibilityInformation getParameterCompatibilityInformation();

    void setApplicationTransport(ApplicationTransport atp);
    ApplicationTransport getApplicationTransport();
}
