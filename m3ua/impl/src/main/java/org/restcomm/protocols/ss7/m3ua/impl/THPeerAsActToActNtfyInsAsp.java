
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.UnknownTransitionException;

/**
 * NTFY is received stating that there are insufficient ASP in ACTIVE state. Hence check if AS has more ASP's in INACTIVE state.
 * If found make them ACTIVE
 *
 * @author amit bhayani
 *
 */
public class THPeerAsActToActNtfyInsAsp implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THPeerAsActToActNtfyInsAsp.class);

    private AsImpl asImpl;
    private FSM fsm;

    public THPeerAsActToActNtfyInsAsp(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {

        // Iterate through all the ASP for this AS and activate if they are
        // inactive
        for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                .getNext()) != end;) {
            AspImpl aspTemp = (AspImpl) n.getValue();
            AspFactoryImpl factory = aspTemp.getAspFactory();

            FSM aspLocalFSM = aspTemp.getLocalFSM();
            AspState aspState = AspState.getState(aspLocalFSM.getState().getName());

            if (aspState == AspState.INACTIVE && factory.getStatus()) {
                factory.sendAspActive(this.asImpl);
                try {
                    aspLocalFSM.signal(TransitionState.ASP_ACTIVE_SENT);
                } catch (UnknownTransitionException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return true;
    }
}
