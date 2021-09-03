
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Confidentiality;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;

/**
 * @author baranowb
 *
 */
public interface TCQueryIndication extends DialogIndication {

    SccpAddress getDestinationAddress();

    SccpAddress getOriginatingAddress();

    boolean getDialogTermitationPermission();


    ApplicationContext getApplicationContextName();

    UserInformation getUserInformation();

    SecurityContext getSecurityContext();

    Confidentiality getConfidentiality();

}
