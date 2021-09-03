
package org.restcomm.protocols.ss7.oam.common.alarm;

import javax.management.Notification;

/**
 *
 * Notifications about alarms that AlarmProvider sends as JMX notifications
 *
 * @author sergey vetyutnev
 *
 */
public class AlarmNotification extends Notification {

    private static final long serialVersionUID = -5746460310998997260L;

    private AlarmMessage alarmMessage;

    public AlarmNotification(String type, Object source, long sequenceNumber, long timeStamp, String msg,
            AlarmMessage alarmMessage) {
        super(type, source, sequenceNumber, timeStamp, msg);

        this.alarmMessage = alarmMessage;
    }

    public AlarmMessage getAlarmMessage() {
        return this.alarmMessage;
    }
}
