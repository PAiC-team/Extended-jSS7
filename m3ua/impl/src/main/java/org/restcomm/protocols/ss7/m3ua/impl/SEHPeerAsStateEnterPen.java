
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.UnknownTransitionException;

/**
 * @author amit bhayani
 *
 */
public class SEHPeerAsStateEnterPen extends SEHAsStateEnterPen {

    private static final Logger logger = Logger.getLogger(SEHPeerAsStateEnterPen.class);

    /**
     * @param asImpl
     * @param fsm
     */
    public SEHPeerAsStateEnterPen(AsImpl asImpl, FSM fsm) {
        super(asImpl, fsm);
    }

    @Override
    public void onEvent(FSMState state) {
        super.onEvent(state);

        // If there is even one ASP in INACTIVE state for this AS, ACTIVATE it
        for (FastList.Node<Asp> n = asImpl.appServerProcs.head(), end = asImpl.appServerProcs.tail(); (n = n.getNext()) != end;) {
            AspImpl aspImpl = (AspImpl) n.getValue();

            FSM aspLocalFSM = aspImpl.getLocalFSM();

            if (AspState.getState(aspLocalFSM.getState().getName()) == AspState.INACTIVE) {
                AspFactoryImpl aspFactoryImpl = aspImpl.getAspFactory();
                aspFactoryImpl.sendAspActive(this.asImpl);

                // Transition the state of ASP to ACTIVE_SENT
                try {
                    aspLocalFSM.signal(TransitionState.ASP_ACTIVE_SENT);
                } catch (UnknownTransitionException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

}
