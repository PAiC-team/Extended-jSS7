
package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCBeginRequestImpl extends DialogRequestImpl implements TCBeginRequest {

    private boolean returnMessageOnError;
    private SccpAddress originatingAddress, destinationAddress;

    // fields
    private ApplicationContextName applicationContextName;
    private UserInformation userInformation;

    TCBeginRequestImpl() {
        super(EventType.Begin);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# getApplicationContextName()
     */
    public ApplicationContextName getApplicationContextName() {
        return this.applicationContextName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# getDestinationAddress()
     */
    public SccpAddress getDestinationAddress() {
        return this.destinationAddress;
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
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# getUserInformation()
     */
    public UserInformation getUserInformation() {
        return this.userInformation;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# setApplicationContextName
     * (org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName)
     */
    public void setApplicationContextName(ApplicationContextName applicationContextName) {
        this.applicationContextName = applicationContextName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest# setDestinationAddress
     * (org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    public void setDestinationAddress(SccpAddress sccpCalledPartyAddress) {
        this.destinationAddress = sccpCalledPartyAddress;

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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest#
     * setUserInformation(org.restcomm.protocols.ss7.tcap.asn.UserInformation)
     */
    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;

    }

    public void setReturnMessageOnError(boolean returnMessageOnError) {
        this.returnMessageOnError = returnMessageOnError;
    }

    public boolean getReturnMessageOnError() {
        return this.returnMessageOnError;
    }

}
