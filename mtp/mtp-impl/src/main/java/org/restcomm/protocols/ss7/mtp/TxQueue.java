package org.restcomm.protocols.ss7.mtp;

import java.util.ArrayList;

/**
 *
 * @author kulikov
 */
public class TxQueue {
    private ArrayList<byte[]> queue = new ArrayList();

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void offer(byte[] frame) {
        queue.add(frame);
    }

    public byte[] peak() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

}
