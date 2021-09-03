
package org.restcomm.protocols.ss7.oam.common.alarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CurrentAlarmListImpl implements CurrentAlarmList, Serializable {

    private ArrayList<AlarmMessage> lst = new ArrayList<AlarmMessage>();

    public CurrentAlarmListImpl() {
    }

    public void addAlarm(AlarmMessage alm) {
        this.lst.add(alm);
    }

    public void sortAlarms() {
        Collections.sort(this.lst);
    }

    @Override
    public AlarmMessage[] getCurrentAlarmList() {
        AlarmMessage[] res = new AlarmMessage[this.lst.size()];
        return this.lst.toArray(res);
    }

    @Override
    public String toString() {
        return "CurrentAlarmListImpl [lst=" + lst + "]";
    }

}
