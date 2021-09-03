
package org.restcomm.protocols.ss7.tcap;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PreviewDialogData {

    private static final Logger logger = Logger.getLogger(PreviewDialogData.class);

    private ApplicationContextName lastACN;
    private InvokeImpl[] operationsSentA;
    private InvokeImpl[] operationsSentB;

    private Object upperDialog;

    private PreviewDialogDataKey previewDialogDataKey1;
    private PreviewDialogDataKey previewDialogDataKey2;

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

    public ApplicationContextName getLastACN() {
        return this.lastACN;
    }

    public Long getDialogId() {
        return this.dialogId;
    }

    public InvokeImpl[] getOperationsSentA() {
        return this.operationsSentA;
    }

    public InvokeImpl[] getOperationsSentB() {
        return this.operationsSentB;
    }

    public Object getUpperDialog() {
        return this.upperDialog;
    }

    public void setLastACN(ApplicationContextName applicationContextName) {
        this.lastACN = applicationContextName;
    }

    public void setOperationsSentA(InvokeImpl[] operationsSentA) {
        this.operationsSentA = operationsSentA;
    }

    public void setOperationsSentB(InvokeImpl[] operationsSentB) {
        this.operationsSentB = operationsSentB;
    }

    public void setUpperDialog(Object upperDialogObject) {
        this.upperDialog = upperDialogObject;
    }

    protected PreviewDialogDataKey getPreviewDialogDataKey1() {
        return this.previewDialogDataKey1;
    }

    protected PreviewDialogDataKey getPreviewDialogDataKey2() {
        return this.previewDialogDataKey2;
    }

    protected void setPreviewDialogDataKey1(PreviewDialogDataKey val) {
        this.previewDialogDataKey1 = val;
    }

    protected void setPreviewDialogDataKey2(PreviewDialogDataKey val) {
        this.previewDialogDataKey2 = val;
    }

    protected void startIdleTimer() {

        try {
            this.dialogLock.lock();
            if (this.idleTimerFuture != null) {
                throw new IllegalStateException();
            }

            IdleTimerTask idleTimerTask = new IdleTimerTask();
            idleTimerTask.pdd = this;
            this.idleTimerFuture = this.executor.schedule(idleTimerTask, this.idleTaskTimeout, TimeUnit.MILLISECONDS);

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
                DialogImpl dialog = (DialogImpl)provider.getPreviewDialog(previewDialogDataKey1, null, null, null, 0);
                provider.timeout(dialog);
                provider.removePreviewDialog(dialog);

//                provider.removePreviewDialog(pdd);
//
//                if (logger.isEnabledFor(Level.ERROR)) {
//                    StringBuilder sb = new StringBuilder();
//                    if (this.pdd.previewDialogDataKey1 != null) {
//                        sb.append(", trId1=");
//                        sb.append(this.pdd.previewDialogDataKey1.origTxId);
//                    }
//                    if (this.pdd.previewDialogDataKey2 != null) {
//                        sb.append(", trId2=");
//                        sb.append(this.pdd.previewDialogDataKey2.origTxId);
//                    }
//                }
            } finally {
                dialogLock.unlock();
            }
        }

    }
}
