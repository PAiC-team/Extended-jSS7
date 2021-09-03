
package org.restcomm.protocols.ss7.statistics;

import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;

/**
*
* @author sergey vetyutnev
*
*/
public class StatDataCollectorMin extends StatDataCollectorLongImpl {

    public StatDataCollectorMin(String name) {
        super(name);
    }

    @Override
    protected void reset() {
        val = Long.MAX_VALUE;
    }

    @Override
    public void updateData(long newVal) {
        if (val > newVal)
            val = newVal;
    }

    @Override
    public StatDataCollectorType getStatDataCollectorType() {
        return StatDataCollectorType.MIN;
    }

    @Override
    public void updateData(String newValue) {
    }

}
