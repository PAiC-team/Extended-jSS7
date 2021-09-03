package org.restcomm.protocols.ss7.tools.traceparser;


/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceReaderDriver {

    /**
     * Add TraceListener
     *
     * @param listener
     */
    void addTraceListener(TraceReaderListener listener);

    /**
     * Remove TraceListener
     *
     * @param listener
     */
    void removeTraceListener(TraceReaderListener listener);

    /**
     * Open a trace file and start parsing
     *
     */
    void startTraceFile() throws TraceReaderException;

    /**
     * Stop trace file parsing
     *
     */
    void stop();

}
