package org.restcomm.protocols.ss7.m3ua.message.ssnm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.ConcernedDPC;
import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * <p>
 * The Signalling Congestion SCON message can be sent from an SGP to all concerned ASPs to indicate that an SG has determined
 * that there is congestion in the SS7 network to one or more destinations, or to an ASP in response to a DATA or DAUD message,
 * as appropriate. For some MTP protocol variants (e.g., ANSI MTP) the SCON message may be sent when the SS7 congestion level
 * changes. The SCON message MAY also be sent from the M3UA layer of an ASP to an M3UA peer, indicating that the congestion
 * level of the M3UA layer or the ASP has changed.
 * </p>
 *
 * @author amit bhayani
 *
 */
public interface SignallingCongestion extends M3UAMessage {

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    RoutingContext getRoutingContexts();

    void setRoutingContexts(RoutingContext routingContexts);

    AffectedPointCode getAffectedPointCodes();

    void setAffectedPointCodes(AffectedPointCode affectedPointCodes);

    ConcernedDPC getConcernedDPC();

    void setConcernedDPC(ConcernedDPC concernedDPC);

    CongestedIndication getCongestedIndication();

    void setCongestedIndication(CongestedIndication congestedIndication);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);

}
