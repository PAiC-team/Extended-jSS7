
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
public interface TCResponseRequest extends DialogRequest {

    /**
     * Sets origin address. This parameter is used only in first TCResponse, sent as response to TCQuery. This parameter, if
     * set, changes local peer address(remote end will send request to value set by this method).
     *
     * @return
     */
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
