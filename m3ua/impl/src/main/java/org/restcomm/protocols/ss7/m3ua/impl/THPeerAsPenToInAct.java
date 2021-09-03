
package org.restcomm.protocols.ss7.m3ua.impl;

import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;

/**
 * <p>
 * When the far end As transitions to INACTIVE, the local As should also transition on INACTIVE
 * </p>
 * <p>
 * Clear the pending queue
 * </p>
 *
 * @author amit bhayani
 *
 */
public class THPeerAsPenToInAct implements TransitionHandler {

    private AsImpl asImpl;

    public THPeerAsPenToInAct(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
    }

    public boolean process(FSMState state) {
        this.asImpl.clearPendingQueue();
        return true;
    }

}
