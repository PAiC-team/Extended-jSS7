package org.restcomm.protocols.ss7.m3ua.impl.message.rkm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.rkm.RegistrationResponse;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RegistrationResult;

/**
 *
 * @author amit bhayani
 *
 */
public class RegistrationResponseImpl extends M3UAMessageImpl implements RegistrationResponse {

    public RegistrationResponseImpl() {
        super(MessageClass.ROUTING_KEY_MANAGEMENT, MessageType.REG_RESPONSE, MessageType.S_REG_RESPONSE);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        ((ParameterImpl) parameters.get(Parameter.Registration_Result)).write(byteBuf);
    }

    public RegistrationResult getRegistrationResult() {
        return (RegistrationResult) parameters.get(ParameterImpl.Registration_Result);
    }

    public void setRegistrationResult(RegistrationResult registrationResult) {
        parameters.put(ParameterImpl.Registration_Result, registrationResult);
    }

}
