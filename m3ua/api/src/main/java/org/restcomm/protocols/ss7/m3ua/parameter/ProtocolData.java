package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * Protocl data parameter.
 *
 * @author kulikov
 */
public interface ProtocolData extends Parameter {
    /**
     * Gets origination point code.
     *
     * @return point code value in decimal format.
     */
    int getOpc();

    /**
     * Gets destination point code
     *
     * @return point code value in decimal format
     */
    int getDpc();

    /**
     * Gets the service indicator.
     *
     * @return service indicator value.
     */
    int getSI();

    /**
     * Gets the network indicator.
     *
     * @return the network indicator value.
     */
    int getNI();

    /**
     * Gets the message priority.
     *
     * @return message priority value.
     */
    int getMP();

    /**
     * Gets the signaling link selection.
     *
     * @return the signaling link selection value
     */
    int getSLS();

    /**
     * Gets the payload of message.
     *
     * @return binary message.
     */
    byte[] getData();
}
