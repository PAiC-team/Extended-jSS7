
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.M3UAManagementEventListener;
import org.restcomm.protocols.ss7.m3ua.State;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMStateEventHandler;

/**
 *
 * @author amit bhayani
 *
 */
public class AspStateEnterDown implements FSMStateEventHandler {

    private static final Logger logger = Logger.getLogger(AspStateEnterDown.class);

    private final AspImpl aspImpl;

    public AspStateEnterDown(AspImpl aspImpl) {
        this.aspImpl = aspImpl;
    }

    @Override
    public void onEvent(FSMState state) {

        // Call listener and indicate of state change only if not already done
        if (!this.aspImpl.state.getName().equals(State.STATE_DOWN)) {
            AspState oldState = AspState.getState(this.aspImpl.state.getName());
            this.aspImpl.state = AspState.DOWN;

            FastList<M3UAManagementEventListener> managementEventListenersTmp = this.aspImpl.aspFactoryImpl.m3UAManagementImpl.managementEventListeners;

            for (FastList.Node<M3UAManagementEventListener> n = managementEventListenersTmp.head(), end = managementEventListenersTmp
                    .tail(); (n = n.getNext()) != end;) {
                M3UAManagementEventListener m3uaManagementEventListener = n.getValue();
                try {
                    m3uaManagementEventListener.onAspDown(this.aspImpl, oldState);
                } catch (Throwable ee) {
                    logger.error("Exception while invoking onAspDown", ee);
                }
            }
        }
    }
}
