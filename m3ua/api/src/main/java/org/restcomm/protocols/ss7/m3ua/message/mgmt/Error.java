package org.restcomm.protocols.ss7.m3ua.message.mgmt;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.DiagnosticInfo;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * The Error message is used to notify a peer of an error event associated with an incoming message. For example, the message
 * type might be unexpected given the current state, or a parameter value might be invalid. Error messages MUST NOT be generated
 * in response to other Error messages.
 *
 * @author amit bhayani
 *
 */
public interface Error extends M3UAMessage {

    ErrorCode getErrorCode();

    void setErrorCode(ErrorCode errorCode);

    RoutingContext getRoutingContext();

    void setRoutingContext(RoutingContext routingContext);

    NetworkAppearance getNetworkAppearance();

    void setNetworkAppearance(NetworkAppearance networkAppearance);

    AffectedPointCode getAffectedPointCode();

    void setAffectedPointCode(AffectedPointCode affectedPointCode);

    DiagnosticInfo getDiagnosticInfo();

    void setDiagnosticInfo(DiagnosticInfo diagnosticInfo);

}
