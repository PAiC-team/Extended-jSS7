package org.restcomm.protocols.ss7.tools.traceparser;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class TraceReaderDriverBase implements TraceReaderDriver {

    protected Logger loger = Logger.getLogger(TraceReaderDriverBase.class);

    protected ArrayList<TraceReaderListener> listeners = new ArrayList<TraceReaderListener>();
    protected boolean isStarted = false;

    protected ProcessControl processControl;
    protected String fileName;

    public TraceReaderDriverBase(ProcessControl processControl, String fileName) {
        this.processControl = processControl;
        this.fileName = fileName;
    }

    public void addTraceListener(TraceReaderListener listener) {
        this.listeners.add(listener);
    }

    public void removeTraceListener(TraceReaderListener listener) {
        this.listeners.remove(listener);
    }

    public void stop() {
        this.isStarted = false;
    }

}
