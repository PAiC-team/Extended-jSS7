
package org.restcomm.protocols.ss7.m3ua.impl;

import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;

/**
 * Transition handler that doesn't cause State change
 *
 * @author amit bhayani
 */
public class THNoTrans implements TransitionHandler {

    public THNoTrans() {
    }

    @Override
    public boolean process(FSMState state) {
        return false;
    }

}
