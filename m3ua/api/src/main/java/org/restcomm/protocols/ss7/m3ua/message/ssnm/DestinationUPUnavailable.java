package org.restcomm.protocols.ss7.m3ua.message.ssnm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.UserCause;

/**
 * Destination User Part Unavailable (DUPU) message is used by an SGP to inform concerned ASPs that a remote peer MTP3-User Part
 * (e.g., ISUP or SCCP) at an SS7 node is unavailable.
 *
 * @author amit bhayani
 *
 */
public interface DestinationUPUnavailable extends M3UAMessage {

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    AffectedPointCode getAffectedPointCode();

    void setAffectedPointCode(AffectedPointCode affectedPointCode);

    UserCause getUserCause();

    void setUserCause(UserCause userCause);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);

}