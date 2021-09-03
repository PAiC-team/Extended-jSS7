
package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

/**
 * @author baranowb
 *
 */
public interface TCBeginRequest extends DialogRequest {

    void setReturnMessageOnError(boolean val);

    boolean getReturnMessageOnError();

    /**
     * Destination address. If this address is different than one in dialog, this value will overwrite dialog value.
     */
    SccpAddress getDestinationAddress();

    void setDestinationAddress(SccpAddress sccpCalledPartyAddress);

    /**
     * Origin address. If this address is different than one in dialog, this value will overwrite dialog value.
     */
    SccpAddress getOriginatingAddress();

    void setOriginatingAddress(SccpAddress sccpCallingPartyAddress);

    /**
     * Application context name for this dialog.
     *
     * @return
     */
    ApplicationContextName getApplicationContextName();

    void setApplicationContextName(ApplicationContextName applicationContextName);

    /**
     * User information for this dialog.
     *
     * @return
     */
    UserInformation getUserInformation();

    void setUserInformation(UserInformation acn);

}
