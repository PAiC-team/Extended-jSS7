
package org.restcomm.protocols.ss7.oam.common.alarm;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanLayer;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum AlarmLayer implements MBeanLayer {

    ALARM("ALARM");

    private final String name;

    public static final String NAME_ALARM = "ALARM";

    private AlarmLayer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static MBeanLayer getInstance(String name) {
        if (NAME_ALARM.equals(name)) {
            return ALARM;
        }

        return null;
    }

}
