package org.restcomm.protocols.ss7.m3ua.message;

/**
 * Represents M3UA protocol data unit.
 *
 * @author kulikov
 */
public interface M3UAMessage {
    /**
     * Gets the message class parameter.
     *
     * @return the identifier of message class.
     */
    int getMessageClass();

    /**
     * Gets the type of this message.
     *
     * @return the identifier of message type.
     */
    int getMessageType();
}
