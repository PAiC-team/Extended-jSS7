
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;

/**
 *
 * @author amit bhayani
 *
 */
public class THPeerAsInActToDwn implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THPeerAsActToPen.class);

    private AsImpl asImpl;
    private FSM fsm;

    public THPeerAsInActToDwn(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {
        AspImpl causeAsp = (AspImpl) this.fsm.getAttribute(AsImpl.ATTRIBUTE_ASP);

        // check if there is at least one other ASP in INACTIVE state. If
        // yes this AS remains in INACTIVE state else goes in DOWN state.
        for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                .getNext()) != end;) {
            AspImpl aspImpl = (AspImpl) n.getValue();

            FSM aspLocalFSM = aspImpl.getLocalFSM();
            AspState aspState = AspState.getState(aspLocalFSM.getState().getName());

            if (aspImpl != causeAsp && aspState == AspState.INACTIVE) {
                return false;
            }
        }
        return true;
    }

}
