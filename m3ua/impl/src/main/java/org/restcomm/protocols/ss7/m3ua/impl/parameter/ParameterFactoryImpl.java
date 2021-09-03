
package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.ConcernedDPC;
import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication;
import org.restcomm.protocols.ss7.m3ua.parameter.CorrelationId;
import org.restcomm.protocols.ss7.m3ua.parameter.DeregistrationResult;
import org.restcomm.protocols.ss7.m3ua.parameter.DeregistrationStatus;
import org.restcomm.protocols.ss7.m3ua.parameter.DestinationPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.DiagnosticInfo;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.HeartbeatData;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.LocalRKIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.OPCList;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.m3ua.parameter.RegistrationResult;
import org.restcomm.protocols.ss7.m3ua.parameter.RegistrationStatus;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingKey;
import org.restcomm.protocols.ss7.m3ua.parameter.ServiceIndicators;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;
import org.restcomm.protocols.ss7.m3ua.parameter.UserCause;
import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication.CongestionLevel;

/**
 *
 * @author kulikov
 */
public class ParameterFactoryImpl implements ParameterFactory {

    public ProtocolData createProtocolData(int opc, int dpc, int si, int ni, int messagePriority, int sls, byte[] data) {
        return new ProtocolDataImpl(opc, dpc, si, ni, messagePriority, sls, data);
    }

    public ProtocolData createProtocolData(byte[] payloadData) {
        ProtocolDataImpl protocolData = new ProtocolDataImpl(payloadData);
        return protocolData;
    }

    public NetworkAppearance createNetworkAppearance(long networkAppearance) {
        return new NetworkAppearanceImpl(networkAppearance);
    }

    public RoutingContext createRoutingContext(long[] routingContext) {
        return new RoutingContextImpl(routingContext);
    }

    public CorrelationId createCorrelationId(long correlationId) {
        return new CorrelationIdImpl(correlationId);
    }

    public AffectedPointCode createAffectedPointCode(int[] pc, short[] mask) {
        return new AffectedPointCodeImpl(pc, mask);
    }

    public DestinationPointCode createDestinationPointCode(int pc, short mask) {
        return new DestinationPointCodeImpl(pc, mask);
    }

    public InfoString createInfoString(String string) {
        return new InfoStringImpl(string);
    }

    public ConcernedDPC createConcernedDPC(int pointCode) {
        return new ConcernedDPCImpl(pointCode);
    }

    public CongestedIndication createCongestedIndication(CongestionLevel congestionLevel) {
        return new CongestedIndicationImpl(congestionLevel);
    }

    public UserCause createUserCause(int user, int cause) {
        return new UserCauseImpl(user, cause);
    }

    public ASPIdentifier createASPIdentifier(long aspId) {
        return new ASPIdentifierImpl(aspId);
    }

    public LocalRKIdentifier createLocalRKIdentifier(long localRKIdentifier) {
        return new LocalRKIdentifierImpl(localRKIdentifier);
    }

    public OPCList createOPCList(int[] pc, short[] mask) {
        return new OPCListImpl(pc, mask);
    }

    public ServiceIndicators createServiceIndicators(short[] serviceIndicators) {
        return new ServiceIndicatorsImpl(serviceIndicators);
    }

    public TrafficModeType createTrafficModeType(int trafficModeType) {
        return new TrafficModeTypeImpl(trafficModeType);
    }

    public RegistrationStatus createRegistrationStatus(int registrationStatus) {
        return new RegistrationStatusImpl(registrationStatus);
    }

    public DiagnosticInfo createDiagnosticInfo(String diagnosticInfo) {
        return new DiagnosticInfoImpl(diagnosticInfo);
    }

    public RoutingKey createRoutingKey(LocalRKIdentifier localRkId, RoutingContext routingContext, TrafficModeType trafficModeType,
            NetworkAppearance networkAppearance, DestinationPointCode[] dpc, ServiceIndicators[] serviceIndicators, OPCList[] opcList) {
        return new RoutingKeyImpl(localRkId, routingContext, trafficModeType, networkAppearance, dpc, serviceIndicators, opcList);
    }

