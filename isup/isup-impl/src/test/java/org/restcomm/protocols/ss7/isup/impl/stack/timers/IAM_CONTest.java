
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.ConnectMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.InitialAddressMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CalledPartyNumber;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyCategory;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.ForwardCallIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.NatureOfConnectionIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.TransmissionMediumRequirement;

/**
 * @author baranowb
 *
 */
public class IAM_CONTest extends SingleTimers {

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
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getRequest()
     */

    protected ISUPMessage getRequest() {
        InitialAddressMessage msg = super.provider.getMessageFactory().createIAM(1);
        NatureOfConnectionIndicators nai = super.provider.getParameterFactory().createNatureOfConnectionIndicators();
        ForwardCallIndicators fci = super.provider.getParameterFactory().createForwardCallIndicators();
        CallingPartyCategory cpg = super.provider.getParameterFactory().createCallingPartyCategory();
        TransmissionMediumRequirement tmr = super.provider.getParameterFactory().createTransmissionMediumRequirement();
        CalledPartyNumber cpn = super.provider.getParameterFactory().createCalledPartyNumber();
        cpn.setAddress("14614577");

        msg.setNatureOfConnectionIndicators(nai);
        msg.setForwardCallIndicators(fci);
        msg.setCallingPartCategory(cpg);
        msg.setCalledPartyNumber(cpn);
        msg.setTransmissionMediumRequirement(tmr);

        return msg;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getAnswer()
     */

    protected ISUPMessage getAnswer() {
        ConnectMessage ans = super.provider.getMessageFactory().createCON();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        ans.setCircuitIdentificationCode(cic);
        return ans;
    }
}
