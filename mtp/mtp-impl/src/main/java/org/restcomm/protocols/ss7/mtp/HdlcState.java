package org.restcomm.protocols.ss7.mtp;

/**
 * Represents HDLC frame.
 *
 * @author kulikov
 */
public class HdlcState {
    public int state; /* What state we are in */
    public int data; /* Our current data queue */
    public int bits; /* Number of bits in our data queue */
    public int ones; /* Number of ones */
}
