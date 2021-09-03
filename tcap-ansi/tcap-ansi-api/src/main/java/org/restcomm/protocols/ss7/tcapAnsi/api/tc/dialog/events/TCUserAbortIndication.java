
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Confidentiality;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;

/**
 *
 * @author amit bhayani
 * @author baranowb
 */
public interface TCUserAbortIndication extends DialogIndication {

    ApplicationContext getApplicationContextName();

    UserInformation getUserInformation();

    SecurityContext getSecurityContext();

    Confidentiality getConfidentiality();

    UserInformationElement getUserAbortInformation();

    SccpAddress getOriginatingAddress();

}
