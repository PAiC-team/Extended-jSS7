
package org.restcomm.protocols.ss7.statistics.api;

import java.util.Date;

/**
 *
 * Collection of data for all StatDataCollector-style counters Contains StatCounterCollection data depending on a counter name
 *
 * @author sergey vetyutnev
 *
 */
public interface StatDataCollection {

    StatCounterCollection registerStatCounterCollector(String counterName, StatDataCollectorType type);

    StatCounterCollection unregisterStatCounterCollector(String counterName);

    StatCounterCollection getStatCounterCollector(String counterName);

    void clearDeadCampaigns(Date lastTime);

    StatResult restartAndGet(String counterName, String campaignName);

    void updateData(String counterName, long newValue);

    void updateData(String counterName, String newValue);
}
