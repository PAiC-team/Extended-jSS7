
package org.restcomm.protocols.ss7.oam.common.statistics;

import java.util.Arrays;

import org.restcomm.protocols.ss7.oam.common.statistics.api.ComplexValue;
import org.restcomm.protocols.ss7.oam.common.statistics.api.SourceValueObject;

/**
*
* @author sergey vetyutnev
*
*/
public class SourceValueObjectImpl implements SourceValueObject {

    private String objectName;
    private long value;
    private double valueA;
    private double valueB;
    private ComplexValue[] complexValue;

    public SourceValueObjectImpl(String objectName, long value) {
        this.objectName = objectName;
        this.value = value;
    }

    public void setValueA(double val) {
        valueA = val;
    }

    public void setValueB(double val) {
        valueB = val;
    }

    public void setComplexValue(ComplexValue[] val) {
        complexValue = val;
    }

    @Override
    public String getObjectName() {
        return objectName;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public double getValueA() {
        return valueA;
    }

    @Override
    public double getValueB() {
        return valueB;
    }

    @Override
    public ComplexValue[] getComplexValue() {
        return complexValue;
    }

    @Override
    public String toString() {
        return "SourceValueObjectImpl [objectName=" + objectName + ", value=" + value + ", valueA=" + valueA + ", valueB="
                + valueB + ", complexValue=" + Arrays.toString(complexValue) + "]";
    }

}