    public RegistrationResult createRegistrationResult(LocalRKIdentifier localRkId, RegistrationStatus registrationStatus, RoutingContext routingContext) {
        return new RegistrationResultImpl(localRkId, registrationStatus, routingContext);
    }

    public DeregistrationStatus createDeregistrationStatus(int deregistrationStatus) {
        return new DeregistrationStatusImpl(deregistrationStatus);
    }

    public DeregistrationResult createDeregistrationResult(RoutingContext routingContext, DeregistrationStatus deregistrationStatus) {
        return new DeregistrationResultImpl(routingContext, deregistrationStatus);
    }

    public ErrorCode createErrorCode(int errorCode) {
        return new ErrorCodeImpl(errorCode);
    }

    public Status createStatus(int type, int info) {
        return new StatusImpl(type, info);
    }

    public HeartbeatData createHeartbeatData(byte[] data) {
        return new HeartbeatDataImpl(data);
    }

    public Parameter createParameter(int tag, byte[] value) {
        ParameterImpl parameter;
        switch (tag) {
            case ParameterImpl.Protocol_Data:
                parameter = new ProtocolDataImpl(value);
                break;
            case ParameterImpl.Traffic_Mode_Type:
                parameter = new TrafficModeTypeImpl(value);
                break;
            case ParameterImpl.Network_Appearance:
                parameter = new NetworkAppearanceImpl(value);
                break;
            case ParameterImpl.Routing_Context:
                parameter = new RoutingContextImpl(value);
                break;
            case ParameterImpl.Correlation_ID:
                parameter = new CorrelationIdImpl(value);
                break;
            case ParameterImpl.Affected_Point_Code:
                parameter = new AffectedPointCodeImpl(value);
                break;
            case ParameterImpl.Originating_Point_Code_List:
                parameter = new OPCListImpl(value);
                break;
            case ParameterImpl.Destination_Point_Code:
                parameter = new DestinationPointCodeImpl(value);
                break;
            case ParameterImpl.INFO_String:
                parameter = new InfoStringImpl(value);
                break;
            case ParameterImpl.Concerned_Destination:
                parameter = new ConcernedDPCImpl(value);
                break;
            case ParameterImpl.Congestion_Indications:
                parameter = new CongestedIndicationImpl(value);
                break;
            case ParameterImpl.User_Cause:
                parameter = new UserCauseImpl(value);
                break;
            case ParameterImpl.ASP_Identifier:
                parameter = new ASPIdentifierImpl(value);
                break;
            case ParameterImpl.Local_Routing_Key_Identifier:
                parameter = new LocalRKIdentifierImpl(value);
                break;
            case ParameterImpl.Service_Indicators:
                parameter = new ServiceIndicatorsImpl(value);
                break;
            case ParameterImpl.Routing_Key:
                parameter = new RoutingKeyImpl(value);
                break;
            case ParameterImpl.Registration_Status:
                parameter = new RegistrationStatusImpl(value);
                break;
            case ParameterImpl.Registration_Result:
                parameter = new RegistrationResultImpl(value);
                break;
            case ParameterImpl.Deregistration_Status:
                parameter = new DeregistrationStatusImpl(value);
                break;
            case ParameterImpl.Deregistration_Result:
                parameter = new DeregistrationResultImpl(value);
                break;
            case ParameterImpl.Diagnostic_Information:
                parameter = new DiagnosticInfoImpl(value);
                break;
            case ParameterImpl.Error_Code:
                parameter = new ErrorCodeImpl(value);
                break;
            case ParameterImpl.Status:
                parameter = new StatusImpl(value);
                break;
            case ParameterImpl.Heartbeat_Data:
                parameter = new HeartbeatDataImpl(value);
                break;
            default:
                parameter = new UnknownParameterImpl(tag, value.length, value);
                break;
        }
        return parameter;
    }

}
