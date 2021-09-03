
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupQueryMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupQueryResponseMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitStateIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.RangeAndStatus;

/**
 * @author baranowb
 *
 */
public class CQMTest extends SingleTimers {

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT()
     */

    protected long getT() {
        return ISUPTimeoutEvent.T28_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT_ID()
     */

    protected int getT_ID() {
        return ISUPTimeoutEvent.T28;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness# getRequest()
     */

    protected ISUPMessage getRequest() {
        CircuitGroupQueryMessage msg = super.provider.getMessageFactory().createCQM(1);
        RangeAndStatus ras = super.provider.getParameterFactory().createRangeAndStatus();
        ras.setRange((byte) 7, true);
        ras.setAffected((byte) 1, true);
        ras.setAffected((byte) 0, true);
        msg.setRangeAndStatus(ras);
        return msg;
    }

    protected ISUPMessage getAfterTRequest() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getAnswer ()
     */
    protected ISUPMessage getAnswer() {
        CircuitGroupQueryResponseMessage ans = super.provider.getMessageFactory().createCQR();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        ans.setCircuitIdentificationCode(cic);

        RangeAndStatus ras = super.provider.getParameterFactory().createRangeAndStatus();
        ras.setRange((byte) 7, true);
        ras.setAffected((byte) 1, true);
        ras.setAffected((byte) 0, true);
        ans.setRangeAndStatus(ras);

        CircuitStateIndicator ci = super.provider.getParameterFactory().createCircuitStateIndicator();
        byte[] state = new byte[2];
        state[0] = ci.createCircuitState(ci._MBS_LAR_BLOCKED, ci._CPS_CIB, ci._HBS_LAR_BLOCKED);
        state[1] = ci.createCircuitState(ci._MBS_LAR_BLOCKED, ci._CPS_COB, ci._HBS_LAR_BLOCKED);
        ci.setCircuitState(state);
        ans.setCircuitStateIndicator(ci);
        return ans;
    }
}
