
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.InformationMessage;
import org.restcomm.protocols.ss7.isup.message.InformationRequestMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationRequestIndicators;

/**
 * @author baranowb
 *
 */
public class INRTest extends SingleTimers {

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT()
     */

    protected long getT() {
        return ISUPTimeoutEvent.T33_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.SingleTimers#getT_ID()
     */

    protected int getT_ID() {
        return ISUPTimeoutEvent.T33;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getRequest()
     */

    protected ISUPMessage getRequest() {
        InformationRequestMessage msg = super.provider.getMessageFactory().createINR(1);
        InformationRequestIndicators inri = super.provider.getParameterFactory().createInformationRequestIndicators();
        msg.setInformationRequestIndicators(inri);
        return msg;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.timers.EventTestHarness#getAnswer()
     */

    protected ISUPMessage getAnswer() {
        InformationMessage ans = super.provider.getMessageFactory().createINF();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        ans.setCircuitIdentificationCode(cic);

        InformationIndicators ii = super.provider.getParameterFactory().createInformationIndicators();
        ii.setCallingPartyAddressResponseIndicator(ii._CPARI_ADDRESS_INCLUDED);
        ans.setInformationIndicators(ii);
        return ans;
    }
}
