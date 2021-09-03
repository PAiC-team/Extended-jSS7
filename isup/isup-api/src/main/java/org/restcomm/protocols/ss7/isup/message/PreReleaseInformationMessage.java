package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.ApplicationTransport;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.OptionalBackwardCallIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.OptionalForwardCallIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.ParameterCompatibilityInformation;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface PreReleaseInformationMessage extends ISUPMessage {
    /**
     * Pre-release information Message, Q.763 reference table 52 <br>
     * {@link PreReleaseInformationMessage}
     */
    int MESSAGE_CODE = 0x42;

    MessageCompatibilityInformation getMessageCompatibilityInformation();

    void setMessageCompatibilityInformation(MessageCompatibilityInformation mci);

    ParameterCompatibilityInformation getParameterCompatibilityInformation();

    void setParameterCompatibilityInformation(ParameterCompatibilityInformation pci);

    OptionalForwardCallIndicators getOptionalForwardCallIndicators();

    void setOptionalForwardCallIndicators(OptionalForwardCallIndicators obci);

    OptionalBackwardCallIndicators getOptionalBackwardCallIndicators();

    void setOptionalBackwardCallIndicators(OptionalBackwardCallIndicators obci);

    ApplicationTransport getApplicationTransport();

    void setApplicationTransport(ApplicationTransport at);
}
