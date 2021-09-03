package org.restcomm.protocols.ss7.mtp;

import java.io.IOException;

import org.mobicents.protocols.stream.api.Stream;

/**
 * @author baranowb
 * @author kulikov
 */
public interface Mtp1 extends Stream {
    // FIXME: Oleg what's that?
    /**
     * Gets the code of this channel.
     *
     * @return the code of this channel.
     */
    int getCode();

    /**
     * Set MTP2 layer serving this MTP1
     *
     * @param link
     */
    void setLink(Object link);

    /**
     * Get MTP2 layer serving this MTP1
     *
     * @return
     */
    Object getLink();

    /**
     * Fetches implementation dependent IO Buffer size which should be used
     *
     * @return integer number, Mtp2 implementation should assign buffers of this size to interact with Mtp1
     */
    int getIOBufferSize();

    /**
     * Reads up to buffer.length bytes from layer 1.
     *
     * @param buffer reader buffer
     * @return the number of actually read bytes.
     */
    int read(byte[] buffer) throws IOException;

    /**
     * Writes data to layer 1.
     *
     * @param buffer the buffer containing data to write.
     * @param bytesToWrite
     */
    void write(byte[] buffer, int bytesToWrite) throws IOException;

    /**
     * Open message transfer part layer 1.
     */
    void open() throws IOException;

    /**
     * Close message transfer part layer 1.
     */
    void close();

}
