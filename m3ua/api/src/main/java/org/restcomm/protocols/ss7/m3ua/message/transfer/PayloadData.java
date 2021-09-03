package org.restcomm.protocols.ss7.m3ua.message.transfer;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.CorrelationId;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * @author amit bhayani
 * @author kulikov
 */
public interface PayloadData extends M3UAMessage {

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    ProtocolData getData();

    void setData(ProtocolData protocolData);

    CorrelationId getCorrelationId();

    void setCorrelationId(CorrelationId correlationId);

}
