
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ReleaseCompleteMessage;
import org.restcomm.protocols.ss7.isup.message.ReleaseMessage;
import org.restcomm.protocols.ss7.isup.message.ResetCircuitMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;

/**
 * @author baranowb
 *
 */
public class RELTest extends DoubleTimers {
    // thanks to magic of super class, this is whole test :)
    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getSmallerT()
     */

    protected long getSmallerT() {
        return ISUPTimeoutEvent.T1_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getBiggerT()
     */

    protected long getBiggerT() {
        return ISUPTimeoutEvent.T5_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getSmallerT_ID()
     */

    protected int getSmallerT_ID() {
        return ISUPTimeoutEvent.T1;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getBiggerT_ID()
     */

    protected int getBiggerT_ID() {
        return ISUPTimeoutEvent.T5;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getRequest()
     */

    protected ISUPMessage getRequest() {
        ReleaseMessage rel = super.provider.getMessageFactory().createREL(1);
        CauseIndicators ci = super.provider.getParameterFactory().createCauseIndicators();
        ci.setCauseValue(0);
        ci.setCodingStandard(ci._CODING_STANDARD_NATIONAL);
        rel.setCauseIndicators(ci);
        return rel;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getAnswer()
     */

    protected ISUPMessage getAnswer() {
        ReleaseCompleteMessage rlc = super.provider.getMessageFactory().createRLC();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        rlc.setCircuitIdentificationCode(cic);
        return rlc; // asnwer to RSC is... RLC :P
    }

    protected ISUPMessage getAfterBigTRequest() {
        ResetCircuitMessage rsc = super.provider.getMessageFactory().createRSC(1);
        return rsc;
    }
}
