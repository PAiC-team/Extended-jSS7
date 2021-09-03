package org.restcomm.protocols.ss7.m3ua.message.ssnm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * <p>
 * Destination Unavailable (DUNA) message is sent from an SGP in an SG to all concerned ASPs to indicate that the SG has
 * determined that one or more SS7 destinations are unreachable.
 * </p>
 * <p>
 * look at section 3.4.1 in RFC 4666
 * </p>
 *
 * @author amit bhayani
 *
 */
public interface DestinationUnavailable extends M3UAMessage {

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    RoutingContext getRoutingContexts();

    void setRoutingContexts(RoutingContext routingContexts);

    AffectedPointCode getAffectedPointCodes();

    void setAffectedPointCodes(AffectedPointCode affectedPointCodes);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);

}
