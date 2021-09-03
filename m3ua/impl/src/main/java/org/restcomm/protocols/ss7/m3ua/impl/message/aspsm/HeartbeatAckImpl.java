package org.restcomm.protocols.ss7.m3ua.impl.message.aspsm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.aspsm.HeartbeatAck;
import org.restcomm.protocols.ss7.m3ua.parameter.HeartbeatData;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class HeartbeatAckImpl extends M3UAMessageImpl implements HeartbeatAck {

    public HeartbeatAckImpl() {
        super(MessageClass.ASP_STATE_MAINTENANCE, MessageType.HEARTBEAT_ACK, MessageType.S_HEARTBEAT_ACK);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        if (parameters.containsKey(Parameter.Heartbeat_Data)) {
            ((ParameterImpl) parameters.get(Parameter.Heartbeat_Data)).write(byteBuf);
        }
    }

    public HeartbeatData getHeartbeatData() {
        return (HeartbeatData) parameters.get(Parameter.Heartbeat_Data);
    }

    public void setHeartbeatData(HeartbeatData heartbeatData) {
        if (heartbeatData != null) {
            parameters.put(Parameter.Heartbeat_Data, heartbeatData);
        }
    }

}
