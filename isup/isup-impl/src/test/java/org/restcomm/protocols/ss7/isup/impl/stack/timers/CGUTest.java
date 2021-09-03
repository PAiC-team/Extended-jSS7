
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupUnblockingAckMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupUnblockingMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitGroupSuperVisionMessageType;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitIdentificationCode;
import org.restcomm.protocols.ss7.isup.message.parameter.RangeAndStatus;

/**
 * @author baranowb
 *
 */
public class CGUTest extends DoubleTimers {
    // thanks to magic of super class, this is whole test :)
    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getSmallerT()
     */

    protected long getSmallerT() {
        return ISUPTimeoutEvent.T20_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getBiggerT()
     */

    protected long getBiggerT() {
        return ISUPTimeoutEvent.T21_DEFAULT;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getSmallerT_ID()
     */

    protected int getSmallerT_ID() {
        return ISUPTimeoutEvent.T20;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getBiggerT_ID()
     */

    protected int getBiggerT_ID() {
        return ISUPTimeoutEvent.T21;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getRequest()
     */

    protected ISUPMessage getRequest() {
        CircuitGroupUnblockingMessage cgu = super.provider.getMessageFactory().createCGU(1);

        RangeAndStatus ras = super.provider.getParameterFactory().createRangeAndStatus();
        ras.setRange((byte) 7, true);
        ras.setAffected((byte) 1, true);
        ras.setAffected((byte) 0, true);
        cgu.setRangeAndStatus(ras);

        CircuitGroupSuperVisionMessageType cgsvmt = super.provider.getParameterFactory()
                .createCircuitGroupSuperVisionMessageType();
        cgsvmt.setCircuitGroupSuperVisionMessageTypeIndicator(cgsvmt._CIRCUIT_GROUP_SMTIHFO);
        cgu.setSupervisionType(cgsvmt);

        return cgu;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.isup.impl.stack.DoubleTimers#getAnswer()
     */

    protected ISUPMessage getAnswer() {
        CircuitGroupUnblockingAckMessage cgua = super.provider.getMessageFactory().createCGUA();
        CircuitIdentificationCode cic = super.provider.getParameterFactory().createCircuitIdentificationCode();
        cic.setCIC(1);
        cgua.setCircuitIdentificationCode(cic);

        RangeAndStatus ras = super.provider.getParameterFactory().createRangeAndStatus();
        ras.setRange((byte) 7, true);
        ras.setAffected((byte) 1, true);
        ras.setAffected((byte) 0, true);
        cgua.setRangeAndStatus(ras);

        CircuitGroupSuperVisionMessageType cgsvmt = super.provider.getParameterFactory()
                .createCircuitGroupSuperVisionMessageType();
        cgsvmt.setCircuitGroupSuperVisionMessageTypeIndicator(cgsvmt._CIRCUIT_GROUP_SMTIHFO);
        cgua.setSupervisionType(cgsvmt);

        return cgua;
    }
}
