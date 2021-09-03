
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastSet;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;

/**
 *
 * @author amit bhayani
 *
 */
public class THPeerAsInActToAct implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THPeerAsInActToAct.class);

    private AsImpl asImpl;
    private FSM fsm;

    THPeerAsInActToAct(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    @Override
    public boolean process(FSMState state) {
        FastSet<AsStateListener> asStateListeners = this.asImpl.getAsStateListeners();
        for (FastSet.Record r = asStateListeners.head(), end = asStateListeners.tail(); (r = r.getNext()) != end;) {
            AsStateListener asAsStateListener = asStateListeners.valueOf(r);
            try {
                asAsStateListener.onAsActive(this.asImpl);
            } catch (Exception e) {
                logger.error(String.format("Error while calling AsStateListener=%s onAsActive method for As=%s",
                        asAsStateListener, this.asImpl));
            }
        }
        return true;
    }

}
