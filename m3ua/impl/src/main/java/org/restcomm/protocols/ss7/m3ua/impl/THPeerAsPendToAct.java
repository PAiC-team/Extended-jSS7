
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;

/**
 * @author amit bhayani
 *
 */
public class THPeerAsPendToAct implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THPeerAsPendToAct.class);

    private AsImpl asImpl;
    private FSM fsm;

    /**
     *
     */
    public THPeerAsPendToAct(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler#process(org.mobicents.protocols.ss7.m3ua.impl.fsm.State)
     */
    @Override
    public boolean process(FSMState state) {

        // Send the PayloadData (if any) from pending queue to other side
        AspImpl causeAsp = (AspImpl) this.fsm.getAttribute(AsImpl.ATTRIBUTE_ASP);
        this.asImpl.sendPendingPayloadData(causeAsp);

        return true;
    }

}
