
package org.restcomm.protocols.ss7.oam.common.statistics;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanLayer;

/**
*
* @author sergey vetyutnev
*
*/
public enum CounterLayer implements MBeanLayer {

    COUNTER("COUNTER");

    private final String name;

    public static final String NAME_COUNTER = "COUNTER";

    private CounterLayer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static MBeanLayer getInstance(String name) {
        if (NAME_COUNTER.equals(name)) {
            return COUNTER;
        }

        return null;
    }

}
