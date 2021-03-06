
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;
import javolution.util.FastSet;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMStateEventHandler;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.UnknownTransitionException;

/**
 * {@link AsStatePenTimeout#onEvent(FSMState)} is called when the pending timer T(r) expires.
 *
 * @author amit bhayani
 *
 */
public class AsStatePenTimeout implements FSMStateEventHandler {

    private AsImpl asImpl;
    private FSM fsm;
    private static final Logger logger = Logger.getLogger(AsStatePenTimeout.class);

    boolean inactive = false;

    public AsStatePenTimeout(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    /**
     * <p>
     * An active ASP has transitioned to ASP-INACTIVE or ASP DOWN and it was the last remaining active ASP in the AS. A recovery
     * timer T(r) SHOULD be started, and all incoming signalling messages SHOULD be queued by the SGP. If an ASP becomes
     * ASP-ACTIVE before T(r) expires, the AS is moved to the AS-ACTIVE state, and all the queued messages will be sent to the
     * ASP.
     * </p>
     * <p>
     * If T(r) expires before an ASP becomes ASP-ACTIVE, and the SGP has no alternative, the SGP may stop queuing messages and
     * discard all previously queued messages. The AS will move to the AS-INACTIVE state if at least one ASP is in ASP-INACTIVE;
     * otherwise, it will move to AS-DOWN state.
     * </p>
     */
    public void onEvent(FSMState state) {

        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(String.format("PENDING timed out for As=%s", this.asImpl.getName()));
        }

        // Clear the Pending Queue for this As
        this.asImpl.clearPendingQueue();

        this.inactive = false;

        // check if there are any ASP's who are INACTIVE, transition to
        // INACTIVE, else transition to DOWN
        for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                .getNext()) != end;) {
            AspImpl aspImpl = (AspImpl) n.getValue();

            FSM aspLocalFSM = aspImpl.getLocalFSM();

            if (AspState.getState(aspLocalFSM.getState().getName()) == AspState.INACTIVE) {
                try {
                    this.fsm.signal(TransitionState.AS_INACTIVE);
                    inactive = true;
                    break;
                } catch (UnknownTransitionException e) {
                    logger.error(e.getMessage(), e);
                }

            }// if
        }// for

        if (!this.inactive) {
            // else transition to DOWN
            try {
                this.fsm.signal(TransitionState.AS_DOWN);
                inactive = true;
            } catch (UnknownTransitionException e) {
                logger.error(e.getMessage(), e);
            }
        }

        // Now send MTP3 PAUSE
        FastSet<AsStateListener> asStateListeners = this.asImpl.getAsStateListeners();
        for (FastSet.Record r = asStateListeners.head(), end = asStateListeners.tail(); (r = r.getNext()) != end;) {
            AsStateListener asAsStateListener = asStateListeners.valueOf(r);
            try {
                asAsStateListener.onAsInActive(this.asImpl);
            } catch (Exception e) {
                logger.error(String.format("Error while calling AsStateListener=%s onAsInActive method for As=%s",
                        asAsStateListener, this.asImpl));
            }
        }
    }

}
