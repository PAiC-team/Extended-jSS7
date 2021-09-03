
package org.restcomm.protocols.ss7.m3ua.message;

import io.netty.buffer.ByteBuf;

/**
 * Constructs M3UA message.
 *
 * @author amit bhayani
 * @author kulikov
 */
public interface MessageFactory {
    /**
     * Constructs M3UAMessage.
     *
     * @param messageClass the class of the message
     * @param messageType type of the message
     * @return M3UA message.
     */
    M3UAMessage createMessage(int messageClass, int messageType);

    M3UAMessage createMessage(ByteBuf byteBuf);

}
