
package org.restcomm.protocols.ss7.tools.simulator;

import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostInterface;

/**
*
* @author sergey vetyutnev
*
*/
public interface TesterHostFactoryInterface {

    TesterHostInterface createTesterHost(String appName, String persistDir);

}
