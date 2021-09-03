package org.restcomm.protocols.ss7.tools.traceparser;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceReaderListener {

    /**
     * Deliver the ss7 message data. Data is a raw data that includes BSN+Bib + FSN+Fib + LI + SIO + SIF
     *
     * @param data
     */
    void ss7Message(int si, int ni, int priority, int opc, int dpc, int sls, byte[] data) throws TraceReaderException;

}
