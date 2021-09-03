
package org.restcomm.protocols.ss7.oam.common.statistics;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDef;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDefSet;

/**
*
* @author sergey vetyutnev
*
*/
public class CounterDefSetImpl implements CounterDefSet {

    private String name;
    private ArrayList<CounterDef> counterDefs = new ArrayList<CounterDef>();

    public CounterDefSetImpl(String name) {
        this.name = name;
    }

    public void addCounterDef(CounterDef val) {
        counterDefs.add(val);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CounterDef[] getCounterDefs() {
        CounterDef[] res = new CounterDef[counterDefs.size()];
        counterDefs.toArray(res);
        return res;
    }

}
