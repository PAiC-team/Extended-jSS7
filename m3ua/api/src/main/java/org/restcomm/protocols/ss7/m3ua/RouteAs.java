
package org.restcomm.protocols.ss7.m3ua;

import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

/**
 * @author abhayani
 *
 */
public interface RouteAs {

    As[] getAsArray();

    TrafficModeType getTrafficModeType();

    void setTrafficModeType(TrafficModeType trafficModeType);
}
