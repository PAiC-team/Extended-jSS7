package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

public interface TCContinueIndication extends DialogIndication {

    // public Byte getQOS();

    ApplicationContextName getApplicationContextName();

    UserInformation getUserInformation();

    SccpAddress getOriginatingAddress();
}
