package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.restcomm.protocols.ss7.tcap.asn.AbortSource;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

public class TCUserAbortIndicationImpl extends DialogIndicationImpl implements TCUserAbortIndication {

    private UserInformation userInformation;
    private AbortSource abortSource;
    private ApplicationContextName acn;
    private ResultSourceDiagnostic resultSourceDiagnostic;
    private Boolean aareApdu = false;
    private Boolean abrtApdu = false;

    private SccpAddress originatingAddress;

    TCUserAbortIndicationImpl() {
        super(EventType.UAbort);
        // TODO Auto-generated constructor stub
    }

    // public External getAbortReason() {
    //
    // return abortReason;
    // }

    public Boolean IsAareApdu() {
        return this.aareApdu;
    }

    public void SetAareApdu() {
        this.aareApdu = true;
    }

    public Boolean IsAbrtApdu() {
        return this.abrtApdu;
    }

    public void SetAbrtApdu() {
        this.abrtApdu = true;
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

    /**
     * @return the abortSource
     */
    public AbortSource getAbortSource() {
        return abortSource;
    }

    public void setAbortSource(AbortSource abortSource) {
        this.abortSource = abortSource;

    }

    public ApplicationContextName getApplicationContextName() {
        return this.acn;
    }

    public void setApplicationContextName(ApplicationContextName applicationContextName) {
        this.acn = applicationContextName;
    }

    public ResultSourceDiagnostic getResultSourceDiagnostic() {
        return this.resultSourceDiagnostic;
    }

    public void setResultSourceDiagnostic(ResultSourceDiagnostic resultSourceDiagnostic) {
        this.resultSourceDiagnostic = resultSourceDiagnostic;
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
    public void setOriginatingAddress(SccpAddress sccpCalledPartyAddress) {
        this.originatingAddress = sccpCalledPartyAddress;

    }

}
