package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.restcomm.protocols.ss7.m3ua.parameter.DeregistrationResult;
import org.restcomm.protocols.ss7.m3ua.parameter.DeregistrationStatus;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 *
 * @author amit bhayani
 *
 */
public class DeregistrationResultImpl extends ParameterImpl implements DeregistrationResult {

    private RoutingContext routingContext;
    private DeregistrationStatus status;
    private byte[] value;

    private ByteBuf buf = Unpooled.buffer(16);

    public DeregistrationResultImpl(RoutingContext routingContext, DeregistrationStatus status) {
        this.tag = Parameter.Deregistration_Result;
        this.routingContext = routingContext;
        this.status = status;

        this.encode();
    }

    public DeregistrationResultImpl(byte[] data) {
        this.tag = Parameter.Deregistration_Result;
        int pos = 0;

        while (pos < data.length) {
            short tag = (short) ((data[pos] & 0xff) << 8 | (data[pos + 1] & 0xff));
            short len = (short) ((data[pos + 2] & 0xff) << 8 | (data[pos + 3] & 0xff));

            byte[] value = new byte[len - 4];

            System.arraycopy(data, pos + 4, value, 0, value.length);
            pos += len;
            // parameters.put(tag, factory.createParameter(tag, value));
            switch (tag) {
                case ParameterImpl.Routing_Context:
                    this.routingContext = new RoutingContextImpl(value);
                    break;

                case ParameterImpl.Deregistration_Status:
                    this.status = new DeregistrationStatusImpl(value);
                    break;

            }

            // The Parameter Length does not include any padding octets. We have
            // to consider padding here
            pos += (pos % 4);
        }// end of while
    }

    private void encode() {
        ((RoutingContextImpl) this.routingContext).write(buf);
        ((DeregistrationStatusImpl) this.status).write(buf);

        int length = buf.readableBytes();
        value = new byte[length];
        buf.getBytes(buf.readerIndex(), value);
    }

    @Override
    protected byte[] getValue() {
        return this.value;
    }

    public DeregistrationStatus getDeregistrationStatus() {
        return this.status;
    }

    public RoutingContext getRoutingContext() {
        return this.routingContext;
    }

}
