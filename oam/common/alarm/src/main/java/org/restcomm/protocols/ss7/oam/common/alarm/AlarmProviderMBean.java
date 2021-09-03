
package org.restcomm.protocols.ss7.oam.common.alarm;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface AlarmProviderMBean {

    /**
     * Returns a current list of active alarms
     */
    CurrentAlarmList getCurrentAlarmList();

    /**
     * Returns a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    String getAlarmProviderObjectPath();

}
