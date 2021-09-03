
package org.restcomm.protocols.ss7.tcapAnsi;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tcapAnsi.api.TCAPStack;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.asn.InvokeImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PreviewDialogData {

    private static final Logger logger = Logger.getLogger(PreviewDialogData.class);

    private ApplicationContext lastACN;
    private InvokeImpl[] operationsSentA;
    private InvokeImpl[] operationsSentB;

    private Object upperDialog;

    private PreviewDialogDataKey prevewDialogDataKey1;
    private PreviewDialogDataKey prevewDialogDataKey2;

    private ReentrantLock dialogLock = new ReentrantLock();
    private Future idleTimerFuture;
    private ScheduledExecutorService executor;
    private TCAPProviderImpl provider;
    private long idleTaskTimeout;
    private Long dialogId;

    public PreviewDialogData(TCAPProviderImpl provider, Long dialogId) {
        this.provider = provider;
        this.dialogId = dialogId;
        TCAPStack stack = provider.getStack();
        this.idleTaskTimeout = stack.getDialogIdleTimeout();
        this.executor = provider._EXECUTOR;
    }

    public ApplicationContext getLastACN() {
        return lastACN;
    }

    public Long getDialogId() {
        return dialogId;
    }

    public InvokeImpl[] getOperationsSentA() {
        return operationsSentA;
    }

    public InvokeImpl[] getOperationsSentB() {
        return operationsSentB;
    }

    public Object getUpperDialog() {
        return upperDialog;
    }

    public void setLastACN(ApplicationContext val) {
        lastACN = val;
    }

    public void setOperationsSentA(InvokeImpl[] val) {
        operationsSentA = val;
    }

    public void setOperationsSentB(InvokeImpl[] val) {
        operationsSentB = val;
    }

    public void setUpperDialog(Object val) {
        upperDialog = val;
    }

    protected PreviewDialogDataKey getPrevewDialogDataKey1() {
        return prevewDialogDataKey1;
    }

    protected PreviewDialogDataKey getPrevewDialogDataKey2() {
        return prevewDialogDataKey2;
    }

    protected void setPrevewDialogDataKey1(PreviewDialogDataKey val) {
        prevewDialogDataKey1 = val;
    }

    protected void setPrevewDialogDataKey2(PreviewDialogDataKey val) {
        prevewDialogDataKey2 = val;
    }

    protected void startIdleTimer() {

        try {
            this.dialogLock.lock();
            if (this.idleTimerFuture != null) {
                throw new IllegalStateException();
            }

            IdleTimerTask t = new IdleTimerTask();
            t.pdd = this;
            this.idleTimerFuture = this.executor.schedule(t, this.idleTaskTimeout, TimeUnit.MILLISECONDS);

        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void stopIdleTimer() {
        try {
            this.dialogLock.lock();
            if (this.idleTimerFuture != null) {
                this.idleTimerFuture.cancel(false);
                this.idleTimerFuture = null;
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void restartIdleTimer() {
        try {
            this.dialogLock.lock();

            stopIdleTimer();
            startIdleTimer();
        } finally {
            this.dialogLock.unlock();
        }
    }

    private class IdleTimerTask implements Runnable {
        PreviewDialogData pdd;

        public void run() {
            try {
                dialogLock.lock();

//              Dialog d1 = new DialogImpl(localAddress, remoteAddress, seqControl, provider._EXECUTOR, provider, pdd, sideB);
                DialogImpl dlg = (DialogImpl)provider.getPreviewDialog(prevewDialogDataKey1, null, null, null, 0);
                provider.timeout(dlg);
                provider.removePreviewDialog(dlg);

//                dialogLock.lock();
//
////                int i1 = provider.dialogPreviewList.size();
//                provider.removePreviewDialog(pdd);
////                int i2 = provider.dialogPreviewList.size();
//
//                if (logger.isEnabledFor(Level.ERROR)) {
//                    StringBuilder sb = new StringBuilder();
//                    if (this.pdd.prevewDialogDataKey1 != null) {
//                        sb.append(", trId1=");
//                        sb.append(this.pdd.prevewDialogDataKey1.origTxId);
//                    }
//                    if (this.pdd.prevewDialogDataKey2 != null) {
//                        sb.append(", trId2=");
//                        sb.append(this.pdd.prevewDialogDataKey2.origTxId);
//                    }
////                    logger.error("Dialog closed by a timeout" + sb.toString() + "  " + i1 + "->" + i2);
//                }
            } finally {
                dialogLock.unlock();
            }
        }

    }
}
