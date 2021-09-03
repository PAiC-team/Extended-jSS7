
package org.restcomm.protocols.ss7.oam.common.alarm;

/**
 *
 * An alarm mediator supplies with alarm messages for alarm receivers
 *
 * Alarm receivers can: - get notifications of creating and clearing alarms by subscribing to AlarmMediator's onAlarm() event
 * (Alarm receivers must implement AlarmListener interface and register by invoking registerAlarmListener()) - get a current
 * active alarm list by invoking getCurrentAlarmList() method
 *
 * @author sergey vetyutnev
 *
 */
public interface AlarmMediator {

    /**
     * Register a listener for notifications of creating and clearing alarms
     *
     * @param listener
     */
    void registerAlarmListener(AlarmListener listener);

    /**
     * Unregister a listener for notifications of creating and clearing alarms * @param listener
     */
    void unregisterAlarmListener(AlarmListener listener);

    /**
     * Obtain a list of alarms that are currently active
     *
     * @return
     */
    CurrentAlarmList getCurrentAlarmList();

    /**
     * Returns a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    String getAlarmProviderObjectPath();

    /**
     * Set a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    void setAlarmProviderObjectPath(String value);

}
