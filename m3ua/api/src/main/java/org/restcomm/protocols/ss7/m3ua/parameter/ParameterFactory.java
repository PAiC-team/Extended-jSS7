
package org.restcomm.protocols.ss7.m3ua.parameter;

import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication.CongestionLevel;

/**
 * Constructs parameters.
 *
 * @author amit bhayani
 * @author kulikov
 */
public interface ParameterFactory {
    /**
     * Constructs Protocol Data parameter.
     *
     * @param opc the origination point code
     * @param dpc the destination point code
     * @param si the service indicator
     * @param ni the network indicator
     * @param mp the message priority indicator
     * @param sls the signaling link selection
     * @param data message payload
     * @return Protocol data parameter
     */
    ProtocolData createProtocolData(int opc, int dpc, int si, int ni, int mp, int sls, byte[] data);

    ProtocolData createProtocolData(byte[] payloadData);

    NetworkAppearance createNetworkAppearance(long networkAppearance);

    RoutingContext createRoutingContext(long[] routingContext);

    CorrelationId createCorrelationId(long correlationId);

    AffectedPointCode createAffectedPointCode(int[] affectedPointCode, short[] mask);

    DestinationPointCode createDestinationPointCode(int dpc, short mask);

    InfoString createInfoString(String infoString);

    ConcernedDPC createConcernedDPC(int pointCode);

    CongestedIndication createCongestedIndication(CongestionLevel level);

    UserCause createUserCause(int user, int cause);

    ASPIdentifier createASPIdentifier(long aspId);

    LocalRKIdentifier createLocalRKIdentifier(long id);

    OPCList createOPCList(int[] pc, short[] mask);

    ServiceIndicators createServiceIndicators(short[] inds);

    TrafficModeType createTrafficModeType(int mode);

    RegistrationStatus createRegistrationStatus(int status);

    DiagnosticInfo createDiagnosticInfo(String info);

    RoutingKey createRoutingKey(LocalRKIdentifier localRKIdentifier, RoutingContext routingContext, TrafficModeType trafficModeType,
            NetworkAppearance networkAppearance, DestinationPointCode[] dpc, ServiceIndicators[] serviceIndicators, OPCList[] opcList);

    RegistrationResult createRegistrationResult(LocalRKIdentifier localRKIdentifier, RegistrationStatus registrationStatus, RoutingContext routingContext);

    DeregistrationStatus createDeregistrationStatus(int status);

    DeregistrationResult createDeregistrationResult(RoutingContext routingContext, DeregistrationStatus deregistrationStatus);

    ErrorCode createErrorCode(int errorCode);

    Status createStatus(int statusType, int statusInfo);

    HeartbeatData createHeartbeatData(byte[] heartbeatData);

}
