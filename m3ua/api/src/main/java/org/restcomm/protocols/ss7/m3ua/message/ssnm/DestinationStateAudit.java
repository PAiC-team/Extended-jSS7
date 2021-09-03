package org.restcomm.protocols.ss7.m3ua.message.ssnm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * Destination State Audit (DAUD) MAY be sent from the ASP to the SGP to audit the availability/congestion state of SS7 routes
 * from the SG to one or more affected destinations.
 *
 * @author amit bhayani
 *
 */
public interface DestinationStateAudit extends M3UAMessage {

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    RoutingContext getRoutingContexts();

    void setRoutingContexts(RoutingContext routingContexts);

    AffectedPointCode getAffectedPointCodes();

    void setAffectedPointCodes(AffectedPointCode affectedPointCodes);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);

}
