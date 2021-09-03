
package org.restcomm.protocols.ss7.sccpext.impl.congestion;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.sccpext.impl.RemoteSignalingPointCodeExtImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class CongStateTimerD implements Runnable {
    private RemoteSignalingPointCodeExtImpl remoteSignalingPointCodeExtImpl;
    private boolean isCancelled;
    private Logger logger = Logger.getLogger(CongStateTimerD.class);

    public CongStateTimerD(RemoteSignalingPointCodeExtImpl remoteSignalingPointCodeExtImpl) {
        if (logger.isDebugEnabled()) {
            logger.debug("SCCP cong TimerD has started: " + remoteSignalingPointCodeExtImpl);
        }
        this.remoteSignalingPointCodeExtImpl = remoteSignalingPointCodeExtImpl;
    }

    @Override
    public void run() {
        if (!isCancelled) {
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong TimerD decreasing cong level: " + remoteSignalingPointCodeExtImpl);
            }
            remoteSignalingPointCodeExtImpl.decreaseCongLevel();
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong TimerD was cancelled: " + remoteSignalingPointCodeExtImpl);
            }
        }
    }

    public void cancel() {
        if (logger.isDebugEnabled()) {
            logger.debug("SCCP cong TimerD is cancelling: " + remoteSignalingPointCodeExtImpl);
        }
        isCancelled = true;
    }
}
