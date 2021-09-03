
package org.restcomm.protocols.ss7.oam.common.alarm;

import java.util.Calendar;

/**
 * Represents one alarm
 *
 * @author sergey vetyutnev
 *
 */
public interface AlarmMessage extends Comparable<AlarmMessage> {

    /**
     * Returns false if an alarm is active now and true if it has already been cleared
     */
    boolean getIsCleared();

    /**
     * Alarm source is the name of subsystem that generated an alarm
     */
    String getAlarmSource();

    /**
     * Severity of an alarm (critical, major, minor, warning)
     */
    AlarmSeverity getAlarmSeverity();

    /**
     * A name of an object that generated an alarm
     */
    String getObjectName();

    /**
     * This is the identifier of the object that generated an alarm The form of ObjectPath must be:
     * "/<Root object or subsystem>/<Next level object or subsystem>/.../<Object or subsystem that directly generated an alarm>"
     * Example: "SS7/m3ua/AS/AS_4" ObjectPath+ProblemName pair must uniquely identify an alarm
     */
    String getObjectPath();

    /**
     * A name of the problem ObjectPath+ProblemName pair must uniquely identify an alarm
     */
    String getProblemName();

    /**
     * A cause of an alarm This field may be null
     */
    String getCause();

    /**
     * Time when an alarm occured
     */
    Calendar getTimeAlarm();

    /**
     * Time when an alarm cleared This field must be null if alarm has not been cleared
     */
    Calendar getTimeClear();

    /**
     * AlarmMediator can add a prefix to AlarmSource field by invoking this method
     */
    void addPrefixToAlarmSource(String prefix);

}
