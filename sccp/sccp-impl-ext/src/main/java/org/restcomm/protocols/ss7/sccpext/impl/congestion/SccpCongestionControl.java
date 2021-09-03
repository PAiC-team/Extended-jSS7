
package org.restcomm.protocols.ss7.sccpext.impl.congestion;

import java.util.concurrent.TimeUnit;

import org.restcomm.protocols.ss7.sccp.SccpCongestionControlAlgo;
import org.restcomm.protocols.ss7.sccp.SccpStack;
import org.restcomm.protocols.ss7.sccp.impl.SccpManagement;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SccpCongestionControl {

    private SccpManagement sccpManagement;
    private SccpStack sccpStack;

    public SccpCongestionControl(SccpManagement sccpManagement, SccpStack sccpStack) {
        this.sccpManagement = sccpManagement;
        this.sccpStack = sccpStack;
    }

    public void scheduleTimer(Runnable timer, int delay) {
        this.sccpManagement.getManagementExecutors().schedule(timer, delay, TimeUnit.MILLISECONDS);
    }

    public void onRestrictionLevelChange(int affectedPc, int newLevel, boolean levelEncreased) {
        sccpManagement.onRestrictionLevelChange(affectedPc, newLevel, levelEncreased);
    }

    public int getCongControlTIMER_A() {
        return sccpStack.getCongControlTIMER_A();
    }

    public int getCongControlTIMER_D() {
        return sccpStack.getCongControlTIMER_D();
    }

    public int getCongControlN() {
        return ((SccpStackImpl) sccpStack).getCongControlN();
    }

    public int getCongControlM() {
        return ((SccpStackImpl) sccpStack).getCongControlM();
    }

    public SccpCongestionControlAlgo getCongControl_Algo() {
        return sccpStack.getCongControl_Algo();
    }

    public boolean isCongControl_blockingOutgoingSccpMessages() {
        return sccpStack.isCongControl_blockingOutgoingSccpMessages();
    }

    public static int generateSccpUserCongLevel(int restrictionLevel) {
        if (restrictionLevel <= 1)
            return 0;
        if (restrictionLevel <= 3)
            return 1;
        if (restrictionLevel <= 5)
            return 2;
        return 3;
    }

    public static int getMaxRestrictionLevelForMtp3Level(int mtp3Level) {
        if (mtp3Level == 1)
            return 3;
        if (mtp3Level == 2)
            return 5;
        return 8;
    }
}
