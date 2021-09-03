package org.restcomm.protocols.ss7.m3ua.message.mgmt;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;

/**
 * The Notify message used to provide an autonomous indication of M3UA events to an M3UA peer.
 *
 * @author amit bhayani
 *
 */
public interface Notify extends M3UAMessage {

    Status getStatus();

    void setStatus(Status status);

    ASPIdentifier getASPIdentifier();

    void setASPIdentifier(ASPIdentifier aspIdentifier);

    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);
}
