
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
public class THLocalAsInactToDwn implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THLocalAsInactToDwn.class);

    private AsImpl asImpl;
    private FSM fsm;

    public THLocalAsInactToDwn(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    int inactCount = 0;

    public boolean process(FSMState state) {
        inactCount = 0;

        try {
            for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                    .getNext()) != end;) {
                AspImpl remAspImpl = (AspImpl) n.getValue();

                FSM aspPeerFSM = remAspImpl.getPeerFSM();
                AspState aspState = AspState.getState(aspPeerFSM.getState().getName());

                if (aspState == AspState.INACTIVE) {
                    inactCount++;
                }
            }

            if (inactCount > 0) {
                // We have at least one more ASP in INACTIVE state, the AS should
                // remain INACTIVE
                return false;
            }

            return true;
        } catch (Exception e) {
            logger.error(String.format("Error while translating Rem AS to DOWN. %s", this.fsm.toString()), e);
        }
        // something wrong
        return false;

    }
}
