package org.restcomm.protocols.ss7.m3ua.message.asptm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * The ASP Inactive Ack message is used to acknowledge an ASP Inactive message received from a remote M3UA peer.
 *
 * @author amit bhayani
 *
 */
public interface ASPInactiveAck extends M3UAMessage {

    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);
}
