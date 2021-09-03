
package org.restcomm.protocols.ss7.cap.api;

import org.restcomm.protocols.ss7.tcap.api.TCAPStack;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPStack {

    /**
     * Returns the name of this stack
     *
     * @return
     */
    String getName();

    CAPProvider getCAPProvider();

    void stop();

    void start() throws Exception;

    TCAPStack getTCAPStack();

}
