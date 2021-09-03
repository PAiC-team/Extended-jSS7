
package org.restcomm.protocols.ss7.oam.common.statistics;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDef;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterType;

/**
*
* @author sergey vetyutnev
*
*/
public class CounterDefImpl implements CounterDef {

    public CounterType counterType;
    public String counterName;
    public String description;

    public CounterDefImpl(CounterType counterType, String counterName, String description) {
        this.counterType = counterType;
        this.counterName = counterName;
        this.description = description;
    }

    @Override
    public String getCounterName() {
        return counterName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public CounterType getCounterType() {
        return counterType;
    }

    @Override
    public String toString() {
        return "CounterDefImpl [counterType=" + counterType + ", counterName=" + counterName + ", description=" + description
                + "]";
    }

}
