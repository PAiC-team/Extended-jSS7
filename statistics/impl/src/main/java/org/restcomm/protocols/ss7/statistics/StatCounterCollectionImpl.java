
package org.restcomm.protocols.ss7.statistics;

import java.util.ArrayList;
import java.util.Date;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.statistics.api.StatCounterCollection;
import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

/**
*
* @author sergey vetyutnev
*
*/
public class StatCounterCollectionImpl implements StatCounterCollection {

    private String counterName;
    private StatDataCollectorType type;
    private FastMap<String, StatDataCollectorAbstractImpl> statsCounterCollectionImpl = new FastMap<String, StatDataCollectorAbstractImpl>();

    public StatCounterCollectionImpl(String counterName, StatDataCollectorType type) {
        this.counterName = counterName;
        this.type = type;
    }

    @Override
    public String getCounterName() {
        return this.counterName;
    }

    @Override
    public void clearDeadCampaigns(Date lastTime) {
        synchronized (this) {
            ArrayList<String> toDel = new ArrayList<String>();
            for (String s : statsCounterCollectionImpl.keySet()) {
                StatDataCollectorAbstractImpl d = statsCounterCollectionImpl.get(s);
                if (d.getSessionStartTime().before(lastTime)) {
                    toDel.add(s);
                }
            }
            for (String s : toDel) {
                statsCounterCollectionImpl.remove(s);
            }
        }
    }

    @Override
    public StatResult restartAndGet(String campaignName) {
        synchronized (this) {
            StatDataCollectorAbstractImpl statDataCollector = statsCounterCollectionImpl.get(campaignName);
            if (statDataCollector != null) {
                return statDataCollector.restartAndGet();
            } else {
                switch (type) {
                case MIN:
                    statDataCollector = new StatDataCollectorMin(campaignName);
                    statDataCollector.reset();
                    break;
                case MAX:
                    statDataCollector = new StatDataCollectorMax(campaignName);
                    statDataCollector.reset();
                    break;
                case StringLongMap:
                    statDataCollector = new StringLongMap(campaignName);
                    statDataCollector.reset();
                    break;
                }
                if (statDataCollector != null) {
                    statsCounterCollectionImpl.put(campaignName, statDataCollector);
                }
                return null;
            }
        }
    }

    @Override
    public void updateData(long newValue) {
        synchronized (this) {
            for (String s : statsCounterCollectionImpl.keySet()) {
                StatDataCollectorAbstractImpl statDataCollector = statsCounterCollectionImpl.get(s);
                statDataCollector.updateData(newValue);
            }
        }
    }

    @Override
    public void updateData(String newValue) {
        synchronized (this) {
            for (String s : statsCounterCollectionImpl.keySet()) {
                StatDataCollectorAbstractImpl statDataCollector = statsCounterCollectionImpl.get(s);
                statDataCollector.updateData(newValue);
            }
        }
    }

}
