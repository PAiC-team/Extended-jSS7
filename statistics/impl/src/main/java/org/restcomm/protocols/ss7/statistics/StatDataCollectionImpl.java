
package org.restcomm.protocols.ss7.statistics;

import java.util.Date;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.statistics.api.StatCounterCollection;
import org.restcomm.protocols.ss7.statistics.api.StatDataCollection;
import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

/**
*
* Collection of data for all StatDataCollector-style counters
* Contains StatCounterCollection data depending on a counter name
*
* @author sergey vetyutnev
*
*/
public class StatDataCollectionImpl implements StatDataCollection {

    private FastMap<String, StatCounterCollection> statsCounterCollection = new FastMap<String, StatCounterCollection>();

    public StatCounterCollection registerStatCounterCollector(String counterName, StatDataCollectorType type) {
        synchronized (this) {
            StatCounterCollectionImpl statCounterCollection = new StatCounterCollectionImpl(counterName, type);
            statsCounterCollection.put(counterName, statCounterCollection);
            return statCounterCollection;
        }
    }

    public StatCounterCollection unregisterStatCounterCollector(String counterName) {
        synchronized (this) {
            StatCounterCollection statCounterCollection = statsCounterCollection.remove(counterName);
            return statCounterCollection;
        }
    }

    public StatCounterCollection getStatCounterCollector(String counterName) {
        synchronized (this) {
            StatCounterCollection statCounterCollection = statsCounterCollection.get(counterName);
            return statCounterCollection;
        }
    }

    public void clearDeadCampaigns(Date lastTime) {
        synchronized (this) {
            for (String s : statsCounterCollection.keySet()) {
                StatCounterCollection statCounterCollection = statsCounterCollection.get(s);
                statCounterCollection.clearDeadCampaigns(lastTime);
            }
        }
    }

    public StatResult restartAndGet(String counterName, String campaignName) {
        StatCounterCollection statCounterCollection;
        synchronized (this) {
            statCounterCollection = this.statsCounterCollection.get(counterName);
        }
        if (statCounterCollection != null) {
            return statCounterCollection.restartAndGet(campaignName);
        } else {
            return null;
        }
    }

    public void updateData(String counterName, long newValue) {
        StatCounterCollection statCounterCollection;
        synchronized (this) {
            statCounterCollection = this.statsCounterCollection.get(counterName);
        }
        if (statCounterCollection != null) {
            statCounterCollection.updateData(newValue);
        }
    }

    public void updateData(String counterName, String newValue) {
        StatCounterCollection statCounterCollection;
        synchronized (this) {
            statCounterCollection = this.statsCounterCollection.get(counterName);
        }
        if (statCounterCollection != null) {
            statCounterCollection.updateData(newValue);
        }
    }
}
