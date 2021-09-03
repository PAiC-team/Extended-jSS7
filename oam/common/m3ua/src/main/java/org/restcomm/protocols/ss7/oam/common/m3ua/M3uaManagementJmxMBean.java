
package org.restcomm.protocols.ss7.oam.common.m3ua;

import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.M3UAManagement;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public interface M3uaManagementJmxMBean extends M3UAManagement {

    As createAppServer(String asName, String functionality, String exchangeType, String ipspType, String rc,
            int trafficMode, int minAspActive, String na) throws Exception;

}
