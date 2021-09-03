package org.restcomm.protocols.ss7.m3ua.impl.message.transfer;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.CorrelationIdImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ProtocolDataImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;
import org.restcomm.protocols.ss7.m3ua.parameter.CorrelationId;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 * @author amit bhayani
 * @author kulikov
 */
public class PayloadDataImpl extends M3UAMessageImpl implements PayloadData {

    public PayloadDataImpl() {
        super(MessageClass.TRANSFER_MESSAGES, MessageType.PAYLOAD, MessageType.S_PAYLOAD);
    }

    public NetworkAppearance getNetworkAppearance() {
        return (NetworkAppearance) parameters.get(Parameter.Network_Appearance);
    }

    public void setNetworkAppearance(NetworkAppearance networkAppearance) {
        if (networkAppearance != null) {
            parameters.put(Parameter.Network_Appearance, networkAppearance);
        }
    }

    public RoutingContext getRoutingContext() {
        return (RoutingContext) parameters.get(Parameter.Routing_Context);
    }

    public void setRoutingContext(RoutingContext routingContext) {
        if (routingContext != null) {
            parameters.put(Parameter.Routing_Context, routingContext);
        }
    }

    public ProtocolData getData() {
        return (ProtocolDataImpl) parameters.get(Parameter.Protocol_Data);
    }

    public void setData(ProtocolData protocolData) {
        parameters.put(Parameter.Protocol_Data, protocolData);
    }

    public CorrelationId getCorrelationId() {
        return (CorrelationIdImpl) parameters.get(Parameter.Correlation_ID);
    }

    public void setCorrelationId(CorrelationId correlationId) {
        if (correlationId != null) {
            parameters.put(Parameter.Correlation_ID, correlationId);
        }
    }

    @Override
    public String toString() {
        return "TransferMessage: " + parameters;
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        if (parameters.containsKey(Parameter.Network_Appearance)) {
            ((ParameterImpl) parameters.get(Parameter.Network_Appearance)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.Routing_Context)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Context)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.Protocol_Data)) {
            ((ParameterImpl) parameters.get(Parameter.Protocol_Data)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.Correlation_ID)) {
            ((ParameterImpl) parameters.get(Parameter.Correlation_ID)).write(byteBuf);
        }
    }
}
