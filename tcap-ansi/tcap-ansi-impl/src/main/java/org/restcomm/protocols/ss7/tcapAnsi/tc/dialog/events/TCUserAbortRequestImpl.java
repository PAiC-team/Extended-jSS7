
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Confidentiality;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUserAbortRequest;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class TCUserAbortRequestImpl extends DialogRequestImpl implements TCUserAbortRequest {

    // fields
    private ApplicationContext applicationContextName;
    private UserInformation userInformation;
    private SecurityContext securityContext;
    private Confidentiality confidentiality;
    private UserInformationElement userAbortInformation;
    private SccpAddress originatingAddress;

    TCUserAbortRequestImpl() {
        super(EventType.Abort);
    }

    // public External getAbortReason() {
    // return this.abortReason;
    // }

    public ApplicationContext getApplicationContextName() {
        return this.applicationContextName;
    }

    public UserInformation getUserInformation() {
        return this.userInformation;
    }

    // public void setAbortReason(External abortReason) {
    // this.abortReason = abortReason;
    // }

    public void setApplicationContextName(ApplicationContext acn) {
        this.applicationContextName = acn;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;

    }

    public void setReturnMessageOnError(boolean val) {
        returnMessageOnError = val;
    }

    public boolean getReturnMessageOnError() {
        return returnMessageOnError;
    }

    @Override
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    @Override
    public void setSecurityContext(SecurityContext val) {
        securityContext = val;
    }

    @Override
    public Confidentiality getConfidentiality() {
        return confidentiality;
    }

    @Override
    public void setConfidentiality(Confidentiality val) {
        confidentiality = val;
    }

    @Override
    public UserInformationElement getUserAbortInformation() {
        return userAbortInformation;
    }

    @Override
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
