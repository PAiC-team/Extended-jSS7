
package org.restcomm.protocols.ss7.tools.simulator;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface Stoppable {

    void stop();

    void execute();

    String getState();

}
