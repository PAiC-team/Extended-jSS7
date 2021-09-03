
package org.restcomm.protocols.ss7.oam.common.tcap;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDefSet;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterMediator;
import org.restcomm.protocols.ss7.oam.common.statistics.api.SourceValueSet;

/**
*
* @author sergey vetyutnev
*
*/
public class CounterMediatorProxy implements CounterMediator {

    @Override
    public String getCounterMediatorName() {
        return "Test";
    }

    @Override
    public String[] getCounterDefSetList() {
        String[] res = new String[] { "counterSet1", "counterSet2", "counterSet3" };
        return res;
    }

    @Override
    public CounterDefSet getCounterDefSet(String counterDefSetName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SourceValueSet getSourceValueSet(String counterDefSetName, String campaignName, int durationInSeconds) {
        // TODO Auto-generated method stub
        return null;
    }

}
