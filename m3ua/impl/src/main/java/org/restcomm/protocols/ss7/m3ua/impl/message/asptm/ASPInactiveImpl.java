package org.restcomm.protocols.ss7.m3ua.impl.message.asptm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.asptm.ASPInactive;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

public class ASPInactiveImpl extends M3UAMessageImpl implements ASPInactive {

    public ASPInactiveImpl() {
        super(MessageClass.ASP_TRAFFIC_MAINTENANCE, MessageType.ASP_INACTIVE, MessageType.S_ASP_INACTIVE);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {

        if (parameters.containsKey(Parameter.Routing_Context)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Context)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.INFO_String)) {
            ((ParameterImpl) parameters.get(Parameter.INFO_String)).write(byteBuf);
        }
    }

    public InfoString getInfoString() {
        return (InfoString) parameters.get(Parameter.INFO_String);
    }

    public RoutingContext getRoutingContext() {
        return (RoutingContext) parameters.get(Parameter.Routing_Context);
    }

    public void setInfoString(InfoString infoString) {
        if (infoString != null) {
            parameters.put(Parameter.INFO_String, infoString);
        }

    }

    public void setRoutingContext(RoutingContext routingContext) {
        if (routingContext != null) {
            parameters.put(Parameter.Routing_Context, routingContext);
        }

    }
}
