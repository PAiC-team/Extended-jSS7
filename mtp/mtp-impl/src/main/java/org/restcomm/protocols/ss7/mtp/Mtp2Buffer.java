package org.restcomm.protocols.ss7.mtp;

/**
 * @author baranowb
 *
 */
public class Mtp2Buffer {
    /**
     * data in this frame.
     */
    byte[] frame = new byte[272 + 7]; // +7 - 272 is max SIF part len,
    /**
     * length of actual data fram to be transmited.
     */
    int len = 0;// len pointer for this buffer, indicates how much data is there really.

    int offset = 0; // offset from beggining of buffer.
    // public boolean isFree()
}
