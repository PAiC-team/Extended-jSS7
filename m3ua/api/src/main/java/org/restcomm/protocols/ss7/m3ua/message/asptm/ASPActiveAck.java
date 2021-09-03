package org.restcomm.protocols.ss7.m3ua.message.asptm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

/**
 * The ASP Active Ack message is used to acknowledge an ASP Active message received from a remote M3UA peer.
 *
 * @author amit bhayani
 *
 */
public interface ASPActiveAck extends M3UAMessage {

    /**
     * The Traffic Mode Type parameter identifies the traffic mode of operation of the ASP within an AS. Optional
     *
     * @return
     */
    TrafficModeType getTrafficModeType();

    void setTrafficModeType(TrafficModeType trafficModeType);

    /**
     * The optional Routing Context parameter contains (a list of) integers indexing the Application Server traffic that the
     * sending ASP is configured/registered to receive.
     *
     * @return
     */
    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);
}
