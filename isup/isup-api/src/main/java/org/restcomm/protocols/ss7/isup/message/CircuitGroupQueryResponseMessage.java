package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.CircuitStateIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.RangeAndStatus;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CircuitGroupQueryResponseMessage extends ISUPMessage {

    /**
     * Circuit Group Query Response Message, Q.763 reference table 24 <br>
     * {@link CircuitGroupQueryResponseMessage}
     */
    int MESSAGE_CODE = 0x2B;

    void setRangeAndStatus(RangeAndStatus ras);

    RangeAndStatus getRangeAndStatus();

    void setCircuitStateIndicator(CircuitStateIndicator ras);

    CircuitStateIndicator getCircuitStateIndicator();
}
