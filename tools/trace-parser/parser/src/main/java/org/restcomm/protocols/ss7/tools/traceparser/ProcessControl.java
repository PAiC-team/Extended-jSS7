package org.restcomm.protocols.ss7.tools.traceparser;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface ProcessControl {

    boolean isFinished();

    String getErrorMessage();

    void interrupt();

    boolean checkNeedInterrupt();

    int getMsgCount();

}
