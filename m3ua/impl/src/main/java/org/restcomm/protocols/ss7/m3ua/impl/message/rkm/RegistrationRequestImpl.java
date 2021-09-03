package org.restcomm.protocols.ss7.m3ua.impl.message.rkm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.rkm.RegistrationRequest;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingKey;

/**
 *
 * @author amit bhayani
 *
 */
public class RegistrationRequestImpl extends M3UAMessageImpl implements RegistrationRequest {

    public RegistrationRequestImpl() {
        super(MessageClass.ROUTING_KEY_MANAGEMENT, MessageType.REG_REQUEST, MessageType.S_REG_REQUEST);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        if (parameters.containsKey(Parameter.Routing_Key)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Key)).write(byteBuf);
        }
    }

    public RoutingKey getRoutingKey() {
        return (RoutingKey) parameters.get(Parameter.Routing_Key);
    }

    public void setRoutingKey(RoutingKey routingKey) {
        parameters.put(Parameter.Routing_Key, routingKey);
    }

}
