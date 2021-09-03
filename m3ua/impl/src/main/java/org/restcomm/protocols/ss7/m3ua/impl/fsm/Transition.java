
package org.restcomm.protocols.ss7.m3ua.impl.fsm;

/**
 *
 * @author amit bhayani
 * @author kulikov
 *
 */
public class Transition {

    private String name;
    protected FSMState destination;

    private TransitionHandler handler;

    protected Transition(String name, FSMState destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setHandler(TransitionHandler handler) {
        this.handler = handler;
    }

    protected FSMState process(FSMState state) {
        // leave current state
        state.leave();

        // new Thread(this).start();
        if (handler != null) {
            if (!handler.process(state)) {
                // If handler couldn't process this transition successfully,
                // return the old state (but this means the LeaveAction could
                // have been executed and we are OK with that). Also, we call
                // cancelLeave on State so as to assign back the last timeout
                // value
                state.cancelLeave();
                return state;
            }
        }

        // enter to the destination
        this.destination.enter();
        return this.destination;
    }

    @Override
    public String toString() {
        return name;
    }
}
