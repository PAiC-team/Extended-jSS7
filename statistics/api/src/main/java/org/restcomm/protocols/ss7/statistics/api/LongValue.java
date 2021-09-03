
package org.restcomm.protocols.ss7.statistics.api;

/**
*
* @author sergey vetyutnev
*
*/
public class LongValue {

    private long val;

    public long getValue() {
        return val;
    }

    public void updateValue() {
        val++;
    }

}
