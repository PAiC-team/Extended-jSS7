package org.restcomm.protocols.ss7.m3ua.impl.message.aspsm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.aspsm.ASPUpAck;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class ASPUpAckImpl extends M3UAMessageImpl implements ASPUpAck {

    public ASPUpAckImpl() {
        super(MessageClass.ASP_STATE_MAINTENANCE, MessageType.ASP_UP_ACK, MessageType.S_ASP_UP_ACK);
    }

    public ASPIdentifier getASPIdentifier() {
        return (ASPIdentifier) parameters.get(Parameter.ASP_Identifier);
    }

    public void setASPIdentifier(ASPIdentifier aspIdentifier) {
        if (aspIdentifier != null) {
            parameters.put(Parameter.ASP_Identifier, aspIdentifier);
        }
    }

    public InfoString getInfoString() {
        return (InfoString) parameters.get(Parameter.INFO_String);
    }

    public void setInfoString(InfoString infoString) {
        if (infoString != null) {
            parameters.put(Parameter.INFO_String, infoString);
        }
    }

    @Override
    protected void encodeParams(ByteBuf buf) {
        if (parameters.containsKey(Parameter.ASP_Identifier)) {
            ((ParameterImpl) parameters.get(Parameter.ASP_Identifier)).write(buf);
        }

        if (parameters.containsKey(Parameter.INFO_String)) {
            ((ParameterImpl) parameters.get(Parameter.INFO_String)).write(buf);
        }
    }

}
