
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Confidentiality;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUserAbortIndication;

public class TCUserAbortIndicationImpl extends DialogIndicationImpl implements TCUserAbortIndication {

    private UserInformation userInformation;
    private ApplicationContext acn;
    private SecurityContext securityContext;
    private Confidentiality confidentiality;
    private UserInformationElement userAbortInformation;
    private SccpAddress originatingAddress;


    TCUserAbortIndicationImpl() {
        super(EventType.Abort);
    }

    public UserInformation getUserInformation() {

        return userInformation;
    }

    /**
     * @param userInformation the userInformation to set
     */
    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public ApplicationContext getApplicationContextName() {
        return this.acn;
    }

    public void setApplicationContextName(ApplicationContext acn) {
        this.acn = acn;
    }

    @Override
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext val) {
        securityContext = val;
    }

    @Override
    public Confidentiality getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(Confidentiality val) {
        confidentiality = val;
    }

    @Override
    public UserInformationElement getUserAbortInformation() {
        return userAbortInformation;
    }

    public void setUserAbortInformation(UserInformationElement val) {
        userAbortInformation = val;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# getOriginatingAddress()
     */
    public SccpAddress getOriginatingAddress() {

        return this.originatingAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# setOriginatingAddress
     * (org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    public void setOriginatingAddress(SccpAddress dest) {
        this.originatingAddress = dest;

    }

}
