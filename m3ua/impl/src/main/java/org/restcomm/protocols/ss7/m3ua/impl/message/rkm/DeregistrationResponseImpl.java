package org.restcomm.protocols.ss7.m3ua.impl.message.rkm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.rkm.DeregistrationResponse;
import org.restcomm.protocols.ss7.m3ua.parameter.DeregistrationResult;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class DeregistrationResponseImpl extends M3UAMessageImpl implements DeregistrationResponse {

    public DeregistrationResponseImpl() {
        super(MessageClass.ROUTING_KEY_MANAGEMENT, MessageType.DEREG_RESPONSE, MessageType.S_DEREG_RESPONSE);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        ((ParameterImpl) parameters.get(Parameter.Deregistration_Result)).write(byteBuf);
    }

    public DeregistrationResult getDeregistrationResult() {
        return (DeregistrationResult) parameters.get(Parameter.Deregistration_Result);
    }

    public void setDeregistrationResult(DeregistrationResult deregistrationResult) {
        parameters.put(Parameter.Deregistration_Result, deregistrationResult);

    }

}
