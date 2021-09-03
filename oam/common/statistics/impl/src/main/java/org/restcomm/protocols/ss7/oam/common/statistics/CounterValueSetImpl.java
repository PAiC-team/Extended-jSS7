
package org.restcomm.protocols.ss7.oam.common.statistics;

import java.util.ArrayList;
import java.util.Date;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterValue;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterValueSet;

/**
*
* @author sergey vetyutnev
*
*/
public class CounterValueSetImpl implements CounterValueSet {

    public Date startTime;
    public Date endTime;
    public int duration;
    public int durationSeconds;
    public ArrayList<CounterValue> counterValues = new ArrayList<CounterValue>();

    public CounterValueSetImpl(Date startTime, Date endTime, int duration, int durationSeconds) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.durationSeconds = durationSeconds;
    }

    public void addCounterValue(CounterValue val) {
        counterValues.add(val);
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getDurationSeconds() {
        return durationSeconds;
    }

    @Override
    public CounterValue[] getCounterValues() {
        CounterValue[] res = new CounterValue[counterValues.size()];
        counterValues.toArray(res);
        return res;
    }

}
