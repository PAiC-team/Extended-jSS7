
package org.restcomm.protocols.ss7.tools.simulator;

import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostImpl;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostInterface;

/**
*
* @author sergey vetyutnev
*
*/
public class TesterHostFactoryImpl implements TesterHostFactoryInterface {

    @Override
    public TesterHostInterface createTesterHost(String appName, String persistDir) {
        return new TesterHostImpl(appName, persistDir);
    }

}
