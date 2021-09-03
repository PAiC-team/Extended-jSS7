
package org.restcomm.protocols.ss7.m3ua.impl.message;

import io.netty.buffer.ByteBuf;
import javolution.text.TextBuilder;
import javolution.util.FastMap;

import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 * @author amit bhayani
 * @author kulikov
 * @author sergey vetyutnev
 */
public abstract class M3UAMessageImpl implements M3UAMessage {

    // header part
    private int messageClass;
    private int messageType;

    private String message;
    protected FastMap<Short, Parameter> parameters = new FastMap<Short, Parameter>();

    private ParameterFactoryImpl factory = new ParameterFactoryImpl();

    int initialPosition = 0;

    public M3UAMessageImpl(String message) {
        this.message = message;
    }

    protected M3UAMessageImpl(int messageClass, int messageType, String message) {
        this(message);
        this.messageClass = messageClass;
        this.messageType = messageType;
    }

    protected abstract void encodeParams(ByteBuf buffer);

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeByte(1);
        byteBuf.writeByte(0);
        byteBuf.writeByte(messageClass);
        byteBuf.writeByte(messageType);

        byteBuf.markWriterIndex();
        byteBuf.writeInt(8);
        int currIndex=byteBuf.writerIndex();

        encodeParams(byteBuf);

        int newIndex=byteBuf.writerIndex();
        byteBuf.resetWriterIndex();
        byteBuf.writeInt(newIndex-currIndex + 8);
        byteBuf.writerIndex(newIndex);
    }

    protected void decode(ByteBuf data) {
        while (data.readableBytes() >= 4) {
            short tag = (short) ((data.readUnsignedByte() << 8) | (data.readUnsignedByte()));
            short len = (short) ((data.readUnsignedByte() << 8) | (data.readUnsignedByte()));

            if (data.readableBytes() < len - 4) {
                return;
            }

            byte[] value = new byte[len - 4];
            data.readBytes(value);
            parameters.put(tag, factory.createParameter(tag, value));

            // The Parameter Length does not include any padding octets. We have
            // to consider padding here
            int padding = 4 - (len % 4);
            if (padding < 4) {
                if (data.readableBytes() < padding)
                    return;
                else
                    data.skipBytes(padding);
            }
        }
    }

    public int getMessageClass() {
        return messageClass;
    }

    public int getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        TextBuilder tb = new TextBuilder();
        tb.append(this.message).append(" Params(");
        for (FastMap.Entry<Short, Parameter> e = parameters.head(), end = parameters.tail(); (e = e.getNext()) != end;) {
            Parameter value = e.getValue();
            tb.append(value.toString());
            tb.append(", ");
        }
        tb.append(")");
        return tb.toString();
    }
}
