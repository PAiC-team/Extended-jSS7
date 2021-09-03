
package org.restcomm.protocols.ss7.oam.common.statistics;

import org.restcomm.protocols.ss7.oam.common.statistics.api.ComplexValue;

/**
*
* @author sergey vetyutnev
*
*/
public class ComplexValueImpl implements ComplexValue {

    private String key;
    private long value;

    public ComplexValueImpl(String key, long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public long getValue() {
        return value;
    }

}
