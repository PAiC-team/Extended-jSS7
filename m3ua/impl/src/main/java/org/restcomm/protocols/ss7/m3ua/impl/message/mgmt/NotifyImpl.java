package org.restcomm.protocols.ss7.m3ua.impl.message.mgmt;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Notify;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;

/**
 *
 * @author amit bhayani
 *
 */
public class NotifyImpl extends M3UAMessageImpl implements Notify {

    public NotifyImpl() {
        super(MessageClass.MANAGEMENT, MessageType.NOTIFY, MessageType.S_NOTIFY);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        ((ParameterImpl) parameters.get(Parameter.Status)).write(byteBuf);

        if (parameters.containsKey(Parameter.ASP_Identifier)) {
            ((ParameterImpl) parameters.get(Parameter.ASP_Identifier)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.Routing_Context)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Context)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.INFO_String)) {
            ((ParameterImpl) parameters.get(Parameter.INFO_String)).write(byteBuf);
        }
    }

    public ASPIdentifier getASPIdentifier() {
        return ((ASPIdentifier) parameters.get(Parameter.ASP_Identifier));
    }

    public InfoString getInfoString() {
        return ((InfoString) parameters.get(Parameter.INFO_String));
    }

    public RoutingContext getRoutingContext() {
        return ((RoutingContext) parameters.get(Parameter.Routing_Context));
    }

    public Status getStatus() {
        return ((Status) parameters.get(Parameter.Status));
    }

    public void setASPIdentifier(ASPIdentifier aspId) {
        if (aspId != null) {
            parameters.put(Parameter.ASP_Identifier, aspId);
        }
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

    public void setStatus(Status status) {
        parameters.put(Parameter.Status, status);
    }
}
