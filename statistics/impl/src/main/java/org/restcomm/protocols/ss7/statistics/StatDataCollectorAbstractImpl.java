
package org.restcomm.protocols.ss7.statistics;

import java.util.Date;

import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

/**
*
* @author sergey vetyutnev
*
*/
public abstract class StatDataCollectorAbstractImpl implements StatDataCollector {

    private String campaignName;
    protected Date sessionStartTime = new Date();

    public StatDataCollectorAbstractImpl(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignName() {
        return this.campaignName;
    }

    public Date getSessionStartTime() {
        return this.sessionStartTime;
    }

    public abstract StatResult restartAndGet();

    protected abstract void reset();

    public abstract void updateData(long newValue);

    public abstract void updateData(String newValue);

    public abstract StatDataCollectorType getStatDataCollectorType();

}
