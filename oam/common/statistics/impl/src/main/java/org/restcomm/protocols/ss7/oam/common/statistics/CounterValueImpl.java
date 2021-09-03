
package org.restcomm.protocols.ss7.oam.common.statistics;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.oam.common.statistics.api.ComplexValue;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDef;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterValue;

/**
*
* @author sergey vetyutnev
*
*/
public class CounterValueImpl implements CounterValue {

    private CounterDef counterDef;
    private String objectName;
    private long longValue;
    private double doubleValue;
    private ArrayList<ComplexValue> complexValue = new ArrayList<ComplexValue>();

    public CounterValueImpl(CounterDef counterDef, String objectName, long longValue) {
        this.counterDef = counterDef;
        this.objectName = objectName;
        this.longValue = longValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public void addComplexValue(ComplexValue val) {
        complexValue.add(val);
    }

    @Override
    public CounterDef getCounterDef() {
        return counterDef;
    }

    @Override
    public String getObjectName() {
        return objectName;
    }

    @Override
    public long getLongValue() {
        return longValue;
    }

    @Override
    public double getDoubleValue() {
        return doubleValue;
    }

    @Override
    public ComplexValue[] getComplexValue() {
        ComplexValue[] res = new ComplexValue[complexValue.size()];
        complexValue.toArray(res);
        return res;
    }

}
