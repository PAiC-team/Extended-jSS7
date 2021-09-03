
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Confidentiality;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;

/**
 *
 * @author baranowb
 *
 */
public interface TCUniRequest extends DialogRequest {

    SccpAddress getDestinationAddress();

    void setDestinationAddress(SccpAddress dest);

    SccpAddress getOriginatingAddress();

    void setOriginatingAddress(SccpAddress dest);


    ApplicationContext getApplicationContextName();

    void setApplicationContextName(ApplicationContext acn);

    UserInformation getUserInformation();

    void setUserInformation(UserInformation acn);

    SecurityContext getSecurityContext();

    void setSecurityContext(SecurityContext val);

    Confidentiality getConfidentiality();

    void setConfidentiality(Confidentiality val);

}
