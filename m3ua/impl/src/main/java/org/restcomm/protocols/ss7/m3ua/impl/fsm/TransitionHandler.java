
package org.restcomm.protocols.ss7.m3ua.impl.fsm;

/**
 *
 * @author amit bhayani
 * @author kulikov
 *
 */
public interface TransitionHandler {

    boolean process(FSMState state);

}
