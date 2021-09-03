
package org.restcomm.protocols.ss7.statistics;

import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;

/**
*
* @author sergey vetyutnev
*
*/
public class StatDataCollectorMax extends StatDataCollectorLongImpl {

    public StatDataCollectorMax(String name) {
        super(name);
    }

    @Override
    protected void reset() {
        val = Long.MIN_VALUE;
    }

    @Override
    public void updateData(long newVal) {
        if (val < newVal)
            val = newVal;
    }

    @Override
    public StatDataCollectorType getStatDataCollectorType() {
        return StatDataCollectorType.MAX;
    }

    @Override
    public void updateData(String newVal) {
    }

}
