
package org.restcomm.protocols.ss7.sccpext.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.sccp.SccpCongestionControlAlgo;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeExt;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.CongStateTimerA;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.CongStateTimerD;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.SccpCongestionControl;

/**
*
* @author sergey vetyutnev
*
*/
public class RemoteSignalingPointCodeExtImpl implements RemoteSignalingPointCodeExt {

    private Logger logger = Logger.getLogger(RemoteSignalingPointCodeExtImpl.class);

    private RemoteSignalingPointCodeImpl remoteSignalingPointCodeImpl;

    private CongStateTimerA timerA;
    private CongStateTimerD timerD;

    private SccpCongestionControl sccpCongestionControl;

    public RemoteSignalingPointCodeExtImpl(SccpCongestionControl sccpCongestionControl,
            RemoteSignalingPointCodeImpl remoteSignalingPointCodeImpl) {
        this.sccpCongestionControl = sccpCongestionControl;
        this.remoteSignalingPointCodeImpl = remoteSignalingPointCodeImpl;
    }

    public void clearCongLevel() {

//        remoteSignalingPointCodeImpl.rl = 0;
//        remoteSignalingPointCodeImpl.rsl = 0;
        remoteSignalingPointCodeImpl.setCurrentRestrictionLevel(0);

        this.sccpCongestionControl.onRestrictionLevelChange(remoteSignalingPointCodeImpl.getRemoteSpc(),
                remoteSignalingPointCodeImpl.getCurrentRestrictionLevel(), false);
        // remoteSignalingPointCodeImpl.rl
    }

    public void increaseCongLevel(int level) {

        if (this.timerA != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong increaseCongLevel - no actions because of timerA is not over: " + this);
            }
            return;
        }

        timerA = new CongStateTimerA(this);
        this.sccpCongestionControl.scheduleTimer(timerA, sccpCongestionControl.getCongControlTIMER_A());
        CongStateTimerD _timerD = timerD;
        if (_timerD != null) {
            _timerD.cancel();
        }
        timerD = new CongStateTimerD(this);
        this.sccpCongestionControl.scheduleTimer(timerD, sccpCongestionControl.getCongControlTIMER_D());

        // remoteSignalingPointCodeImpl.rl
        if (remoteSignalingPointCodeImpl.getCurrentRestrictionLevel() >= sccpCongestionControl.getCongControlN()) {
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong increaseCongLevel - no actions because rl has its max level: " + this);
            }
            return;
        }
        if (sccpCongestionControl.getCongControl_Algo() == SccpCongestionControlAlgo.levelDepended
                && remoteSignalingPointCodeImpl.getCurrentRestrictionLevel() >= SccpCongestionControl.getMaxRestrictionLevelForMtp3Level(level)) {
            return;
        }

        remoteSignalingPointCodeImpl.setRsl(remoteSignalingPointCodeImpl.getCurrentRestrictionSubLevel() + 1);
        if (remoteSignalingPointCodeImpl.getCurrentRestrictionSubLevel() >= sccpCongestionControl.getCongControlM()) {
            remoteSignalingPointCodeImpl.setRsl(0);
            remoteSignalingPointCodeImpl.setRsl(remoteSignalingPointCodeImpl.getCurrentRestrictionLevel() + 1);
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong increaseCongLevel - rl has increased: " + this);
            }

            this.sccpCongestionControl.onRestrictionLevelChange(remoteSignalingPointCodeImpl.getRemoteSpc(),
                    remoteSignalingPointCodeImpl.getCurrentRestrictionLevel(), true);
        }
    }

    public void clearTimerA() {
        timerA = null;
    }

    public void decreaseCongLevel() {
        if (remoteSignalingPointCodeImpl.getCurrentRestrictionLevel() == 0 && remoteSignalingPointCodeImpl.getCurrentRestrictionSubLevel() == 0)
            return;

        remoteSignalingPointCodeImpl.setRsl(remoteSignalingPointCodeImpl.getCurrentRestrictionSubLevel() - 1);
        if (remoteSignalingPointCodeImpl.getCurrentRestrictionSubLevel() < 0) {
            remoteSignalingPointCodeImpl.setRsl(sccpCongestionControl.getCongControlM() - 1);
            remoteSignalingPointCodeImpl.setRsl(remoteSignalingPointCodeImpl.getCurrentRestrictionLevel() - 1);
            if (logger.isDebugEnabled()) {
                logger.debug("SCCP cong increaseCongLevel - rl has decreased: " + this);
            }

            this.sccpCongestionControl.onRestrictionLevelChange(remoteSignalingPointCodeImpl.getRemoteSpc(),
                    remoteSignalingPointCodeImpl.getCurrentRestrictionLevel(), false);
        }

        timerD = new CongStateTimerD(this);
        this.sccpCongestionControl.scheduleTimer(timerD, sccpCongestionControl.getCongControlTIMER_D());
    }

    @Override
    public String toString() {
        return remoteSignalingPointCodeImpl.toString();
    }

}
