package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.restcomm.protocols.ss7.isup.message.parameter.ConnectionRequest;
import org.restcomm.protocols.ss7.isup.message.parameter.FacilityIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.ParameterCompatibilityInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.UserToUserIndicators;

/**
 * Super interface for FAA/FAR
 *
 * @author baranowb
 *
 */
public interface AbstractFacilityMessage extends ISUPMessage {
    //bad name for interface, but...
    void setFacilityIndicator(FacilityIndicator fi);

    FacilityIndicator getFacilityIndicator();

    void setUserToUserIndicators(UserToUserIndicators u2ui);

    UserToUserIndicators getUserToUserIndicators();

    void setCallReference(CallReference cf);

    CallReference getCallReference();

    void setConnectionRequest(ConnectionRequest cr);

    ConnectionRequest getConnectionRequest();

    void setParameterCompatibilityInformation(ParameterCompatibilityInformation pci);

    ParameterCompatibilityInformation getCompatibilityInformation();
}
