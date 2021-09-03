
package org.restcomm.protocols.ss7.sccp.impl;

import org.restcomm.protocols.ss7.sccp.impl.message.SccpAddressedMessageImpl;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;

/**
*
* @author sergey vetyutnev
*
*/
public interface SccpRoutingCtxInterface {

    void sendSccpError(SccpAddressedMessageImpl sccpAddressedMessage, ReturnCauseValue returnCauseInt, RefusalCauseValue refusalCauseInt)
            throws Exception;

    void routeAddressed(SccpAddressedMessageImpl sccpAddressedMessage) throws Exception;

}
