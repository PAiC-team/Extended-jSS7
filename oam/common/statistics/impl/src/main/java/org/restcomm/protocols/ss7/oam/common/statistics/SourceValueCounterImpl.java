
package org.restcomm.protocols.ss7.oam.common.statistics;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDef;
import org.restcomm.protocols.ss7.oam.common.statistics.api.SourceValueCounter;
import org.restcomm.protocols.ss7.oam.common.statistics.api.SourceValueObject;

import javolution.util.FastMap;

/**
*
* @author sergey vetyutnev
*
*/
public class SourceValueCounterImpl implements SourceValueCounter {

    private CounterDef counterDef;
    private FastMap<String, SourceValueObject> objects = new FastMap<String, SourceValueObject>();

    public SourceValueCounterImpl(CounterDef counterDef) {
        this.counterDef = counterDef;
    }

    public void addObject(SourceValueObject val) {
        objects.put(val.getObjectName(), val);
    }

    @Override
    public CounterDef getCounterDef() {
        return counterDef;
    }

    @Override
    public FastMap<String, SourceValueObject> getObjects() {
        return this.objects;
    }

    @Override
    public String toString() {
        return "SourceValueCounterImpl [counterDef=" + counterDef + ", objects=" + objects + "]";
    }

}
