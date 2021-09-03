
package org.restcomm.protocols.ss7.oam.common.alarm;

import java.util.List;
import javolution.util.FastList;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AlarmListenerCollection {

    private transient List<AlarmListener> alarmListeners = new FastList<AlarmListener>();
    private String alarmProviderObjectPath;
    private String alarmProviderObjectPathWithSlash;

    public void registerAlarmListener(AlarmListener listener) {
        synchronized (this) {
            this.alarmListeners.add(listener);
        }
    }

    public void unregisterAlarmListener(AlarmListener listener) {
        synchronized (this) {
            this.alarmListeners.remove(listener);
        }
    }

    public void prepareAlarm(AlarmMessage alarm) {
        if (alarmProviderObjectPath != null)
            alarm.addPrefixToAlarmSource(alarmProviderObjectPath);
    }

    public void onAlarm(AlarmMessage alarm) {
        this.prepareAlarm(alarm);

        synchronized (this) {
            for (AlarmListener listener : this.alarmListeners) {
                listener.onAlarm(alarm);
            }
        }
    }

    /**
     * Returns a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    public String getAlarmProviderObjectPath() {
        return alarmProviderObjectPath;
    }

    /**
     * Set a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    public void setAlarmProviderObjectPath(String value) {
        alarmProviderObjectPath = value;
        if (alarmProviderObjectPath != null && !alarmProviderObjectPath.equals(""))
            alarmProviderObjectPathWithSlash = "/" + alarmProviderObjectPath;
        else
            alarmProviderObjectPathWithSlash = null;
    }

}
