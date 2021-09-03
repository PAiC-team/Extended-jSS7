
package org.restcomm.protocols.ss7.tools.simulator.level1;

import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;

/**
*
* @author sergey vetyutnev
*
*/
public interface DialogicMtp3UserPartProxyInterface {

    void setSourceModuleId(int sourceModuleId);

    void setDestinationModuleId(int destinationModuleId);

    void setDeliveryMessageThreadCount(int deliveryMessageThreadCount) throws Exception;

    void start() throws Exception;

    void stop() throws Exception;

    Mtp3UserPart getMtp3UserPart();

}
