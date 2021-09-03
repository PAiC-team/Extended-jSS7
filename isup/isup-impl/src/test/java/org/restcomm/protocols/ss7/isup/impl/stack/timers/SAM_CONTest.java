
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.ConnectMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.SubsequentAddressMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.SubsequentNumber;

/**
 * @author baranowb
 *
 */
public class SAM_CONTest extends SingleTimers {

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT()
     */

    protected long getT() {
        return ISUPTimeoutEvent.T7_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT_ID()
     */

    protected int getT_ID() {
        return ISUPTimeoutEvent.T7;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness# getRequest()
     */

    protected ISUPMessage getRequest() {
        SubsequentAddressMessage msg = super.provider.getMessageFactory().createSAM(1);

        SubsequentNumber sn = super.provider.getParameterFactory().createSubsequentNumber();
        sn.setAddress("11");
        msg.setSubsequentNumber(sn);

        return msg;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getAnswer ()
     */

    protected ISUPMessage getAnswer() {
        ConnectMessage ans = super.provider.getMessageFactory().createCON();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        ans.setCircuitIdentificationCode(cic);
        return ans;
    }
}
