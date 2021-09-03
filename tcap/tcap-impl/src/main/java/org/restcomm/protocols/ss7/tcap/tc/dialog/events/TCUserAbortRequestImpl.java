package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.DialogServiceUserType;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class TCUserAbortRequestImpl extends DialogRequestImpl implements TCUserAbortRequest {

    private boolean returnMessageOnError;
    private SccpAddress originatingAddress;

    // fields
    private ApplicationContextName applicationContextName;
    private UserInformation userInformation;

    private DialogServiceUserType dialogServiceUserType;

    TCUserAbortRequestImpl() {
        super(EventType.UAbort);
    }

    // public External getAbortReason() {
    // return this.abortReason;
    // }

    public ApplicationContextName getApplicationContextName() {
        return this.applicationContextName;
    }

    public UserInformation getUserInformation() {
        return this.userInformation;
    }

    // public void setAbortReason(External abortReason) {
    // this.abortReason = abortReason;
    // }

    public void setApplicationContextName(ApplicationContextName applicationContextName) {
        this.applicationContextName = applicationContextName;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public void setDialogServiceUserType(DialogServiceUserType dialogServiceUserType) {
        this.dialogServiceUserType = dialogServiceUserType;
    }

    public DialogServiceUserType getDialogServiceUserType() {
        return this.dialogServiceUserType;
    }

    public void setReturnMessageOnError(boolean val) {
        this.returnMessageOnError = val;
    }

    public boolean getReturnMessageOnError() {
        return this.returnMessageOnError;
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
    public void setOriginatingAddress(SccpAddress sccpCallingPartyAddress) {
        this.originatingAddress = sccpCallingPartyAddress;

    }

}
