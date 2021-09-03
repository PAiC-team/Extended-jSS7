
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.M3UAManagementEventListener;
import org.restcomm.protocols.ss7.m3ua.State;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMStateEventHandler;

/**
 *
 * @author amit bhayani
 *
 */
public abstract class SEHAsStateEnterPen implements FSMStateEventHandler {

    private static final Logger logger = Logger.getLogger(SEHAsStateEnterPen.class);

    protected AsImpl asImpl = null;
    private FSM fsm;

    public SEHAsStateEnterPen(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public void onEvent(FSMState state) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Entered in PENDING state for As=%s", asImpl.getName()));
        }

        if (!this.asImpl.state.getName().equals(State.STATE_PENDING)) {
            AsState oldState = AsState.getState(this.asImpl.state.getName());
            this.asImpl.state = AsState.PENDING;

            FastList<M3UAManagementEventListener> managementEventListenersTmp = this.asImpl.m3UAManagementImpl.managementEventListeners;

            for (FastList.Node<M3UAManagementEventListener> n = managementEventListenersTmp.head(), end = managementEventListenersTmp
                    .tail(); (n = n.getNext()) != end;) {
                M3UAManagementEventListener m3uaManagementEventListener = n.getValue();
                try {
                    m3uaManagementEventListener.onAsPending(this.asImpl, oldState);
                } catch (Throwable ee) {
                    logger.error("Exception while invoking onAsPending", ee);
                }
            }
        }
    }
}
